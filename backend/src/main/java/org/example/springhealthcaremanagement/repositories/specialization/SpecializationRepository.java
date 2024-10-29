package org.example.springhealthcaremanagement.repositories.specialization;

import org.example.springhealthcaremanagement.entities.specialization.Specialization;
import org.example.springhealthcaremanagement.entities.specialization.ESpecialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    Optional<Specialization> findByName(ESpecialization department);
}
