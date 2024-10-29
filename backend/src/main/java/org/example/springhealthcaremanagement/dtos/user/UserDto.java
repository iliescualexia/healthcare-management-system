package org.example.springhealthcaremanagement.dtos.user;

import jakarta.persistence.*;
import lombok.*;
import org.example.springhealthcaremanagement.dtos.role.RoleDto;
import org.example.springhealthcaremanagement.entities.role.Role;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private RoleDto role;
}
