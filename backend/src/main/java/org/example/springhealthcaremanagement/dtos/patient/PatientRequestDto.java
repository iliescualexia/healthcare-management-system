package org.example.springhealthcaremanagement.dtos.patient;

import jakarta.persistence.*;
import lombok.*;
import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.address.AddressRequestDto;
import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.dtos.security.SignupRequest;
import org.example.springhealthcaremanagement.dtos.user.UserDto;
import org.example.springhealthcaremanagement.entities.address.Address;
import org.example.springhealthcaremanagement.entities.gender.Gender;
import org.example.springhealthcaremanagement.entities.user.User;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDto {

    private UserDto user;
    private String firstName;
    private String lastName;
    private GenderDto gender;
    private AddressDto address;
    private String phoneNumber;
}
