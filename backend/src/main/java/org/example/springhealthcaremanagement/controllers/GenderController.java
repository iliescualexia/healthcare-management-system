package org.example.springhealthcaremanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.services.address.AddressService;
import org.example.springhealthcaremanagement.services.gender.GenderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.example.springhealthcaremanagement.globals.UrlMapping.ADDRESS;
import static org.example.springhealthcaremanagement.globals.UrlMapping.GENDER;

@RequestMapping(GENDER)
@RestController
@RequiredArgsConstructor
public class GenderController {
    private final GenderService genderService;

    @GetMapping
    public List<GenderDto> findAll() {
        return genderService.findAll();
    }
}
