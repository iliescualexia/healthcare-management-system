package org.example.springhealthcaremanagement.controllers;

import org.example.springhealthcaremanagement.core.SpringControllerBaseTest;
import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.services.gender.GenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.example.springhealthcaremanagement.globals.UrlMapping.GENDER;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GenderControllerTest extends SpringControllerBaseTest {
    @InjectMocks
    private GenderController genderController;

    @Mock
    private GenderService genderService;

    @BeforeEach
    public void setUp() {
        super.setUp();
        genderController = new GenderController(genderService);
        mvc = buildForController(genderController);
    }

    @Test
    void findAll() throws Exception {
        GenderDto gender1 = GenderDto.builder().name("Male").build();
        GenderDto gender2 = GenderDto.builder().name("Female").build();

        List<GenderDto> genderList = Arrays.asList(gender1, gender2);

        when(genderService.findAll()).thenReturn(genderList);

        ResultActions res = performGet(GENDER);

        verify(genderService, times(1)).findAll();

        res.andExpect(status().isOk()).andExpect(contentToBe(genderList));
    }
}
