package org.example.springhealthcaremanagement.dtos.medicalrecord;

import lombok.*;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorDto;
import org.example.springhealthcaremanagement.dtos.medication.MedicationDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordRequestDto {
    private String doctorUsername;
    private String patientUsername;
    private String dateOfVisit;
    private String diagnosis;

    private String treatmentPlan;
    private List<MedicationDto> prescribedMedication = new ArrayList<>();
}
