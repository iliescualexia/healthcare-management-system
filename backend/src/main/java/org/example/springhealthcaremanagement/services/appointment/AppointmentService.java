package org.example.springhealthcaremanagement.services.appointment;

import org.example.springhealthcaremanagement.dtos.appointment.AppointmentDto;
import org.example.springhealthcaremanagement.dtos.appointment.AppointmentRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {
    AppointmentDto save(AppointmentRequestDto appointmentRequestDto);
    List<AppointmentDto> findAll();
    AppointmentDto update(AppointmentDto appointmentDto);
    AppointmentDto delete(AppointmentDto appointmentDto);
    List<AppointmentDto> findByDoctorId(Long doctorId);
    List<AppointmentDto>findUpcomingAppointmentsForPatient(String username);
    List<AppointmentDto> findPastAppointmentsForPatient(String username);
    ResponseEntity<AppointmentDto> cancelAppointment(Long appointmentId);
    ResponseEntity<AppointmentDto> confirmAppointment(Long appointmentId);
    ResponseEntity<AppointmentDto> refuseAppointment(Long appointmentId);
    List<AppointmentDto> findRequestedAppointmentsForDoctor(String username);
    List<AppointmentDto> findUpcomingAppointmentsForDoctor(String username);
    List<AppointmentDto> findPastAppointmentsForDoctor(String username);

}
