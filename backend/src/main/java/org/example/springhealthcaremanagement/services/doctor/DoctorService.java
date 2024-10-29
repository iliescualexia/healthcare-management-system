package org.example.springhealthcaremanagement.services.doctor;

import org.example.springhealthcaremanagement.dtos.doctor.DoctorDto;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorRequestDto;

import java.util.List;

public interface DoctorService {
    List<DoctorDto> findAll();
    DoctorDto save(DoctorRequestDto doctorRequestDto);
    DoctorDto update(DoctorDto doctorDto);
    DoctorDto delete(DoctorDto doctorDto);
    DoctorDto findByUsername(String username);
    List<DoctorDto> findDoctorsWithSpecialization(String specialization);
}
