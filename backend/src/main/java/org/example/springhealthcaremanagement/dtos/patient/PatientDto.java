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
public class PatientDto {
    private Long id;
    private UserDto user;
    private String firstName;
    private String lastName;
    private GenderDto gender;
    private AddressDto address;
    private String phoneNumber;
}
