package org.example.springhealthcaremanagement.services.address;

import org.example.springhealthcaremanagement.core.SpringUnitBaseTest;
import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.address.AddressRequestDto;
import org.example.springhealthcaremanagement.entities.address.Address;
import org.example.springhealthcaremanagement.mappers.AddressMapper;
import org.example.springhealthcaremanagement.repositories.address.AddressRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AddressServiceTest extends SpringUnitBaseTest {
    @InjectMocks
    private AddressServiceImpl addressService;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private AddressMapper addressMapper;

    @Test
    void save() {
        String city = "city";
        String country = "country";
        String postalCode = "postalCode";
        String streetAddress = "street";
        AddressRequestDto dto = new AddressRequestDto();
        dto.setCity(city);
        dto.setCountry(country);
        dto.setPostalCode(postalCode);
        dto.setStreetAddress(streetAddress);
        Address address = Address.builder()
                .city(city)
                .country(country)
                .postalCode(postalCode)
                .streetAddress(streetAddress)
                .build();
        Address savedAddress = Address.builder()
                .id(1L)
                .city(city)
                .country(country)
                .postalCode(postalCode)
                .streetAddress(streetAddress)
                .build();

        when(addressRepository.save(address)).thenReturn(savedAddress);
        when(addressMapper.requestDTOTOEntity(dto)).thenReturn(address);
        when(addressMapper.toDto(savedAddress)).thenReturn(AddressDto.builder()
                .id(1L)
                .city(city)
                .country(country)
                .postalCode(postalCode)
                .streetAddress(streetAddress)
                .build());
        AddressDto result = addressService.save(dto);
        assertEquals(savedAddress.getId(), result.getId());
        assertEquals(savedAddress.getCity(), result.getCity());
        assertEquals(savedAddress.getCountry(), result.getCountry());
        assertEquals(savedAddress.getPostalCode(), result.getPostalCode());
        assertEquals(savedAddress.getStreetAddress(), result.getStreetAddress());
    }

    @Test
    void update() {
        AddressDto dto = new AddressDto();
        dto.setId(1L);
        dto.setCity("city");
        dto.setCountry("country");
        dto.setPostalCode("postalCode");
        dto.setStreetAddress("street");
        Address address = Address.builder()
                .id(1L)
                .city("city")
                .country("country")
                .postalCode("postalCode")
                .streetAddress("street")
                .build();

        when(addressRepository.findById(dto.getId())).thenReturn(Optional.of(address));
        when(addressRepository.save(address)).thenReturn(address);
        when(addressMapper.toEntity(dto)).thenReturn(address);
        when(addressMapper.toDto(address)).thenReturn(dto);

        AddressDto result = addressService.update(dto);

        assertEquals(dto.getId(), result.getId());
        assertEquals(dto.getCity(), result.getCity());
        assertEquals(dto.getCountry(), result.getCountry());
        assertEquals(dto.getPostalCode(), result.getPostalCode());
        assertEquals(dto.getStreetAddress(), result.getStreetAddress());
    }

    @Test
    void delete() {
        AddressDto dto = new AddressDto();
        dto.setId(1L);
        Address address = Address.builder()
                .id(1L)
                .city("city")
                .country("country")
                .postalCode("postalCode")
                .streetAddress("street")
                .build();

        when(addressRepository.findById(dto.getId())).thenReturn(Optional.of(address));
        when(addressMapper.toEntity(dto)).thenReturn(address);

        AddressDto result = addressService.delete(dto);

        assertEquals(dto.getId(), result.getId());
    }

    @Test
    void findAll() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(Address.builder()
                .id(1L)
                .city("city")
                .country("country")
                .postalCode("postalCode")
                .streetAddress("street")
                .build());

        when(addressRepository.findAll()).thenReturn(addresses);
        when(addressMapper.toDto(addresses.get(0))).thenReturn(AddressDto.builder()
                .id(1L)
                .city("city")
                .country("country")
                .postalCode("postalCode")
                .streetAddress("street")
                .build());

        List<AddressDto> result = addressService.findAll();

        assertEquals(addresses.size(), result.size());
        assertEquals(addresses.get(0).getId(), result.get(0).getId());
    }

    @Test
    void findByCity() {
        String city = "city";
        List<Address> addresses = new ArrayList<>();
        addresses.add(Address.builder()
                .id(1L)
                .city(city)
                .country("country")
                .postalCode("postalCode")
                .streetAddress("street")
                .build());

        when(addressRepository.findByCity(city)).thenReturn(addresses);
        when(addressMapper.toDto(addresses.get(0))).thenReturn(AddressDto.builder()
                .id(1L)
                .city(city)
                .country("country")
                .postalCode("postalCode")
                .streetAddress("street")
                .build());

        List<AddressDto> result = addressService.findByCity(city);

        assertEquals(addresses.size(), result.size());
        assertEquals(addresses.get(0).getId(), result.get(0).getId());
    }

    @Test
    void findById() {
        Long id = 1L;
        Address address = Address.builder()
                .id(id)
                .city("city")
                .country("country")
                .postalCode("postalCode")
                .streetAddress("street")
                .build();

        when(addressRepository.findById(id)).thenReturn(Optional.of(address));
        when(addressMapper.toDto(address)).thenReturn(AddressDto.builder()
                .id(id)
                .city("city")
                .country("country")
                .postalCode("postalCode")
                .streetAddress("street")
                .build());

        ResponseEntity<AddressDto> result = addressService.findById(id);

        assertEquals(address.getId(), result.getBody().getId());
    }
}