package org.example.springhealthcaremanagement.dtos.doctor;

import lombok.*;
import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.dtos.specialization.SpecializationDto;
import org.example.springhealthcaremanagement.dtos.user.UserDto;
import org.example.springhealthcaremanagement.dtos.user.UserRequestDto;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private Long id;
    private UserRequestDto user;
    private String firstName;
    private String lastName;
    private GenderDto gender;
    private int yearsOfExperience;
    private Set<SpecializationDto> specializations= new HashSet<>();
    private String biography;
}