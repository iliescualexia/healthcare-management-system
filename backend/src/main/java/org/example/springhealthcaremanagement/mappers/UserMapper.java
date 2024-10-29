package org.example.springhealthcaremanagement.mappers;

import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.dtos.user.UserDto;
import org.example.springhealthcaremanagement.dtos.user.UserRequestDto;
import org.example.springhealthcaremanagement.entities.gender.Gender;
import org.example.springhealthcaremanagement.entities.user.User;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    UserRequestDto toRequestDto(User user);

    User toEntity(UserRequestDto userRequestDto);
}