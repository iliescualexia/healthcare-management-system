package org.example.springhealthcaremanagement.repositories.user;

import org.example.springhealthcaremanagement.core.SpringIntegrationBaseTest;
import org.example.springhealthcaremanagement.entities.role.ERole;
import org.example.springhealthcaremanagement.entities.user.User;
import org.example.springhealthcaremanagement.repositories.role.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryIntegrationTest extends SpringIntegrationBaseTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findByUsername() {
        User user = User.builder()
                .username("admin1111111")
                .email("admin111111@gmail.com")
                .password("admin")
                .role(roleRepository.findByName(ERole.ADMIN).get())
                .build();
        String username = "admin1111111";
        userRepository.save(user);
        assertTrue(userRepository.findByUsername(username).isPresent());
        userRepository.delete(user);
    }

    @Test
    void existsByUsername() {
        User user = User.builder()
                .username("admin2")
                .email("admin2@gmail.com")
                .password("admin")
                .role(roleRepository.findByName(ERole.ADMIN).get())
                .build();
        userRepository.save(user);
        assertTrue(userRepository.existsByUsername(user.getUsername()));
        userRepository.delete(user);
    }

    @Test
    void existsByEmail() {
        User user = User.builder()
                .username("admin3")
                .email("admin3@gmail.com")
                .password("admin3")
                .role(roleRepository.findByName(ERole.ADMIN).get())
                .build();
        userRepository.save(user);
        assertTrue(userRepository.existsByEmail(user.getEmail()));
        userRepository.delete(user);
    }
}