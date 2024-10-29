package org.example.springhealthcaremanagement.services.gender;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.address.AddressRequestDto;
import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.mappers.AddressMapper;
import org.example.springhealthcaremanagement.mappers.GenderMapper;
import org.example.springhealthcaremanagement.repositories.address.AddressRepository;
import org.example.springhealthcaremanagement.repositories.gender.GenderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService{
    private final GenderRepository genderRepository;
    private final GenderMapper genderMapper;
    @Override
    public List<GenderDto> findAll() {
        return genderRepository.findAll().stream().map(genderMapper::toDto).toList();
    }
}
