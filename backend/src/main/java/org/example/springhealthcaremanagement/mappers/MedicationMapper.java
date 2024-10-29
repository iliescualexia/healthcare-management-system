package org.example.springhealthcaremanagement.mappers;

import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.address.AddressRequestDto;
import org.example.springhealthcaremanagement.dtos.medication.MedicationDto;
import org.example.springhealthcaremanagement.dtos.medication.MedicationRequestDto;
import org.example.springhealthcaremanagement.entities.address.Address;
import org.example.springhealthcaremanagement.entities.medication.Medication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicationMapper {
    MedicationDto toDto(Medication medication);

    Medication toEntity(MedicationDto medicationDto);

    MedicationRequestDto entityToRequestDTO(Medication medication);
    Medication requestDTOTOEntity(MedicationRequestDto medicationRequestDto);
}
