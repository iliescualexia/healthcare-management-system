package org.example.springhealthcaremanagement.services.user;

import org.example.springhealthcaremanagement.dtos.doctor.DoctorDto;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorRequestDto;
import org.example.springhealthcaremanagement.dtos.user.UserDto;
import org.example.springhealthcaremanagement.dtos.user.UserRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService{
    ResponseEntity<UserRequestDto> findByUsername(String username);
    UserDto update(UserRequestDto userRequestDto);
}
