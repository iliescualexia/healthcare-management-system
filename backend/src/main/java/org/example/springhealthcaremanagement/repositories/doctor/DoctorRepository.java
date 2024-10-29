package org.example.springhealthcaremanagement.repositories.doctor;

import org.example.springhealthcaremanagement.entities.address.Address;
import org.example.springhealthcaremanagement.entities.doctor.Doctor;
import org.example.springhealthcaremanagement.entities.specialization.Specialization;
import org.example.springhealthcaremanagement.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByUser(User user);
    List<Doctor> findDoctorBySpecializationsContaining(Specialization specialization);
}
