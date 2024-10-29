package org.example.springhealthcaremanagement.mappers;

import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.address.AddressRequestDto;
import org.example.springhealthcaremanagement.entities.address.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toDto(Address address);

    Address toEntity(AddressDto addressDto);

    AddressRequestDto entityToRequestDTO(Address address);
    Address requestDTOTOEntity(AddressRequestDto addressRequestDto);

}