package org.example.springhealthcaremanagement.repositories.role;

import org.example.springhealthcaremanagement.entities.role.ERole;
import org.example.springhealthcaremanagement.entities.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);

}