package org.example.springhealthcaremanagement.dtos.user;

import lombok.*;
import org.example.springhealthcaremanagement.dtos.role.RoleDto;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private RoleDto role;
}
