package org.example.springhealthcaremanagement.services.address;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.address.AddressRequestDto;
import org.example.springhealthcaremanagement.entities.address.Address;
import org.example.springhealthcaremanagement.exception.EntityNotFoundException;
import org.example.springhealthcaremanagement.mappers.AddressMapper;
import org.example.springhealthcaremanagement.repositories.address.AddressRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public AddressDto save(AddressRequestDto addressRequestDto) {
        Address savedAddress = addressRepository.save(addressMapper.requestDTOTOEntity(addressRequestDto));
        return addressMapper.toDto(savedAddress);
    }

    @Override
    public AddressDto update(AddressDto addressDto) {
        getAddressById(addressDto.getId());

        Address updatedAddress = addressRepository.save(addressMapper.toEntity(addressDto));
        return addressMapper.toDto(updatedAddress);
    }

    @Override
    public AddressDto delete(AddressDto addressDto) {
        getAddressById(addressDto.getId());
        addressRepository.delete(addressMapper.toEntity(addressDto));
        return addressDto;

    }
    @Override
    public List<AddressDto> findAll() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AddressDto> findByCity(String city) {
        List<Address> addresses = addressRepository.findByCity(city);
        return addresses.stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<AddressDto> findById(long id) {
        return addressRepository.findById(id)
                .map(address -> ResponseEntity.ok(addressMapper.toDto(address)))
                .orElse(ResponseEntity.notFound().build());
    }
    private Address getAddressById(long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));
    }
}
