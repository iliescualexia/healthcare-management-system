package org.example.springhealthcaremanagement.services.medicalrecord;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.medicalrecord.MedicalRecordDto;
import org.example.springhealthcaremanagement.dtos.medicalrecord.MedicalRecordRequestDto;
import org.example.springhealthcaremanagement.entities.doctor.Doctor;
import org.example.springhealthcaremanagement.entities.medicalrecord.MedicalRecord;
import org.example.springhealthcaremanagement.entities.medication.Medication;
import org.example.springhealthcaremanagement.entities.patient.Patient;
import org.example.springhealthcaremanagement.entities.specialization.ESpecialization;
import org.example.springhealthcaremanagement.entities.user.User;
import org.example.springhealthcaremanagement.exception.EntityNotFoundException;
import org.example.springhealthcaremanagement.mappers.MedicalRecordMapper;
import org.example.springhealthcaremanagement.repositories.doctor.DoctorRepository;
import org.example.springhealthcaremanagement.repositories.medicalrecord.MedicalRecordRepository;
import org.example.springhealthcaremanagement.repositories.medication.MedicationRepository;
import org.example.springhealthcaremanagement.repositories.patient.PatientRepository;
import org.example.springhealthcaremanagement.repositories.user.UserRepository;
import org.example.springhealthcaremanagement.services.doctor.DoctorService;
import org.example.springhealthcaremanagement.services.patient.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService{
    private final MedicalRecordRepository medicalRecordRepository;
    private final MedicalRecordMapper medicalRecordMapper;
    private final MedicationRepository medicationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    @Override
    public MedicalRecordDto save(MedicalRecordRequestDto medicalRecordRequestDto) {
        MedicalRecord medicalRecord = medicalRecordMapper.requestDtoToEntity(medicalRecordRequestDto);
        User userDoctor = findUserByUsername(medicalRecordRequestDto.getDoctorUsername());
        medicalRecord.setDoctor(findDoctorByUser(userDoctor));
        User userPatient = findUserByUsername(medicalRecordRequestDto.getPatientUsername());
        medicalRecord.setPatient(findPatientByUser(userPatient));
        List<Medication> medications = medicationRepository.saveAll(medicalRecord.getPrescribedMedication());
        medicalRecord.setPrescribedMedication(medications);
        MedicalRecord savedMedicalRecord = medicalRecordRepository.save(medicalRecord);
        return medicalRecordMapper.toDto(savedMedicalRecord);
    }

    @Override
    public MedicalRecordDto update(MedicalRecordDto medicalRecordDto) {
        findMedicalRecordById(medicalRecordDto.getId());
        MedicalRecord medicalRecord = medicalRecordMapper.toEntity(medicalRecordDto);
        MedicalRecord updatedMedicalRecord = medicalRecordRepository.save(medicalRecord);
        return medicalRecordMapper.toDto(updatedMedicalRecord);
    }

    @Override
    public MedicalRecordDto delete(MedicalRecordDto medicalRecordDto) {
        findMedicalRecordById(medicalRecordDto.getId());
        medicalRecordRepository.delete(medicalRecordMapper.toEntity(medicalRecordDto));
        return medicalRecordDto;
    }

    @Override
    public List<MedicalRecordDto> findAll() {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        return medicalRecords.stream()
                .map(medicalRecordMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalRecordDto> findAllByDoctor(String doctorUsername) {
        User userDoctor = findUserByUsername(doctorUsername);
        Doctor doctor = findDoctorByUser(userDoctor);
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllByDoctor(doctor);
        return medicalRecords.stream()
                .map(medicalRecordMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalRecordDto> findAllByPatient(String patientUsername) {
        User userPatient = findUserByUsername(patientUsername);
        Patient patient = findPatientByUser(userPatient);
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAllByPatient(patient);
        return medicalRecords.stream()
                .map(medicalRecordMapper::toDto)
                .collect(Collectors.toList());
    }
    private MedicalRecord findMedicalRecordById(Long id){
        return medicalRecordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medical Record not found"));
    }

    private User findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
    private Patient findPatientByUser(User user){
        return patientRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
    }
    private Doctor findDoctorByUser(User user){
        return doctorRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
    }
}
