package org.example.springhealthcaremanagement.repositories.medication;

import org.example.springhealthcaremanagement.core.SpringIntegrationBaseTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MedicationRepositoryIntegrationTest extends SpringIntegrationBaseTest {
    @Autowired
    private MedicationRepository medicationRepository;

}