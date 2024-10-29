package org.example.springhealthcaremanagement.services.appointment;

import org.example.springhealthcaremanagement.core.SpringUnitBaseTest;
import org.example.springhealthcaremanagement.dtos.appointmentstatus.AppointmentStatusDto;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientDto;
import org.example.springhealthcaremanagement.entities.appointmentstatus.AppointmentStatus;
import org.example.springhealthcaremanagement.entities.appointmentstatus.EAppointmentStatus;
import org.example.springhealthcaremanagement.entities.doctor.Doctor;
import org.example.springhealthcaremanagement.entities.patient.Patient;
import org.example.springhealthcaremanagement.repositories.appointment.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

import org.example.springhealthcaremanagement.core.SpringUnitBaseTest;
import org.example.springhealthcaremanagement.dtos.appointment.AppointmentDto;
import org.example.springhealthcaremanagement.dtos.appointment.AppointmentRequestDto;
import org.example.springhealthcaremanagement.entities.appointment.Appointment;
import org.example.springhealthcaremanagement.mappers.AppointmentMapper;
import org.example.springhealthcaremanagement.repositories.appointment.AppointmentRepository;
import org.example.springhealthcaremanagement.repositories.appointmentstatus.AppointmentStatusRepository;
import org.example.springhealthcaremanagement.repositories.doctor.DoctorRepository;
import org.example.springhealthcaremanagement.repositories.patient.PatientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AppointmentServiceImplTest extends SpringUnitBaseTest {
    @InjectMocks
    private AppointmentServiceImpl appointmentService;
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private AppointmentMapper appointmentMapper;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private AppointmentStatusRepository appointmentStatusRepository;

    @Test
    void save() {
        AppointmentRequestDto requestDto = AppointmentRequestDto
                .builder()
                .patient(PatientDto.builder().id(1L).build())
                .doctor(DoctorDto.builder().id(1L).build())
                .status(AppointmentStatusDto.builder().name("PENDING").build())
                .build();
        Appointment appointment = Appointment
                .builder()
                .patient(Patient.builder().id(1L).build())
                .doctor(Doctor.builder().id(1L).build())
                .status(AppointmentStatus.builder().name(EAppointmentStatus.valueOf("PENDING")).build())
                .build();
        Appointment savedAppointment =Appointment
                .builder()
                .id(1L)
                .patient(Patient.builder().id(1L).build())
                .doctor(Doctor.builder().id(1L).build())
                .status(AppointmentStatus.builder().name(EAppointmentStatus.valueOf("PENDING")).build())
                .build();
        AppointmentDto responseDto = AppointmentDto
                .builder()
                .id(1L)
                .patient(PatientDto.builder().id(1L).build())
                .doctor(DoctorDto.builder().id(1L).build())
                .status(AppointmentStatusDto.builder().name("PENDING").build())
                .build();
        Patient patient = Patient.builder().id(1L).build();
        Doctor doctor = Doctor.builder().id(1L).build();
        when(appointmentStatusRepository.findByName(any())).thenReturn(Optional.of(AppointmentStatus.builder().name(EAppointmentStatus.PENDING).build()));
        when(appointmentMapper.requestDtoToEntity(requestDto)).thenReturn(appointment);
        when(patientRepository.findById(any())).thenReturn(Optional.of(patient));
        when(doctorRepository.findById(any())).thenReturn(Optional.of(doctor));
        when(appointmentRepository.save(appointment)).thenReturn(savedAppointment);
        when(appointmentMapper.toDto(savedAppointment)).thenReturn(responseDto);

        AppointmentDto result = appointmentService.save(requestDto);

        assertEquals(responseDto.getId(), result.getId());
    }

    @Test
    void findAll() {
        Appointment appointment = new Appointment();
        AppointmentDto responseDto = new AppointmentDto();

        when(appointmentRepository.findAll()).thenReturn(Collections.singletonList(appointment));
        when(appointmentMapper.toDto(appointment)).thenReturn(responseDto);

        List<AppointmentDto> result = appointmentService.findAll();

        assertEquals(Collections.singletonList(responseDto), result);
    }

    @Test
    void update() {
        AppointmentDto requestDto = new AppointmentDto();
        Appointment appointment = new Appointment();
        Appointment updatedAppointment = new Appointment();
        AppointmentDto responseDto = new AppointmentDto();

        when(appointmentRepository.findById(requestDto.getId())).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(appointment)).thenReturn(updatedAppointment);
        when(appointmentMapper.toDto(updatedAppointment)).thenReturn(responseDto);

        AppointmentDto result = appointmentService.update(requestDto);

        assertEquals(responseDto, result);
    }

    @Test
    void delete() {
        AppointmentDto requestDto = new AppointmentDto();
        Appointment appointment = new Appointment();
        AppointmentDto responseDto = new AppointmentDto();

        when(appointmentRepository.findById(requestDto.getId())).thenReturn(Optional.of(appointment));
        when(appointmentMapper.toDto(appointment)).thenReturn(responseDto);

        AppointmentDto result = appointmentService.delete(requestDto);

        assertEquals(responseDto, result);
        verify(appointmentRepository).delete(appointment);
    }
}