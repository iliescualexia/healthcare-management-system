package org.example.springhealthcaremanagement.controllers;

import org.example.springhealthcaremanagement.core.SpringControllerBaseTest;
import org.example.springhealthcaremanagement.dtos.medicalrecord.MedicalRecordDto;
import org.example.springhealthcaremanagement.dtos.medicalrecord.MedicalRecordRequestDto;
import org.example.springhealthcaremanagement.services.medicalrecord.MedicalRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.example.springhealthcaremanagement.globals.UrlMapping.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MedicalRecordControllerTest extends SpringControllerBaseTest {
    @InjectMocks
    private MedicalRecordController medicalRecordController;

    @Mock
    private MedicalRecordService medicalRecordService;

    @BeforeEach
    public void setUp() {
        super.setUp();
        medicalRecordController = new MedicalRecordController(medicalRecordService);
        mvc = buildForController(medicalRecordController);
    }

    @Test
    void create() throws Exception {
        MedicalRecordRequestDto createRequestDto = MedicalRecordRequestDto.builder().diagnosis("Diagnosis").build();
        MedicalRecordDto result = MedicalRecordDto.builder().id(1L).diagnosis("Diagnosis").build();

        when(medicalRecordService.save(any(MedicalRecordRequestDto.class))).thenReturn(result);

        ResultActions res = performPostWithRequestBody(MEDICAL_RECORD, createRequestDto);

        verify(medicalRecordService, times(1)).save(any(MedicalRecordRequestDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }

    @Test
    void update() throws Exception {
        MedicalRecordDto updateRequestDto = MedicalRecordDto.builder().id(1L).diagnosis("Updated Diagnosis").build();
        MedicalRecordDto result = MedicalRecordDto.builder().id(1L).diagnosis("Updated Diagnosis").build();

        when(medicalRecordService.update(any(MedicalRecordDto.class))).thenReturn(result);

        ResultActions res = performPutWithRequestBody(MEDICAL_RECORD, updateRequestDto);

        verify(medicalRecordService, times(1)).update(any(MedicalRecordDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }


    @Test
    void findAll() throws Exception {
        MedicalRecordDto record1 = MedicalRecordDto.builder().id(1L).diagnosis("Record 1").build();
        MedicalRecordDto record2 = MedicalRecordDto.builder().id(2L).diagnosis("Record 2").build();

        List<MedicalRecordDto> recordList = Arrays.asList(record1, record2);

        when(medicalRecordService.findAll()).thenReturn(recordList);

        ResultActions res = performGet(MEDICAL_RECORD);

        verify(medicalRecordService, times(1)).findAll();

        res.andExpect(status().isOk()).andExpect(contentToBe(recordList));
    }
    @Test
    void delete() throws Exception {
        MedicalRecordDto deleteRequestDto = MedicalRecordDto.builder().id(1L).diagnosis("Diagnosis").build();
        MedicalRecordDto result = MedicalRecordDto.builder().id(1L).diagnosis("Diagnosis").build();

        when(medicalRecordService.delete(any(MedicalRecordDto.class))).thenReturn(result);

        ResultActions res = performDeleteWithRequestBody(MEDICAL_RECORD, deleteRequestDto);

        verify(medicalRecordService, times(1)).delete(any(MedicalRecordDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }

    @Test
    void findAllByDoctor() throws Exception {
        String doctorUsername = "doctor123";
        MedicalRecordDto record1 = MedicalRecordDto.builder().id(1L).diagnosis("Record 1").build();
        MedicalRecordDto record2 = MedicalRecordDto.builder().id(2L).diagnosis("Record 2").build();

        List<MedicalRecordDto> recordList = Arrays.asList(record1, record2);

        when(medicalRecordService.findAllByDoctor(doctorUsername)).thenReturn(recordList);

        ResultActions res = performGet(MEDICAL_RECORD + FIND_MEDICAL_RECORDS_BY_DOCTOR_USERNAME,doctorUsername);

        verify(medicalRecordService, times(1)).findAllByDoctor(doctorUsername);

        res.andExpect(status().isOk()).andExpect(contentToBe(recordList));
    }

    @Test
    void findAllByPatient() throws Exception {
        String patientUsername = "patient123";
        MedicalRecordDto record1 = MedicalRecordDto.builder().id(1L).diagnosis("Record 1").build();
        MedicalRecordDto record2 = MedicalRecordDto.builder().id(2L).diagnosis("Record 2").build();

        List<MedicalRecordDto> recordList = Arrays.asList(record1, record2);

        when(medicalRecordService.findAllByPatient(patientUsername)).thenReturn(recordList);

        ResultActions res = performGet(MEDICAL_RECORD+ FIND_MEDICAL_RECORDS_BY_PATIENT_USERNAME, patientUsername);

        verify(medicalRecordService, times(1)).findAllByPatient(patientUsername);

        res.andExpect(status().isOk()).andExpect(contentToBe(recordList));
    }
}
