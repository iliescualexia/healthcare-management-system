package org.example.springhealthcaremanagement.entities.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "street_address")
    private String streetAddress;

    @Column( name = "city")
    private String city;

    @Column( name = "county")
    private String county;

    @Column(name = "country")
    private String country;

    @Column(name = "postal_code")
    private String postalCode;
}
