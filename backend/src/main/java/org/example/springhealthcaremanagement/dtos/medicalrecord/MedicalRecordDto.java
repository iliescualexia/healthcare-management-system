package org.example.springhealthcaremanagement.dtos.medicalrecord;

import jakarta.persistence.*;
import lombok.*;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorDto;
import org.example.springhealthcaremanagement.dtos.medication.MedicationDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientDto;
import org.example.springhealthcaremanagement.entities.doctor.Doctor;
import org.example.springhealthcaremanagement.entities.medication.Medication;
import org.example.springhealthcaremanagement.entities.patient.Patient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDto {
    private Long id;
    private DoctorDto doctor;
    private PatientDto patient;
    private String dateOfVisit;
    private String diagnosis;

    private String treatmentPlan;
    private List<MedicationDto> prescribedMedication = new ArrayList<>();
}
