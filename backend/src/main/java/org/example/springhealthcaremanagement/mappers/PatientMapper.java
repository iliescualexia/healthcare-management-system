package org.example.springhealthcaremanagement.mappers;

import org.example.springhealthcaremanagement.dtos.doctor.DoctorDto;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorRequestDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientRequestDto;
import org.example.springhealthcaremanagement.entities.doctor.Doctor;
import org.example.springhealthcaremanagement.entities.patient.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, GenderMapper.class})
public interface PatientMapper {
    PatientDto toDto(Patient patient);

    Patient toEntity(PatientDto patientDto);

    Patient requestDtoToEntity(PatientRequestDto patientRequestDto);
}
