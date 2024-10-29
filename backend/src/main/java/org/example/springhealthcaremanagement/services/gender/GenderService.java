package org.example.springhealthcaremanagement.services.gender;

import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.address.AddressRequestDto;
import org.example.springhealthcaremanagement.dtos.gender.GenderDto;

import java.util.List;

public interface GenderService {
    List<GenderDto> findAll();
}
