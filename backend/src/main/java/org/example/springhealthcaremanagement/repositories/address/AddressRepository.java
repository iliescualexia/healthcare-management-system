package org.example.springhealthcaremanagement.repositories.address;

import org.example.springhealthcaremanagement.entities.address.Address;
import org.example.springhealthcaremanagement.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCity(String city);
}
