package org.example.springhealthcaremanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorDto;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorRequestDto;
import org.example.springhealthcaremanagement.services.doctor.DoctorService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.example.springhealthcaremanagement.globals.UrlMapping.*;

@RequestMapping(DOCTOR)
@RestController
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    @PostMapping
    public DoctorDto create(@RequestBody @Valid DoctorRequestDto reqDto){
        return doctorService.save(reqDto);
    }
    @PutMapping
    public DoctorDto update(@RequestBody @Valid DoctorDto doctorDto) {
        return doctorService.update(doctorDto);
    }
    @DeleteMapping
    public DoctorDto delete(@RequestBody @Valid DoctorDto doctorDto) {
        return doctorService.delete(doctorDto);
    }
    @GetMapping
    public List<DoctorDto> findAll() {
        return doctorService.findAll();
    }
    @GetMapping(FIND_DOCTOR_BY_USERNAME)
    public DoctorDto findByUsername(@PathVariable String username) {
        return doctorService.findByUsername(username);
    }
    @GetMapping(FIND_DOCTORS_WITH_SPECIALIZATION)
    public List<DoctorDto> findDoctorsWithSpecialization(@PathVariable String specialization){
        return doctorService.findDoctorsWithSpecialization(specialization);
    }
}
