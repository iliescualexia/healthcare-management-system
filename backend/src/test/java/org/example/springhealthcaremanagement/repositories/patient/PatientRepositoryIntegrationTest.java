package org.example.springhealthcaremanagement.repositories.patient;

import org.example.springhealthcaremanagement.core.SpringIntegrationBaseTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class PatientRepositoryIntegrationTest extends SpringIntegrationBaseTest {
    @Autowired
    private PatientRepository patientRepository;

}