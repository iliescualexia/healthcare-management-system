package org.example.springhealthcaremanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.dtos.specialization.SpecializationDto;
import org.example.springhealthcaremanagement.services.gender.GenderService;
import org.example.springhealthcaremanagement.services.specialization.SpecializationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.example.springhealthcaremanagement.globals.UrlMapping.GENDER;
import static org.example.springhealthcaremanagement.globals.UrlMapping.SPECIALIZATION;

@RequestMapping(SPECIALIZATION)
@RestController
@RequiredArgsConstructor
public class SpecializationController {
    private final SpecializationService specializationService;

    @GetMapping
    public List<SpecializationDto> findAll() {
        return specializationService.findAll();
    }
}
