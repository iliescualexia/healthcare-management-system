package org.example.springhealthcaremanagement.services.patient;

import org.example.springhealthcaremanagement.dtos.patient.PatientDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientRequestDto;
import org.example.springhealthcaremanagement.entities.patient.Patient;

import java.util.List;

public interface PatientService {
    PatientDto save(PatientRequestDto patientRequestDto);

    List<PatientDto> findAll();

    PatientDto update(PatientDto patientDto);

    PatientDto delete(PatientDto patientDto);
    PatientDto findByUsername(String username);
    String generateCsv() throws Exception;
}
