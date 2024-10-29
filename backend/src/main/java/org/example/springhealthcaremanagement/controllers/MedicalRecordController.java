package org.example.springhealthcaremanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.medicalrecord.MedicalRecordDto;
import org.example.springhealthcaremanagement.dtos.medicalrecord.MedicalRecordRequestDto;
import org.example.springhealthcaremanagement.services.medicalrecord.MedicalRecordService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.example.springhealthcaremanagement.globals.UrlMapping.*;

@RequestMapping(MEDICAL_RECORD)
@RestController
@RequiredArgsConstructor
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;
    @PostMapping
    public MedicalRecordDto create(@RequestBody @Valid MedicalRecordRequestDto reqDto){
        return medicalRecordService.save(reqDto);
    }
    @PutMapping
    public MedicalRecordDto update(@RequestBody @Valid MedicalRecordDto medicalRecordDto) {
        return medicalRecordService.update(medicalRecordDto);
    }
    @DeleteMapping
    public MedicalRecordDto delete(@RequestBody @Valid MedicalRecordDto medicalRecordDto) {
        return medicalRecordService.delete(medicalRecordDto);
    }
    @GetMapping
    public List<MedicalRecordDto> findAll() {
        return medicalRecordService.findAll();
    }
    @GetMapping(FIND_MEDICAL_RECORDS_BY_DOCTOR_USERNAME)
    public List<MedicalRecordDto> findAllByDoctor(@PathVariable String doctorUsername) {
        return medicalRecordService.findAllByDoctor(doctorUsername);
    }
    @GetMapping(FIND_MEDICAL_RECORDS_BY_PATIENT_USERNAME)
    public List<MedicalRecordDto> findAllByPatient(@PathVariable String patientUsername) {
        return medicalRecordService.findAllByPatient(patientUsername);
    }

}
