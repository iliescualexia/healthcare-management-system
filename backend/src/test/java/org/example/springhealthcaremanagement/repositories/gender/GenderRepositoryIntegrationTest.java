package org.example.springhealthcaremanagement.repositories.gender;

import org.example.springhealthcaremanagement.core.SpringIntegrationBaseTest;
import org.example.springhealthcaremanagement.entities.appointmentstatus.AppointmentStatus;
import org.example.springhealthcaremanagement.entities.appointmentstatus.EAppointmentStatus;
import org.example.springhealthcaremanagement.entities.gender.EGender;
import org.example.springhealthcaremanagement.entities.gender.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class GenderRepositoryIntegrationTest extends SpringIntegrationBaseTest {
    @Autowired
    private GenderRepository genderRepository;
    @Test
    void findByName() {
        String name = "MALE";
        assertTrue(genderRepository.findByName(EGender.valueOf(name)).isPresent());
    }
}