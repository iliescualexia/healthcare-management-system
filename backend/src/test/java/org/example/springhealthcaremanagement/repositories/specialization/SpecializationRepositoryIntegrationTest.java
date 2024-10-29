package org.example.springhealthcaremanagement.repositories.specialization;

import org.example.springhealthcaremanagement.core.SpringIntegrationBaseTest;
import org.example.springhealthcaremanagement.entities.role.ERole;
import org.example.springhealthcaremanagement.entities.specialization.ESpecialization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class SpecializationRepositoryIntegrationTest extends SpringIntegrationBaseTest {
    @Autowired
    private SpecializationRepository specializationRepository;
    @Test
    void findByName() {
        String name = "INTERNAL_MEDICINE";
        assertTrue(specializationRepository.findByName(ESpecialization.valueOf(name)).isPresent());
    }

}