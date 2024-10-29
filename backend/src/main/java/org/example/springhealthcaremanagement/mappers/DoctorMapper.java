package org.example.springhealthcaremanagement.mappers;

import org.example.springhealthcaremanagement.dtos.doctor.DoctorDto;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorRequestDto;
import org.example.springhealthcaremanagement.dtos.user.UserDto;
import org.example.springhealthcaremanagement.entities.doctor.Doctor;
import org.example.springhealthcaremanagement.entities.user.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {UserMapper.class, GenderMapper.class, SpecializationMapper.class})
public interface DoctorMapper {
    DoctorDto toDto(Doctor doctor);

    Doctor toEntity(DoctorDto doctorDto);

    Doctor requestDtoToEntity(DoctorRequestDto doctorRequestDto);
}