package org.example.springhealthcaremanagement.dtos.doctor;

import lombok.*;
import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.dtos.specialization.SpecializationDto;
import org.example.springhealthcaremanagement.dtos.user.UserDto;

import java.util.HashSet;
import java.util.Set;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRegisterDto {
    private String username;
    private String email;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String gender;
    private int yearsOfExperience;
    private Set<String> specializations= new HashSet<>();
    private String biography;
}
