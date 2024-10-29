package org.example.springhealthcaremanagement.mappers;

import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.dtos.specialization.SpecializationDto;
import org.example.springhealthcaremanagement.entities.gender.Gender;
import org.example.springhealthcaremanagement.entities.role.ERole;
import org.example.springhealthcaremanagement.entities.specialization.ESpecialization;
import org.example.springhealthcaremanagement.entities.specialization.Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpecializationMapper {
    @Mapping(source = "name", target = "name")
    SpecializationDto toDto(Specialization specialization);
    @Mapping(source = "name", target = "name")

    Specialization toEntity(SpecializationDto specializationDto);
    default String map(ESpecialization eSpecialization) {
        return eSpecialization != null ? eSpecialization.name() : null;
    }

    default ESpecialization map(String eSpecialization) {
        return eSpecialization != null ? ESpecialization.valueOf(eSpecialization) : null;
    }
}

