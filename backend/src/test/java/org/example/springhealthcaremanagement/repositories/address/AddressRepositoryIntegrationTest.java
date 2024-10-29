package org.example.springhealthcaremanagement.repositories.address;

import org.example.springhealthcaremanagement.core.SpringIntegrationBaseTest;
import org.example.springhealthcaremanagement.entities.address.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AddressRepositoryIntegrationTest extends SpringIntegrationBaseTest {

    @Autowired
    private AddressRepository addressRepository;


    @Test
    void findByCity() {
        String city = "bucharest";
        Address address = Address.builder()
                .city(city)
                .streetAddress("str. test")
                .country("Romania")
                .postalCode("123456")
                .county("Ilfov")
                .build();
        addressRepository.save(address);
        assertFalse(addressRepository.findByCity(city).isEmpty());
        assertTrue(addressRepository.findByCity("not found").isEmpty());
        addressRepository.delete(address);
    }
}