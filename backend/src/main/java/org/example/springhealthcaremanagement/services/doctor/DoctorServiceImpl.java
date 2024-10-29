package org.example.springhealthcaremanagement.services.doctor;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorDto;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorRequestDto;
import org.example.springhealthcaremanagement.dtos.specialization.SpecializationDto;
import org.example.springhealthcaremanagement.dtos.user.UserDto;
import org.example.springhealthcaremanagement.dtos.user.UserRequestDto;
import org.example.springhealthcaremanagement.entities.doctor.Doctor;
import org.example.springhealthcaremanagement.entities.gender.EGender;
import org.example.springhealthcaremanagement.entities.gender.Gender;
import org.example.springhealthcaremanagement.entities.specialization.ESpecialization;
import org.example.springhealthcaremanagement.entities.specialization.Specialization;
import org.example.springhealthcaremanagement.entities.user.User;
import org.example.springhealthcaremanagement.exception.EntityNotFoundException;
import org.example.springhealthcaremanagement.mappers.DoctorMapper;
import org.example.springhealthcaremanagement.mappers.UserMapper;
import org.example.springhealthcaremanagement.repositories.doctor.DoctorRepository;
import org.example.springhealthcaremanagement.repositories.gender.GenderRepository;
import org.example.springhealthcaremanagement.repositories.specialization.SpecializationRepository;
import org.example.springhealthcaremanagement.repositories.user.UserRepository;
import org.example.springhealthcaremanagement.services.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final PasswordEncoder encoder;
    private final SpecializationRepository specializationRepository;
    private final UserService userService;

    @Override
    public List<DoctorDto> findAll() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public DoctorDto save(DoctorRequestDto doctorRequestDto) {
        Doctor doctor = doctorMapper.requestDtoToEntity(doctorRequestDto);
        doctor.setUser(findUserByUsername(doctorRequestDto.getUser().getUsername()));
        doctor.setGender(findGenderByName(doctorRequestDto.getGender().getName()));
        Set<Specialization> specializations = findSpecializations(doctorRequestDto.getSpecializations());
        doctor.setSpecializations(specializations);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDto(savedDoctor);
    }

    @Override
    public DoctorDto update(DoctorDto doctorDto) {
        findDoctorById(doctorDto.getId());
        Doctor doctor = doctorMapper.toEntity(doctorDto);
        doctor.setGender(findGenderByName(doctorDto.getGender().getName()));
        doctor.setSpecializations(findSpecializations(doctorDto.getSpecializations()));
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDto(updatedDoctor);
    }

    @Override
    public DoctorDto delete(DoctorDto doctorDto) {
        Doctor doctor = findDoctorById(doctorDto.getId());
        doctorRepository.delete(doctor);
        return doctorDto;
    }

    @Override
    public DoctorDto findByUsername(String username) {
        User user = findUserByUsername(username);
        Doctor doctor = findByUser(user);
        return doctorMapper.toDto(doctor);
    }

    @Override
    public List<DoctorDto> findDoctorsWithSpecialization(String specialization) {
        Specialization specializationEntity = findSpecializationByName(specialization);
        List<Doctor> doctorList = doctorRepository.findDoctorBySpecializationsContaining(specializationEntity);
        return doctorList.stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
    private Doctor findByUser(User user) {
        return doctorRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
    }
    private Gender findGenderByName(String name) {
        return genderRepository.findByName(EGender.valueOf(name))
                .orElseThrow(() -> new EntityNotFoundException("Gender not found"));
    }
    private Set<Specialization> findSpecializations(Set<SpecializationDto> specializationDtos) {
        return specializationDtos.stream()
                .map(specializationDto -> specializationRepository.findByName(ESpecialization.valueOf(specializationDto.getName()))
                        .orElseThrow(() -> new EntityNotFoundException("Specialization not found: " + specializationDto.getName())))
                .collect(Collectors.toSet());
    }
    private Specialization findSpecializationByName(String name){
        return specializationRepository.findByName(ESpecialization.valueOf(name))
                .orElseThrow(() -> new EntityNotFoundException("Specialization not found"));
    }

    private Doctor findDoctorById(Long id){
        return doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
    }
}
