package org.example.springhealthcaremanagement.repositories.medicalrecord;

import org.example.springhealthcaremanagement.entities.doctor.Doctor;
import org.example.springhealthcaremanagement.entities.medicalrecord.MedicalRecord;
import org.example.springhealthcaremanagement.entities.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findAllByDoctor(Doctor doctor);
    List<MedicalRecord> findAllByPatient(Patient patient);
}
