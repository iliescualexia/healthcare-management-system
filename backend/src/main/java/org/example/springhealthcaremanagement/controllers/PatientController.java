package org.example.springhealthcaremanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientRequestDto;
import org.example.springhealthcaremanagement.services.patient.PatientService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.example.springhealthcaremanagement.globals.UrlMapping.*;

@RequestMapping(PATIENT)
@RestController
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    @PostMapping
    public PatientDto create(@RequestBody @Valid PatientRequestDto reqDto){
        return patientService.save(reqDto);
    }
    @PutMapping
    public PatientDto update(@RequestBody @Valid PatientDto patientDto) {
            return patientService.update(patientDto);
    }
    @DeleteMapping
    public PatientDto delete(@RequestBody @Valid PatientDto patientDto) {
        return patientService.delete(patientDto);
    }
    @GetMapping
    public List<PatientDto> findAll() {
        return patientService.findAll();
    }
    @GetMapping(FIND_PATIENT_BY_USERNAME)
    public PatientDto findByUsername(@PathVariable String username) {
        return patientService.findByUsername(username);
    }

}
