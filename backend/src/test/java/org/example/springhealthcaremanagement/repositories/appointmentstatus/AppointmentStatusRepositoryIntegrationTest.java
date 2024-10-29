package org.example.springhealthcaremanagement.repositories.appointmentstatus;

import org.example.springhealthcaremanagement.core.SpringIntegrationBaseTest;
import org.example.springhealthcaremanagement.entities.appointmentstatus.AppointmentStatus;
import org.example.springhealthcaremanagement.entities.appointmentstatus.EAppointmentStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentStatusRepositoryIntegrationTest extends SpringIntegrationBaseTest {
    @Autowired
    private AppointmentStatusRepository appointmentStatusRepository;
    @Test
    void findByName() {
        String name = "PENDING";
        assertTrue(appointmentStatusRepository.findByName(EAppointmentStatus.valueOf(name)).isPresent());
    }
}