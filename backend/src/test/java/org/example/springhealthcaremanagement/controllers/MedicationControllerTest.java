package org.example.springhealthcaremanagement.controllers;

import org.example.springhealthcaremanagement.core.SpringControllerBaseTest;
import org.example.springhealthcaremanagement.dtos.medication.MedicationDto;
import org.example.springhealthcaremanagement.dtos.medication.MedicationRequestDto;
import org.example.springhealthcaremanagement.services.medication.MedicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.example.springhealthcaremanagement.globals.UrlMapping.MEDICATION;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MedicationControllerTest extends SpringControllerBaseTest {
    @InjectMocks
    private MedicationController medicationController;

    @Mock
    private MedicationService medicationService;

    @BeforeEach
    public void setUp() {
        super.setUp();
        medicationController = new MedicationController(medicationService);
        mvc = buildForController(medicationController);
    }

    @Test
    void create() throws Exception {
        MedicationRequestDto createRequestDto = MedicationRequestDto.builder().name("Medicine X").build();
        MedicationDto result = MedicationDto.builder().id(1L).name("Medicine X").build();

        when(medicationService.save(any(MedicationRequestDto.class))).thenReturn(result);

        ResultActions res = performPostWithRequestBody(MEDICATION, createRequestDto);

        verify(medicationService, times(1)).save(any(MedicationRequestDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }

    @Test
    void update() throws Exception {
        MedicationDto updateRequestDto = MedicationDto.builder().id(1L).name("Updated Medicine").build();
        MedicationDto result = MedicationDto.builder().id(1L).name("Updated Medicine").build();

        when(medicationService.update(any(MedicationDto.class))).thenReturn(result);

        ResultActions res = performPutWithRequestBody(MEDICATION, updateRequestDto);

        verify(medicationService, times(1)).update(any(MedicationDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }

    @Test
    void findAll() throws Exception {
        MedicationDto medication1 = MedicationDto.builder().id(1L).name("Medicine X").build();
        MedicationDto medication2 = MedicationDto.builder().id(2L).name("Medicine Y").build();

        List<MedicationDto> medicationList = Arrays.asList(medication1, medication2);

        when(medicationService.findAll()).thenReturn(medicationList);

        ResultActions res = performGet(MEDICATION);

        verify(medicationService, times(1)).findAll();

        res.andExpect(status().isOk()).andExpect(contentToBe(medicationList));
    }
    @Test
    void delete() throws Exception {
        MedicationDto deleteRequestDto = MedicationDto.builder().id(1L).name("Medicine X").build();
        MedicationDto result = MedicationDto.builder().id(1L).name("Medicine X").build();

        when(medicationService.delete(any(MedicationDto.class))).thenReturn(result);

        ResultActions res = performDeleteWithRequestBody(MEDICATION, deleteRequestDto);

        verify(medicationService, times(1)).delete(any(MedicationDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }
}
