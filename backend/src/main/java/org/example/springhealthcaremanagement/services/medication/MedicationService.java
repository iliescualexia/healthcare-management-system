package org.example.springhealthcaremanagement.services.medication;

import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.address.AddressRequestDto;
import org.example.springhealthcaremanagement.dtos.medication.MedicationDto;
import org.example.springhealthcaremanagement.dtos.medication.MedicationRequestDto;
import org.example.springhealthcaremanagement.entities.medication.Medication;

import java.util.List;

public interface MedicationService {
    MedicationDto save(MedicationRequestDto medicationRequestDto);
    MedicationDto update(MedicationDto medicationDto);
    MedicationDto delete(MedicationDto medicationDto);
    List<MedicationDto> findAll();
}
