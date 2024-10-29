package org.example.springhealthcaremanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.address.AddressRequestDto;
import org.example.springhealthcaremanagement.services.address.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.example.springhealthcaremanagement.globals.UrlMapping.*;

@RequestMapping(ADDRESS)
@RestController
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public AddressDto create(@RequestBody @Valid AddressRequestDto reqDto){
        return addressService.save(reqDto);
    }
    @PutMapping
    public AddressDto update(@RequestBody @Valid AddressDto addressDto) {
        return addressService.update(addressDto);
    }
    @DeleteMapping
    public AddressDto delete(@RequestBody @Valid AddressDto addressDto) {
        return addressService.delete(addressDto);
    }
    @GetMapping
    public List<AddressDto> findAll() {
        return addressService.findAll();
    }
    @GetMapping(ADDRESS_ID_PATH)
    public ResponseEntity<AddressDto> findById(@PathVariable Long id){
        return addressService.findById(id);
    }
    @GetMapping(ADDRESS_CITY_PATH)
    public List<AddressDto> findById(@PathVariable String city){
        return addressService.findByCity(city);
    }
}
