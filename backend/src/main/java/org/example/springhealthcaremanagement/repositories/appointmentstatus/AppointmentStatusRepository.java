package org.example.springhealthcaremanagement.repositories.appointmentstatus;

import org.example.springhealthcaremanagement.entities.appointmentstatus.AppointmentStatus;
import org.example.springhealthcaremanagement.entities.appointmentstatus.EAppointmentStatus;
import org.example.springhealthcaremanagement.entities.gender.Gender;
import org.example.springhealthcaremanagement.entities.specialization.ESpecialization;
import org.example.springhealthcaremanagement.entities.specialization.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus,Long> {
    Optional<AppointmentStatus> findByName(EAppointmentStatus appointmentStatus);
}
