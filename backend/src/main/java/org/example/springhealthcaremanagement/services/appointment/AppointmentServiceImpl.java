package org.example.springhealthcaremanagement.services.appointment;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.appointment.AppointmentDto;
import org.example.springhealthcaremanagement.dtos.appointment.AppointmentRequestDto;
import org.example.springhealthcaremanagement.entities.appointment.Appointment;
import org.example.springhealthcaremanagement.entities.appointmentstatus.AppointmentStatus;
import org.example.springhealthcaremanagement.entities.appointmentstatus.EAppointmentStatus;
import org.example.springhealthcaremanagement.entities.doctor.Doctor;
import org.example.springhealthcaremanagement.entities.patient.Patient;
import org.example.springhealthcaremanagement.entities.user.User;
import org.example.springhealthcaremanagement.exception.EntityNotFoundException;
import org.example.springhealthcaremanagement.mappers.AppointmentMapper;
import org.example.springhealthcaremanagement.repositories.appointment.AppointmentRepository;
import org.example.springhealthcaremanagement.repositories.appointmentstatus.AppointmentStatusRepository;
import org.example.springhealthcaremanagement.repositories.doctor.DoctorRepository;
import org.example.springhealthcaremanagement.repositories.patient.PatientRepository;
import org.example.springhealthcaremanagement.repositories.user.UserRepository;
import org.example.springhealthcaremanagement.services.email.EmailService;
import org.example.springhealthcaremanagement.services.email.EmailServiceImpl;
import org.example.springhealthcaremanagement.services.messages.SmsService;
import org.example.springhealthcaremanagement.services.messages.SmsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentStatusRepository appointmentStatusRepository;
    private final UserRepository userRepository;
    private final SmsService smsService;
    private final EmailService emailService;
    @Override
    public AppointmentDto save(AppointmentRequestDto appointmentRequestDto) {
        Appointment appointment = appointmentMapper.requestDtoToEntity(appointmentRequestDto);
        appointment.setPatient(findPatientById(appointment.getPatient().getId()));
        appointment.setDoctor(findDoctorById(appointment.getDoctor().getId()));
        appointment.setStatus(findAppointmentStatusByName(appointmentRequestDto.getStatus().getName()));
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDto(savedAppointment);

    }

    @Override
    public List<AppointmentDto> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDto update(AppointmentDto appointmentDto) {
        Appointment appointment = findAppointmentById(appointmentDto.getId());
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDto(updatedAppointment);
    }

    @Override
    public AppointmentDto delete(AppointmentDto appointmentDto) {
        Appointment appointment = findAppointmentById(appointmentDto.getId());
        appointmentRepository.delete(appointment);
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public List<AppointmentDto> findByDoctorId(Long doctorId) {
        Doctor doctor = findDoctorById(doctorId);
        List<Appointment> appointments = appointmentRepository.findAppointmentByDoctor(doctor);
        return appointments.stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> findUpcomingAppointmentsForPatient(String username) {
       User user = findUserByUsername(username);
       Patient patient = findByUser(user);
       List<Appointment> appointments = appointmentRepository.findAppointmentByPatientAndDateTimeAfter(patient, LocalDateTime.now());
         return appointments.stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<AppointmentDto> findPastAppointmentsForPatient(String username) {
        User user = findUserByUsername(username);
        Patient patient = findByUser(user);
        List<Appointment> appointments = appointmentRepository.findAppointmentByPatientAndDateTimeBefore(patient, LocalDateTime.now());
        return appointments.stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<AppointmentDto> cancelAppointment(Long appointmentId) {
        Appointment appointment = findAppointmentById(appointmentId);
        AppointmentStatus appointmentStatus = findAppointmentStatusByName("CANCELLED");
        appointment.setStatus(appointmentStatus);
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return ResponseEntity.ok(appointmentMapper.toDto(updatedAppointment));
    }

    @Override
    public ResponseEntity<AppointmentDto> confirmAppointment(Long appointmentId) {
        Appointment appointment = findAppointmentById(appointmentId);
        AppointmentStatus appointmentStatus = findAppointmentStatusByName("CONFIRMED");
        appointment.setStatus(appointmentStatus);
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return ResponseEntity.ok(appointmentMapper.toDto(updatedAppointment));
    }

    @Override
    public ResponseEntity<AppointmentDto> refuseAppointment(Long appointmentId) {
        Appointment appointment = findAppointmentById(appointmentId);
        AppointmentStatus appointmentStatus = findAppointmentStatusByName("REFUSED");
        appointment.setStatus(appointmentStatus);
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return ResponseEntity.ok(appointmentMapper.toDto(updatedAppointment));
    }

    @Override
    public List<AppointmentDto> findRequestedAppointmentsForDoctor(String username) {
        User user = findUserByUsername(username);
        Doctor doctor = findDoctorByUser(user);
        AppointmentStatus appointmentStatus = findAppointmentStatusByName("PENDING");
        List<Appointment> appointments = appointmentRepository.findAppointmentByDoctorAndStatus(doctor, appointmentStatus);
        return appointments.stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> findUpcomingAppointmentsForDoctor(String username) {
        User user = findUserByUsername(username);
        Doctor doctor = findDoctorByUser(user);
        List<Appointment> appointments = appointmentRepository.findAppointmentByDoctorAndDateTimeAfter(doctor, LocalDateTime.now());
        return appointments.stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> findPastAppointmentsForDoctor(String username) {
        User user = findUserByUsername(username);
        Doctor doctor = findDoctorByUser(user);
        List<Appointment> appointments = appointmentRepository.findAppointmentByDoctorAndDateTimeBefore(doctor, LocalDateTime.now());
        return appointments.stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    private Patient findPatientById(Long patientId) {
        return patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found"));
    }
    private Appointment findAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
    }
    private AppointmentStatus findAppointmentStatusByName(String name){
        return appointmentStatusRepository.findByName(EAppointmentStatus.valueOf(name)).orElseThrow(() -> new EntityNotFoundException("Appointment status not found"));
    }
    private Doctor findDoctorById(Long doctorId){
        return doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
    }
    private User findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
    private Patient findByUser(User user){
        return patientRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
    }
    private Doctor findDoctorByUser(User user){
        return doctorRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
    }
}
