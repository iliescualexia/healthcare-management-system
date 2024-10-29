package org.example.springhealthcaremanagement.services.specialization;

import org.example.springhealthcaremanagement.dtos.specialization.SpecializationDto;

import java.util.List;

public interface SpecializationService {
    List<SpecializationDto> findAll();
}
