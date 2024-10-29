package org.example.springhealthcaremanagement.services.specialization;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.specialization.SpecializationDto;
import org.example.springhealthcaremanagement.entities.address.Address;
import org.example.springhealthcaremanagement.entities.specialization.Specialization;
import org.example.springhealthcaremanagement.mappers.SpecializationMapper;
import org.example.springhealthcaremanagement.repositories.specialization.SpecializationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecializationServiceImpl implements SpecializationService{
    private final SpecializationRepository specializationRepository;
    private final SpecializationMapper specializationMapper;
    @Override
    public List<SpecializationDto> findAll() {
        List<Specialization> specializations = specializationRepository.findAll();
        return specializations.stream()
                .map(specializationMapper::toDto)
                .collect(Collectors.toList());
    }
}
