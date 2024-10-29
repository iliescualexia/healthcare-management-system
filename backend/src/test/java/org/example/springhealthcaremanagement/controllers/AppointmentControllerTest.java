package org.example.springhealthcaremanagement.controllers;

import org.example.springhealthcaremanagement.core.SpringControllerBaseTest;
import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.address.AddressRequestDto;
import org.example.springhealthcaremanagement.dtos.appointment.AppointmentDto;
import org.example.springhealthcaremanagement.dtos.appointment.AppointmentRequestDto;
import org.example.springhealthcaremanagement.services.appointment.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.example.springhealthcaremanagement.details.UserDetailsImpl.build;
import static org.example.springhealthcaremanagement.globals.UrlMapping.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AppointmentControllerTest extends SpringControllerBaseTest {
    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentService appointmentService;
    @BeforeEach
    public void setUp() {
        super.setUp();
        appointmentController = new AppointmentController(appointmentService);
        mvc = buildForController(appointmentController);
    }
    @Test
    void create() throws Exception {
        AppointmentRequestDto createRequestDto = AppointmentRequestDto.builder().reason("name").build();
        AppointmentDto result = AppointmentDto.builder().id(1L).reason("name").build();

        when(appointmentService.save(any(AppointmentRequestDto.class))).thenReturn(result);

        ResultActions res = performPostWithRequestBody(APPOINTMENT, createRequestDto);

        verify(appointmentService, times(1)).save(any(AppointmentRequestDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }

    @Test
    void update() throws Exception {
        AppointmentDto updateRequestDto = AppointmentDto.builder().id(1L).reason("Updated Reason").build();
        AppointmentDto result = AppointmentDto.builder().id(1L).reason("Updated Reason").build();

        when(appointmentService.update(any(AppointmentDto.class))).thenReturn(result);

        ResultActions res = performPutWithRequestBody(APPOINTMENT, updateRequestDto);

        verify(appointmentService, times(1)).update(any(AppointmentDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }


    @Test
    void findAll() throws Exception {
        AppointmentDto appointment1 = AppointmentDto.builder().id(1L).reason("Checkup").build();
        AppointmentDto appointment2 = AppointmentDto.builder().id(2L).reason("Consultation").build();

        List<AppointmentDto> appointmentList = Arrays.asList(appointment1, appointment2);

        when(appointmentService.findAll()).thenReturn(appointmentList);

        ResultActions res = performGet(APPOINTMENT);

        verify(appointmentService, times(1)).findAll();

        res.andExpect(status().isOk()).andExpect(contentToBe(appointmentList));
    }

    @Test
    void delete() throws Exception {
        AppointmentDto deleteRequestDto = AppointmentDto.builder().id(1L).reason("Checkup").build();
        AppointmentDto result = AppointmentDto.builder().id(1L).reason("Checkup").build();

        when(appointmentService.delete(any(AppointmentDto.class))).thenReturn(result);

        ResultActions res = performDeleteWithRequestBody(APPOINTMENT, deleteRequestDto);

        verify(appointmentService, times(1)).delete(any(AppointmentDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }
    @Test
    void findByDoctorId() throws Exception {
        Long doctorId = 1L;
        AppointmentDto appointment1 = AppointmentDto.builder().id(1L).reason("Checkup").build();
        AppointmentDto appointment2 = AppointmentDto.builder().id(2L).reason("Consultation").build();

        List<AppointmentDto> appointmentList = Arrays.asList(appointment1, appointment2);

        when(appointmentService.findByDoctorId(doctorId)).thenReturn(appointmentList);

        ResultActions res = performGet(APPOINTMENT+FIND_APPOINTMENTS_BY_DOCTOR_ID,doctorId);

        verify(appointmentService, times(1)).findByDoctorId(doctorId);

        res.andExpect(status().isOk()).andExpect(contentToBe(appointmentList));
    }
    @Test
    void findUpcomingAppointmentsForPatient() throws Exception {
        String username = "johndoe";
        AppointmentDto appointment1 = AppointmentDto.builder().id(1L).reason("Checkup").build();
        AppointmentDto appointment2 = AppointmentDto.builder().id(2L).reason("Consultation").build();

        List<AppointmentDto> appointmentList = Arrays.asList(appointment1, appointment2);

        when(appointmentService.findUpcomingAppointmentsForPatient(username)).thenReturn(appointmentList);

        ResultActions res = performGet(APPOINTMENT + FIND_APPOINTMENTS_BY_PATIENT_ID, username);

        verify(appointmentService, times(1)).findUpcomingAppointmentsForPatient(username);

        res.andExpect(status().isOk()).andExpect(contentToBe(appointmentList));
    }

    @Test
    void findPastAppointmentsForPatient() throws Exception {
        String username = "johndoe";
        AppointmentDto appointment1 = AppointmentDto.builder().id(1L).reason("Checkup").build();
        AppointmentDto appointment2 = AppointmentDto.builder().id(2L).reason("Consultation").build();

        List<AppointmentDto> appointmentList = Arrays.asList(appointment1, appointment2);

        when(appointmentService.findPastAppointmentsForPatient(username)).thenReturn(appointmentList);

        ResultActions res = performGet(APPOINTMENT + FIND_PAST_APPOINTMENTS_FOR_PATIENT, username);

        verify(appointmentService, times(1)).findPastAppointmentsForPatient(username);

        res.andExpect(status().isOk()).andExpect(contentToBe(appointmentList));
    }
    @Test
    void cancelAppointment() throws Exception {
        Long appointmentId = 1L;
        AppointmentDto result = AppointmentDto.builder().id(appointmentId).reason("Checkup").build();

        when(appointmentService.cancelAppointment(appointmentId)).thenReturn(ResponseEntity.ok(result));

        ResultActions res = performPutWithPathVariables(APPOINTMENT + CANCEL_APPOINTMENT, appointmentId);

        verify(appointmentService, times(1)).cancelAppointment(appointmentId);

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }

    @Test
    void refuseAppointment() throws Exception {
        Long appointmentId = 1L;
        AppointmentDto result = AppointmentDto.builder().id(appointmentId).reason("Checkup").build();

        when(appointmentService.refuseAppointment(appointmentId)).thenReturn(ResponseEntity.ok(result));

        ResultActions res = performPutWithPathVariables(APPOINTMENT + REFUSE_APPOINTMENT, appointmentId);

        verify(appointmentService, times(1)).refuseAppointment(appointmentId);

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }

    @Test
    void confirmAppointment() throws Exception {
        Long appointmentId = 1L;
        AppointmentDto result = AppointmentDto.builder().id(appointmentId).reason("Checkup").build();

        when(appointmentService.confirmAppointment(appointmentId)).thenReturn(ResponseEntity.ok(result));

        ResultActions res = performPutWithPathVariables(APPOINTMENT +CONFIRM_APPOINTMENT, appointmentId);

        verify(appointmentService, times(1)).confirmAppointment(appointmentId);

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }

    @Test
    void findUpcomingAppointmentsForDoctor() throws Exception {
        String username = "drsmith";
        AppointmentDto appointment1 = AppointmentDto.builder().id(1L).reason("Checkup").build();
        AppointmentDto appointment2 = AppointmentDto.builder().id(2L).reason("Consultation").build();

        List<AppointmentDto> appointmentList = Arrays.asList(appointment1, appointment2);

        when(appointmentService.findUpcomingAppointmentsForDoctor(username)).thenReturn(appointmentList);

        ResultActions res = performGet(APPOINTMENT + FIND_UPCOMING_APPOINTMENTS_FOR_DOCTOR, username);

        verify(appointmentService, times(1)).findUpcomingAppointmentsForDoctor(username);

        res.andExpect(status().isOk()).andExpect(contentToBe(appointmentList));
    }

    @Test
    void findPastAppointmentsForDoctor() throws Exception {
        String username = "drsmith";
        AppointmentDto appointment1 = AppointmentDto.builder().id(1L).reason("Checkup").build();
        AppointmentDto appointment2 = AppointmentDto.builder().id(2L).reason("Consultation").build();

        List<AppointmentDto> appointmentList = Arrays.asList(appointment1, appointment2);

        when(appointmentService.findPastAppointmentsForDoctor(username)).thenReturn(appointmentList);

        ResultActions res = performGet(APPOINTMENT + FIND_PAST_APPOINTMENTS_FOR_DOCTOR ,username);

        verify(appointmentService, times(1)).findPastAppointmentsForDoctor(username);

        res.andExpect(status().isOk()).andExpect(contentToBe(appointmentList));
    }
    @Test
    void findRequestedAppointmentsForDoctor() throws Exception {
        String username = "drsmith";
        AppointmentDto appointment1 = AppointmentDto.builder().id(1L).reason("Checkup").build();
        AppointmentDto appointment2 = AppointmentDto.builder().id(2L).reason("Consultation").build();

        List<AppointmentDto> appointmentList = Arrays.asList(appointment1, appointment2);

        when(appointmentService.findRequestedAppointmentsForDoctor(username)).thenReturn(appointmentList);

        ResultActions res = performGet(APPOINTMENT + FIND_REQUESTED_APPOINTMENTS_FOR_DOCTOR, username);

        verify(appointmentService, times(1)).findRequestedAppointmentsForDoctor(username);
        res.andExpect(status().isOk()).andExpect(contentToBe(appointmentList));
    }

}