package org.example.springhealthcaremanagement.controllers;

import org.example.springhealthcaremanagement.core.SpringControllerBaseTest;
import org.example.springhealthcaremanagement.dtos.specialization.SpecializationDto;
import org.example.springhealthcaremanagement.services.specialization.SpecializationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.example.springhealthcaremanagement.globals.UrlMapping.SPECIALIZATION;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SpecializationControllerTest extends SpringControllerBaseTest {
    @InjectMocks
    private SpecializationController specializationController;

    @Mock
    private SpecializationService specializationService;

    @BeforeEach
    public void setUp() {
        super.setUp();
        specializationController = new SpecializationController(specializationService);
        mvc = buildForController(specializationController);
    }

    @Test
    void findAll() throws Exception {
        SpecializationDto specialization1 = SpecializationDto.builder().name("CARDIOLOGY").build();
        SpecializationDto specialization2 = SpecializationDto.builder().name("DERMATOLOGY").build();

        List<SpecializationDto> specializationList = Arrays.asList(specialization1, specialization2);

        when(specializationService.findAll()).thenReturn(specializationList);

        ResultActions res = performGet(SPECIALIZATION);

        verify(specializationService, times(1)).findAll();

        res.andExpect(status().isOk()).andExpect(contentToBe(specializationList));
    }
}
