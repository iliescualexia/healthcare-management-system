package org.example.springhealthcaremanagement.dtos.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private String role;
}
