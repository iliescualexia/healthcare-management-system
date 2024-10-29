package org.example.springhealthcaremanagement.repositories.medication;

import org.example.springhealthcaremanagement.entities.medicalrecord.MedicalRecord;
import org.example.springhealthcaremanagement.entities.medication.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
