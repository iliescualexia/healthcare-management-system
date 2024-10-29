package org.example.springhealthcaremanagement.repositories.appointment;

import org.example.springhealthcaremanagement.entities.address.Address;
import org.example.springhealthcaremanagement.entities.appointment.Appointment;
import org.example.springhealthcaremanagement.entities.appointmentstatus.AppointmentStatus;
import org.example.springhealthcaremanagement.entities.doctor.Doctor;
import org.example.springhealthcaremanagement.entities.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAppointmentByDoctor(Doctor doctor);
    List<Appointment> findAppointmentByPatientAndDateTimeBefore(Patient patient, LocalDateTime dateTime);
    List<Appointment> findAppointmentByPatientAndDateTimeAfter(Patient patient, LocalDateTime dateTime);
    List<Appointment> findAllByDateTimeBefore(LocalDateTime dateTime);
    List<Appointment> findAppointmentByDoctorAndDateTimeAfter(Doctor doctor, LocalDateTime dateTime);
    List<Appointment> findAppointmentByDoctorAndDateTimeBefore(Doctor doctor, LocalDateTime dateTime);
    List<Appointment> findAppointmentByDoctorAndStatus(Doctor doctor, AppointmentStatus status);
    List<Appointment> findAppointmentByDateTimeBetweenAndStatus(LocalDateTime start, LocalDateTime end, AppointmentStatus status);

}
