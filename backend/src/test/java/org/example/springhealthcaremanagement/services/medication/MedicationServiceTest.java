package org.example.springhealthcaremanagement.services.medication;

import org.example.springhealthcaremanagement.core.SpringUnitBaseTest;
import org.example.springhealthcaremanagement.dtos.medication.MedicationDto;
import org.example.springhealthcaremanagement.dtos.medication.MedicationRequestDto;
import org.example.springhealthcaremanagement.entities.medication.Medication;
import org.example.springhealthcaremanagement.mappers.MedicationMapper;
import org.example.springhealthcaremanagement.repositories.medication.MedicationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MedicationServiceTest extends SpringUnitBaseTest {
    @InjectMocks
    private MedicationServiceImpl medicationService;

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private MedicationMapper medicationMapper;

    @Test
    void save() {
        // Setup
        MedicationRequestDto requestDto = new MedicationRequestDto();
        requestDto.setName("Aspirin");
        requestDto.setDescription("Painkiller");

        Medication medication = Medication.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .build();

        Medication savedMedication = Medication.builder()
                .id(1L)
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .build();

        MedicationDto responseDto = MedicationDto.builder()
                .id(1L)
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .build();

        when(medicationMapper.requestDTOTOEntity(requestDto)).thenReturn(medication);
        when(medicationRepository.save(medication)).thenReturn(savedMedication);
        when(medicationMapper.toDto(savedMedication)).thenReturn(responseDto);

        MedicationDto result = medicationService.save(requestDto);

        assertEquals(responseDto, result);
    }


    @Test
    void update() {
        MedicationDto requestDto = new MedicationDto();
        requestDto.setId(1L);
        Medication medication = Medication.builder().id(1L).build();
        Medication updatedMedication = Medication.builder().id(1L).build();
        MedicationDto responseDto = MedicationDto.builder().id(1L).build();

        when(medicationRepository.findById(requestDto.getId())).thenReturn(Optional.of(medication));
        when(medicationRepository.save(any())).thenReturn(updatedMedication);
        when(medicationMapper.toDto(updatedMedication)).thenReturn(responseDto);

        MedicationDto result = medicationService.update(requestDto);
        assertEquals(responseDto, result);
    }



    @Test
    void delete() {
        MedicationDto requestDto = new MedicationDto();
        requestDto.setId(1L);
        Medication medication = Medication.builder().id(1L).build();
        MedicationDto responseDto = MedicationDto.builder().id(1L).build();

        when(medicationRepository.findById(requestDto.getId())).thenReturn(Optional.of(medication));
        when(medicationMapper.toDto(medication)).thenReturn(responseDto);

        MedicationDto result = medicationService.delete(requestDto);
        assertEquals(responseDto.getId(), result.getId());
        verify(medicationRepository).delete(any());
    }

    @Test
    void findAll() {
        Medication medication = new Medication();
        MedicationDto responseDto = new MedicationDto();

        when(medicationRepository.findAll()).thenReturn(Collections.singletonList(medication));
        when(medicationMapper.toDto(medication)).thenReturn(responseDto);

        List<MedicationDto> result = medicationService.findAll();

        assertEquals(Collections.singletonList(responseDto), result);
    }
}