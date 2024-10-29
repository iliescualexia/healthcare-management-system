package org.example.springhealthcaremanagement.dtos.address;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String streetAddress;
    private String city;
    private String county;
    private String country;
    private String postalCode;
}
