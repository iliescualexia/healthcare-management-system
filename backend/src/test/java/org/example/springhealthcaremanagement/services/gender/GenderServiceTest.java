package org.example.springhealthcaremanagement.services.gender;

import org.example.springhealthcaremanagement.core.SpringUnitBaseTest;
import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.entities.gender.Gender;
import org.example.springhealthcaremanagement.mappers.GenderMapper;
import org.example.springhealthcaremanagement.repositories.gender.GenderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GenderServiceTest extends SpringUnitBaseTest {

    @InjectMocks
    private GenderServiceImpl genderService;

    @Mock
    private GenderRepository genderRepository;

    @Mock
    private GenderMapper genderMapper;

    @Test
    void findAll() {
        Gender gender = new Gender();
        GenderDto genderDto = new GenderDto();

        when(genderRepository.findAll()).thenReturn(Collections.singletonList(gender));
        when(genderMapper.toDto(gender)).thenReturn(genderDto);

        List<GenderDto> result = genderService.findAll();

        assertEquals(Collections.singletonList(genderDto), result);
    }
}