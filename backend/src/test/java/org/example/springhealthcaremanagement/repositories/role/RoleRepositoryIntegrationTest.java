package org.example.springhealthcaremanagement.repositories.role;

import org.example.springhealthcaremanagement.core.SpringIntegrationBaseTest;
import org.example.springhealthcaremanagement.entities.role.ERole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
class RoleRepositoryIntegrationTest extends SpringIntegrationBaseTest {
    @Autowired
    private RoleRepository roleRepository;
    @Test
    void findByName() {
        String name = "ADMIN";
        assertTrue(roleRepository.findByName(ERole.valueOf(name)).isPresent());
    }
}