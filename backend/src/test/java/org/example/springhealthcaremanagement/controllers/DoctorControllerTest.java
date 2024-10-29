package org.example.springhealthcaremanagement.controllers;

import org.example.springhealthcaremanagement.core.SpringControllerBaseTest;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorDto;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorRequestDto;
import org.example.springhealthcaremanagement.dtos.specialization.SpecializationDto;
import org.example.springhealthcaremanagement.dtos.user.UserRequestDto;
import org.example.springhealthcaremanagement.entities.user.User;
import org.example.springhealthcaremanagement.services.doctor.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.example.springhealthcaremanagement.globals.UrlMapping.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DoctorControllerTest extends SpringControllerBaseTest {
    @InjectMocks
    private DoctorController doctorController;

    @Mock
    private DoctorService doctorService;

    @BeforeEach
    public void setUp() {
        super.setUp();
        doctorController = new DoctorController(doctorService);
        mvc = buildForController(doctorController);
    }

    @Test
    void create() throws Exception {
        DoctorRequestDto createRequestDto = DoctorRequestDto.builder().firstName("John Doe").build();
        DoctorDto result = DoctorDto.builder().id(1L).firstName("John Doe").build();

        when(doctorService.save(any(DoctorRequestDto.class))).thenReturn(result);

        ResultActions res = performPostWithRequestBody(DOCTOR, createRequestDto);

        verify(doctorService, times(1)).save(any(DoctorRequestDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }

    @Test
    void update() throws Exception {
        DoctorDto updateRequestDto = DoctorDto.builder().id(1L).firstName("Updated Name").build();
        DoctorDto result = DoctorDto.builder().id(1L).firstName("Updated Name").build();

        when(doctorService.update(any(DoctorDto.class))).thenReturn(result);

        ResultActions res = performPutWithRequestBody(DOCTOR, updateRequestDto);

        verify(doctorService, times(1)).update(any(DoctorDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }

    @Test
    void delete() throws Exception {
        DoctorDto deleteRequestDto = DoctorDto.builder().id(1L).firstName("John Doe").build();
        DoctorDto result = DoctorDto.builder().id(1L).firstName("John Doe").build();

        when(doctorService.delete(any(DoctorDto.class))).thenReturn(result);

        ResultActions res = performDeleteWithRequestBody(DOCTOR, deleteRequestDto);

        verify(doctorService, times(1)).delete(any(DoctorDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }

    @Test
    void findAll() throws Exception {
        DoctorDto doctor1 = DoctorDto.builder().id(1L).firstName("John Doe").build();
        DoctorDto doctor2 = DoctorDto.builder().id(2L).firstName("Jane Smith").build();

        List<DoctorDto> doctorList = Arrays.asList(doctor1, doctor2);

        when(doctorService.findAll()).thenReturn(doctorList);

        ResultActions res = performGet(DOCTOR);

        verify(doctorService, times(1)).findAll();

        res.andExpect(status().isOk()).andExpect(contentToBe(doctorList));
    }
    @Test
    void findByUsername() throws Exception {
        String username = "john_doe";
        DoctorDto doctor = DoctorDto.builder().id(1L).firstName("John Doe").user(UserRequestDto.builder().username(username).build()).build();

        when(doctorService.findByUsername(username)).thenReturn(doctor);

        ResultActions res = performGet(DOCTOR+ FIND_DOCTOR_BY_USERNAME, username);

        verify(doctorService, times(1)).findByUsername(username);

        res.andExpect(status().isOk()).andExpect(contentToBe(doctor));
    }

    @Test
    void findDoctorsWithSpecialization() throws Exception {
        String specialization = "CARDIOLOGY";
        SpecializationDto specializationDto = SpecializationDto.builder().name(specialization).build();
        Set<SpecializationDto> specializations = Set.of(specializationDto);
        DoctorDto doctor1 = DoctorDto.builder().id(1L).firstName("John Doe").specializations(specializations).build();
        DoctorDto doctor2 = DoctorDto.builder().id(2L).firstName("Jane Smith").specializations(specializations).build();

        List<DoctorDto> doctorList = Arrays.asList(doctor1, doctor2);

        when(doctorService.findDoctorsWithSpecialization(specialization)).thenReturn(doctorList);

        ResultActions res = performGet(DOCTOR + FIND_DOCTORS_WITH_SPECIALIZATION,specialization);

        verify(doctorService, times(1)).findDoctorsWithSpecialization(specialization);

        res.andExpect(status().isOk()).andExpect(contentToBe(doctorList));
    }
}
