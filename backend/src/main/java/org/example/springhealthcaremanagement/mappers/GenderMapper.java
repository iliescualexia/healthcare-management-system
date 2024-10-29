package org.example.springhealthcaremanagement.mappers;

import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.entities.address.Address;
import org.example.springhealthcaremanagement.entities.gender.EGender;
import org.example.springhealthcaremanagement.entities.gender.Gender;
import org.example.springhealthcaremanagement.entities.role.ERole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenderMapper {
    @Mapping(source = "name", target = "name")
    GenderDto toDto(Gender gender);
    @Mapping(source = "name", target = "name")
    Gender toEntity(GenderDto genderDto);
    default String map(EGender eGender) {
        return eGender != null ? eGender.name() : null;
    }

    default EGender map(String eGender) {
        return eGender != null ? EGender.valueOf(eGender) : null;
    }
}
