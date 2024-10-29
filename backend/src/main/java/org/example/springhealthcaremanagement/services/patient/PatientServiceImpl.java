package org.example.springhealthcaremanagement.services.patient;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.patient.PatientDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientRequestDto;
import org.example.springhealthcaremanagement.entities.gender.EGender;
import org.example.springhealthcaremanagement.entities.gender.Gender;
import org.example.springhealthcaremanagement.entities.patient.Patient;
import org.example.springhealthcaremanagement.entities.user.User;
import org.example.springhealthcaremanagement.exception.EntityNotFoundException;
import org.example.springhealthcaremanagement.mappers.AddressMapper;
import org.example.springhealthcaremanagement.mappers.PatientMapper;
import org.example.springhealthcaremanagement.repositories.gender.GenderRepository;
import org.example.springhealthcaremanagement.repositories.patient.PatientRepository;
import org.example.springhealthcaremanagement.repositories.user.UserRepository;
import org.example.springhealthcaremanagement.services.address.AddressService;
import org.example.springhealthcaremanagement.services.messages.SmsService;
import org.example.springhealthcaremanagement.services.messages.SmsServiceImpl;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final AddressService addressService;
    private final AddressMapper addressMapper;
    private final SmsService smsService;
    @Override
    public PatientDto save(PatientRequestDto patientRequestDto) {
        Patient patient = patientMapper.requestDtoToEntity(patientRequestDto);
        patient.setUser(userRepository.findByUsername(patientRequestDto.getUser().getUsername()).orElseThrow());
        patient.setGender(genderRepository.findByName(EGender.valueOf(patientRequestDto.getGender().getName())).orElseThrow());
        Patient savedPatient = patientRepository.save(patient);
        smsService.createNewContact(savedPatient);
        return patientMapper.toDto(savedPatient);
    }


    @Override
    public List<PatientDto> findAll() {
       List<Patient> patients = patientRepository.findAll();
         return patients.stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto update(PatientDto patientDto) {
        findPatientById(patientDto.getId());
        Patient patient = patientMapper.toEntity(patientDto);
        addressService.update(patientDto.getAddress());
        patient.setUser(findUserByUsername(patientDto.getUser().getUsername()));
        patient.setGender(findGenderByName(patientDto.getGender().getName()));
        Patient updatedPatient = patientRepository.save(patient);
        return patientMapper.toDto(updatedPatient);
    }

    @Override
    public PatientDto delete(PatientDto patientDto) {
        Patient patient = findPatientById(patientDto.getId());
        patientRepository.delete(patient);
        return patientMapper.toDto(patient);
    }

    @Override
    public PatientDto findByUsername(String username) {
        User user = findUserByUsername(username);
        Patient patient = findByUser(user);
        return patientMapper.toDto(patient);
    }
    @Override
    public String generateCsv() {
        List<Patient> patients = patientRepository.findAll();
        try (FileWriter writer = new FileWriter("patients.csv")) {
            writer.append("phone,first_name,last_name\n");
            for (Patient patient : patients) {
                writer.append(patient.getPhoneNumber()).append(",")
                        .append(patient.getFirstName()).append(",")
                        .append(patient.getLastName()).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "patients.csv";
    }
    private Patient findPatientById(Long id){
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
    }
    private User findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
    private Patient findByUser(User user){
        return patientRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
    }
    private Gender findGenderByName(String name) {
        return genderRepository.findByName(EGender.valueOf(name))
                .orElseThrow(() -> new EntityNotFoundException("Gender not found"));
    }
}
