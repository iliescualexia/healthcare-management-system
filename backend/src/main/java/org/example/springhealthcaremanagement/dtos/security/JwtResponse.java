package org.example.springhealthcaremanagement.dtos.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

    private String token;
    private Long id;
    private String username;
    private String email;
    private String role;

}