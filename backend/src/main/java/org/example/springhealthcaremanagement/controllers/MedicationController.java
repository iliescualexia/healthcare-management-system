package org.example.springhealthcaremanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.medication.MedicationDto;
import org.example.springhealthcaremanagement.dtos.medication.MedicationRequestDto;
import org.example.springhealthcaremanagement.services.medication.MedicationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import static org.example.springhealthcaremanagement.globals.UrlMapping.MEDICATION;

@RequestMapping(MEDICATION)
@RestController
@RequiredArgsConstructor
public class MedicationController {
    private final MedicationService medicationService;
    @PostMapping
    public MedicationDto create(@RequestBody @Valid MedicationRequestDto reqDto){
        return medicationService.save(reqDto);
    }
    @PutMapping
    public MedicationDto update(@RequestBody @Valid MedicationDto medicationDto) {
        return medicationService.update(medicationDto);
    }
    @DeleteMapping
    public MedicationDto delete(@RequestBody @Valid MedicationDto medicationDto) {
        return medicationService.delete(medicationDto);
    }
    @GetMapping
    public List<MedicationDto> findAll() {
        return medicationService.findAll();
    }
}
