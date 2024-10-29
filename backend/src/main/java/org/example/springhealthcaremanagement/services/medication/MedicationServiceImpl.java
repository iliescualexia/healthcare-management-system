package org.example.springhealthcaremanagement.services.medication;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.medication.MedicationDto;
import org.example.springhealthcaremanagement.dtos.medication.MedicationRequestDto;
import org.example.springhealthcaremanagement.entities.address.Address;
import org.example.springhealthcaremanagement.entities.medication.Medication;
import org.example.springhealthcaremanagement.exception.EntityNotFoundException;
import org.example.springhealthcaremanagement.mappers.AddressMapper;
import org.example.springhealthcaremanagement.mappers.MedicationMapper;
import org.example.springhealthcaremanagement.repositories.address.AddressRepository;
import org.example.springhealthcaremanagement.repositories.medication.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService{
    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;

    @Override
    public MedicationDto save(MedicationRequestDto medicationRequestDto) {
        Medication savedMedication =  medicationRepository.save(medicationMapper.requestDTOTOEntity(medicationRequestDto));
        return medicationMapper.toDto(savedMedication);
    }

    @Override
    public MedicationDto update(MedicationDto medicationDto) {
        findMedicationById(medicationDto.getId());

        Medication updatedMedication = medicationRepository.save(medicationMapper.toEntity(medicationDto));
        return medicationMapper.toDto(updatedMedication);
    }

    @Override
    public MedicationDto delete(MedicationDto medicationDto) {
        findMedicationById(medicationDto.getId());
        medicationRepository.delete(medicationMapper.toEntity(medicationDto));
        return medicationDto;
    }

    @Override
    public List<MedicationDto> findAll() {
        List<Medication> medications = medicationRepository.findAll();
        return medications.stream()
                .map(medicationMapper::toDto)
                .collect(Collectors.toList());
    }
    private Medication findMedicationById(Long id) {
        return medicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medication not found"));
    }
}
