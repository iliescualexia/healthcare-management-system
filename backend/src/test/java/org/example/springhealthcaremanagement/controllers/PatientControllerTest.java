package org.example.springhealthcaremanagement.controllers;

import org.example.springhealthcaremanagement.core.SpringControllerBaseTest;
import org.example.springhealthcaremanagement.dtos.patient.PatientDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientRequestDto;
import org.example.springhealthcaremanagement.dtos.user.UserDto;
import org.example.springhealthcaremanagement.services.patient.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.example.springhealthcaremanagement.globals.UrlMapping.FIND_PATIENT_BY_USERNAME;
import static org.example.springhealthcaremanagement.globals.UrlMapping.PATIENT;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PatientControllerTest extends SpringControllerBaseTest {
    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientService patientService;

    @BeforeEach
    public void setUp() {
        super.setUp();
        patientController = new PatientController(patientService);
        mvc = buildForController(patientController);
    }

    @Test
    void create() throws Exception {
        PatientRequestDto createRequestDto = PatientRequestDto.builder().firstName("John Doe").build();
        PatientDto result = PatientDto.builder().id(1L).firstName("John Doe").build();

        when(patientService.save(any(PatientRequestDto.class))).thenReturn(result);

        ResultActions res = performPostWithRequestBody(PATIENT, createRequestDto);

        verify(patientService, times(1)).save(any(PatientRequestDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }

    @Test
    void update() throws Exception {
        PatientDto updateRequestDto = PatientDto.builder().id(1L).firstName("Updated Patient").build();
        PatientDto result = PatientDto.builder().id(1L).firstName("Updated Patient").build();

        when(patientService.update(any(PatientDto.class))).thenReturn(result);

        ResultActions res = performPutWithRequestBody(PATIENT, updateRequestDto);

        verify(patientService, times(1)).update(any(PatientDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }


    @Test
    void findAll() throws Exception {
        PatientDto patient1 = PatientDto.builder().id(1L).firstName("John Doe").build();
        PatientDto patient2 = PatientDto.builder().id(2L).firstName("Jane Smith").build();

        List<PatientDto> patientList = Arrays.asList(patient1, patient2);

        when(patientService.findAll()).thenReturn(patientList);

        ResultActions res = performGet(PATIENT);

        verify(patientService, times(1)).findAll();

        res.andExpect(status().isOk()).andExpect(contentToBe(patientList));
    }
    @Test
    void delete() throws Exception {
        PatientDto deleteRequestDto = PatientDto.builder().id(1L).firstName("John Doe").build();
        PatientDto result = PatientDto.builder().id(1L).firstName("John Doe").build();

        when(patientService.delete(any(PatientDto.class))).thenReturn(result);

        ResultActions res = performDeleteWithRequestBody(PATIENT, deleteRequestDto);

        verify(patientService, times(1)).delete(any(PatientDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }

    @Test
    void findByUsername() throws Exception {
        String username = "john_doe";
        PatientDto result = PatientDto.builder().id(1L).firstName("John Doe").user(UserDto.builder().username(username).build()).build();

        when(patientService.findByUsername(username)).thenReturn(result);

        ResultActions res = performGet(PATIENT + FIND_PATIENT_BY_USERNAME, username);

        verify(patientService, times(1)).findByUsername(username);

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }
}
