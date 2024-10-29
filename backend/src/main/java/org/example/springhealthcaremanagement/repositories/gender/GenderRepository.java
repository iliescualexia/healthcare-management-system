package org.example.springhealthcaremanagement.repositories.gender;

import org.example.springhealthcaremanagement.entities.gender.EGender;
import org.example.springhealthcaremanagement.entities.gender.Gender;
import org.example.springhealthcaremanagement.entities.role.ERole;
import org.example.springhealthcaremanagement.entities.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepository extends JpaRepository<Gender,Long> {
    Optional<Gender> findByName(EGender eGender);
}
