package org.example.springhealthcaremanagement.services.specialization;

import org.example.springhealthcaremanagement.core.SpringUnitBaseTest;
import org.example.springhealthcaremanagement.dtos.specialization.SpecializationDto;
import org.example.springhealthcaremanagement.entities.specialization.Specialization;
import org.example.springhealthcaremanagement.mappers.SpecializationMapper;
import org.example.springhealthcaremanagement.repositories.specialization.SpecializationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SpecializationServiceTest extends SpringUnitBaseTest {
    @InjectMocks
    private SpecializationServiceImpl specializationService;
    @Mock
    private SpecializationRepository specializationRepository;
    @Mock
    private SpecializationMapper specializationMapper;

    @Test
    void findAll() {
        Specialization specialization = new Specialization();
        SpecializationDto specializationDto = new SpecializationDto();

        when(specializationRepository.findAll()).thenReturn(Collections.singletonList(specialization));
        when(specializationMapper.toDto(specialization)).thenReturn(specializationDto);

        List<SpecializationDto> result = specializationService.findAll();
        assertEquals(Collections.singletonList(specializationDto), result);
    }
}