package org.example.springhealthcaremanagement.controllers;

import org.example.springhealthcaremanagement.core.SpringControllerBaseTest;
import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.address.AddressRequestDto;
import org.example.springhealthcaremanagement.services.address.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.example.springhealthcaremanagement.globals.UrlMapping.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AddressControllerTest extends SpringControllerBaseTest {
    @InjectMocks
    private AddressController addressController;
    @Mock
    private AddressService addressService;
    @BeforeEach
    public void setUp() {
        super.setUp();
        addressController = new AddressController(addressService);
        mvc = buildForController(addressController);
    }
    @Test
    void create() throws Exception{
        AddressRequestDto createRequestDto = AddressRequestDto.builder().city("name").build();

        AddressDto result = AddressDto.builder().id(1L).city("name").build();

        when(addressService.save(any(AddressRequestDto.class)))
                .thenReturn(result);

        ResultActions res = performPostWithRequestBody(ADDRESS, createRequestDto);

        verify(addressService, times(1)).save(any(AddressRequestDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }


    @Test
    void update() throws Exception {
        AddressDto updateRequestDto = AddressDto.builder().id(1L).city("New York").build();

        AddressDto result = AddressDto.builder().id(1L).city("New York").build();

        when(addressService.update(any(AddressDto.class))).thenReturn(result);

        ResultActions res = performPutWithRequestBody(ADDRESS, updateRequestDto);

        verify(addressService, times(1)).update(any(AddressDto.class));
        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }
    @Test
    void findAll() throws Exception {
        AddressDto address1 = AddressDto.builder().id(1L).city("New York").build();
        AddressDto address2 = AddressDto.builder().id(2L).city("Los Angeles").build();

        List<AddressDto> addressList = Arrays.asList(address1, address2);

        when(addressService.findAll()).thenReturn(addressList);

        ResultActions res = performGet(ADDRESS);

        verify(addressService, times(1)).findAll();

        res.andExpect(status().isOk()).andExpect(contentToBe(addressList));
    }
    @Test
    void findById() throws Exception {
        Long id = 1L;
        AddressDto result = AddressDto.builder().id(id).city("New York").build();

        when(addressService.findById(id)).thenReturn(ResponseEntity.of(Optional.of(result)));

        ResultActions res = performGet(ADDRESS+ADDRESS_ID_PATH, id);

        verify(addressService, times(1)).findById(id);

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }
    @Test
    void delete() throws Exception {
        AddressDto deleteRequestDto = AddressDto.builder().id(1L).city("New York").build();

        AddressDto result = AddressDto.builder().id(1L).city("New York").build();

        when(addressService.delete(any(AddressDto.class))).thenReturn(result);

        ResultActions res = performDeleteWithRequestBody(ADDRESS, deleteRequestDto);

        verify(addressService, times(1)).delete(any(AddressDto.class));

        res.andExpect(status().isOk()).andExpect(contentToBe(result));
    }

    @Test
    void findByCity() throws Exception {
        String city = "New York";
        AddressDto address1 = AddressDto.builder().id(1L).city(city).build();
        AddressDto address2 = AddressDto.builder().id(2L).city(city).build();

        List<AddressDto> addressList = Arrays.asList(address1, address2);

        when(addressService.findByCity(city)).thenReturn(addressList);

        ResultActions res = performGet(ADDRESS + ADDRESS_CITY_PATH,city);

        verify(addressService, times(1)).findByCity(city);

        res.andExpect(status().isOk()).andExpect(contentToBe(addressList));
    }

}