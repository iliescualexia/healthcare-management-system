package org.example.springhealthcaremanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.appointment.AppointmentDto;
import org.example.springhealthcaremanagement.dtos.appointment.AppointmentRequestDto;
import org.example.springhealthcaremanagement.entities.appointment.Appointment;
import org.example.springhealthcaremanagement.services.appointment.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.example.springhealthcaremanagement.globals.UrlMapping.*;

@RequestMapping(APPOINTMENT)
@RestController
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    @PostMapping
    public AppointmentDto create(@RequestBody @Valid AppointmentRequestDto reqDto){
        return appointmentService.save(reqDto);
    }
    @PutMapping
    public AppointmentDto update(@RequestBody @Valid AppointmentDto appointmentDto) {
        return appointmentService.update(appointmentDto);
    }
    @DeleteMapping
    public AppointmentDto delete(@RequestBody @Valid AppointmentDto appointmentDto) {
        return appointmentService.delete(appointmentDto);
    }
    @GetMapping
    public List<AppointmentDto> findAll() {
        return appointmentService.findAll();
    }

    @GetMapping(FIND_APPOINTMENTS_BY_DOCTOR_ID)
    public List<AppointmentDto> findByDoctorId(@PathVariable Long doctorId) {
        return appointmentService.findByDoctorId(doctorId);
    }
    @GetMapping(FIND_APPOINTMENTS_BY_PATIENT_ID)
    public List<AppointmentDto> findUpcomingAppointmentsForPatient(@PathVariable String username) {
        return appointmentService.findUpcomingAppointmentsForPatient(username);
    }
    @GetMapping(FIND_PAST_APPOINTMENTS_FOR_PATIENT)
    public List<AppointmentDto> findPastAppointmentsForPatient(@PathVariable String username) {
        return appointmentService.findPastAppointmentsForPatient(username);
    }
    @PutMapping(CANCEL_APPOINTMENT)
    public ResponseEntity<AppointmentDto> cancelAppointment(@PathVariable Long appointmentId) {
        return appointmentService.cancelAppointment(appointmentId);
    }
    @PutMapping(REFUSE_APPOINTMENT)
    public ResponseEntity<AppointmentDto> refuseAppointment(@PathVariable Long appointmentId) {
        return appointmentService.refuseAppointment(appointmentId);
    }
    @PutMapping(CONFIRM_APPOINTMENT)
    public ResponseEntity<AppointmentDto> confirmAppointment(@PathVariable Long appointmentId) {
        return appointmentService.confirmAppointment(appointmentId);
    }
    @GetMapping(FIND_UPCOMING_APPOINTMENTS_FOR_DOCTOR)
    public List<AppointmentDto> findUpcomingAppointmentsForDoctor(@PathVariable String username) {
        return appointmentService.findUpcomingAppointmentsForDoctor(username);
    }
    @GetMapping(FIND_PAST_APPOINTMENTS_FOR_DOCTOR)
    public List<AppointmentDto> findPastAppointmentsForDoctor(@PathVariable String username) {
        return appointmentService.findPastAppointmentsForDoctor(username);
    }
    @GetMapping(FIND_REQUESTED_APPOINTMENTS_FOR_DOCTOR)
    public List<AppointmentDto> findRequestedAppointmentsForDoctor(@PathVariable String username) {
        return appointmentService.findRequestedAppointmentsForDoctor(username);
    }
}
