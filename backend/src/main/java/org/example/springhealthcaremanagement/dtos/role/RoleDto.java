package org.example.springhealthcaremanagement.dtos.role;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.example.springhealthcaremanagement.entities.role.ERole;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private String name;
}
