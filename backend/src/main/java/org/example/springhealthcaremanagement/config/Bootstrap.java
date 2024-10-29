package org.example.springhealthcaremanagement.config;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.entities.appointmentstatus.AppointmentStatus;
import org.example.springhealthcaremanagement.entities.appointmentstatus.EAppointmentStatus;
import org.example.springhealthcaremanagement.entities.specialization.Specialization;
import org.example.springhealthcaremanagement.entities.specialization.ESpecialization;
import org.example.springhealthcaremanagement.entities.gender.EGender;
import org.example.springhealthcaremanagement.entities.gender.Gender;
import org.example.springhealthcaremanagement.entities.role.ERole;
import org.example.springhealthcaremanagement.entities.role.Role;
import org.example.springhealthcaremanagement.repositories.appointmentstatus.AppointmentStatusRepository;
import org.example.springhealthcaremanagement.repositories.specialization.SpecializationRepository;
import org.example.springhealthcaremanagement.repositories.gender.GenderRepository;
import org.example.springhealthcaremanagement.repositories.role.RoleRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
public class Bootstrap {

    private final RoleRepository roleRepository;
    private final SpecializationRepository specializationRepository;
    private final GenderRepository genderRepository;
    private final AppointmentStatusRepository appointmentStatusRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void bootstrapData() {
        for (ERole value : ERole.values()) {
            if (roleRepository.findByName(value).isEmpty()) {
                roleRepository.save(
                        Role.builder().name(value).build()
                );
            }
        }
        for (ESpecialization value : ESpecialization.values()) {
            if (specializationRepository.findByName(value).isEmpty()) {
                specializationRepository.save(
                        Specialization.builder().name(value).build()
                );
            }
        }
        for (EGender value : EGender.values()) {
            if (genderRepository.findByName(value).isEmpty()) {
                genderRepository.save(
                        Gender.builder().name(value).build()
                );
            }
        }
        for (EAppointmentStatus value : EAppointmentStatus.values()) {
            if (appointmentStatusRepository.findByName(value).isEmpty()) {
                appointmentStatusRepository.save(
                        AppointmentStatus.builder().name(value).build()
                );
            }
        }
    }
}