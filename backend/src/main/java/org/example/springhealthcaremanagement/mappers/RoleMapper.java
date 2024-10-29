package org.example.springhealthcaremanagement.mappers;

import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.dtos.role.RoleDto;
import org.example.springhealthcaremanagement.entities.gender.Gender;
import org.example.springhealthcaremanagement.entities.role.ERole;
import org.example.springhealthcaremanagement.entities.role.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(source = "name", target = "name")
    RoleDto toDto(Role role);

    @Mapping(source = "name", target = "name")
    Role toEntity(RoleDto roleDto);

    default String map(ERole eRole) {
        return eRole != null ? eRole.name() : null;
    }

    default ERole map(String eRole) {
        return eRole != null ? ERole.valueOf(eRole) : null;
    }

}