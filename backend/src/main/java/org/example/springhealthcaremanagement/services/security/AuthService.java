package org.example.springhealthcaremanagement.services.security;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.security.SignupRequest;
import org.example.springhealthcaremanagement.dtos.user.UserDto;
import org.example.springhealthcaremanagement.entities.role.ERole;
import org.example.springhealthcaremanagement.entities.role.Role;
import org.example.springhealthcaremanagement.entities.user.User;
import org.example.springhealthcaremanagement.exception.EntityNotFoundException;
import org.example.springhealthcaremanagement.mappers.UserMapper;
import org.example.springhealthcaremanagement.repositories.role.RoleRepository;
import org.example.springhealthcaremanagement.repositories.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDto register(SignupRequest signUpRequest) {
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .build();

        String rolesStr = signUpRequest.getRole();
        Role role;

        if (rolesStr == null) {
            role = roleRepository.findByName(ERole.PATIENT)
                    .orElseThrow(() -> new EntityNotFoundException("Cannot find CUSTOMER role"));

        } else {
                role = roleRepository.findByName(ERole.valueOf(rolesStr))
                        .orElseThrow(() -> new EntityNotFoundException("Cannot find role: " + rolesStr));
        }

        user.setRole(role);
        return userMapper.toDto(userRepository.save(user));
    }

    public Authentication authenticate(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }
}