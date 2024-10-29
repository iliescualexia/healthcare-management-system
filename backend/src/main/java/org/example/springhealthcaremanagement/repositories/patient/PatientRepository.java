package org.example.springhealthcaremanagement.repositories.patient;

import org.example.springhealthcaremanagement.entities.doctor.Doctor;
import org.example.springhealthcaremanagement.entities.medicalrecord.MedicalRecord;
import org.example.springhealthcaremanagement.entities.patient.Patient;
import org.example.springhealthcaremanagement.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByUser(User user);
}
