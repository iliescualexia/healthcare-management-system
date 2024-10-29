package org.example.springhealthcaremanagement.services.medicalrecord;

import org.example.springhealthcaremanagement.dtos.medicalrecord.MedicalRecordDto;
import org.example.springhealthcaremanagement.dtos.medicalrecord.MedicalRecordRequestDto;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecordDto save(MedicalRecordRequestDto medicalRecordRequestDto);
    MedicalRecordDto update(MedicalRecordDto medicalRecordDto);
    MedicalRecordDto delete(MedicalRecordDto medicalRecordDto);
    List<MedicalRecordDto> findAll();
    List<MedicalRecordDto> findAllByDoctor(String doctorUsername);
    List<MedicalRecordDto> findAllByPatient(String patientUsername);
}
