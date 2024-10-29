package org.example.springhealthcaremanagement.dtos.patient;

import lombok.*;
import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.dtos.user.UserDto;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegisterDto {

    private String username;
    private String email;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String gender;
    private String streetAddress;
    private String city;
    private String county;
    private String country;
    private String postalCode;
    private String phoneNumber;
}
