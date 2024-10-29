package org.example.springhealthcaremanagement.services.address;

import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.address.AddressRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    AddressDto save(AddressRequestDto addressRequestDto);
    AddressDto update(AddressDto addressDto);
    AddressDto delete(AddressDto addressDto);
    List<AddressDto> findAll();
    List<AddressDto> findByCity (String city);
    ResponseEntity<AddressDto> findById (long id);

}
