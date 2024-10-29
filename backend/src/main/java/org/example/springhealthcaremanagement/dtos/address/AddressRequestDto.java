package org.example.springhealthcaremanagement.dtos.address;

import lombok.*;
import org.example.springhealthcaremanagement.entities.address.Address;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {
    private String streetAddress;
    private String city;
    private String county;
    private String country;
    private String postalCode;
}
