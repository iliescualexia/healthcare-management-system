package org.example.springhealthcaremanagement.services.patient;

import ClickSend.Model.Contact;
import org.example.springhealthcaremanagement.core.SpringUnitBaseTest;
import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientRequestDto;
import org.example.springhealthcaremanagement.dtos.user.UserDto;
import org.example.springhealthcaremanagement.entities.appointment.Appointment;
import org.example.springhealthcaremanagement.entities.gender.EGender;
import org.example.springhealthcaremanagement.entities.gender.Gender;
import org.example.springhealthcaremanagement.entities.patient.Patient;
import org.example.springhealthcaremanagement.entities.user.User;
import org.example.springhealthcaremanagement.exception.EntityNotFoundException;
import org.example.springhealthcaremanagement.mappers.PatientMapper;
import org.example.springhealthcaremanagement.repositories.gender.GenderRepository;
import org.example.springhealthcaremanagement.repositories.patient.PatientRepository;
import org.example.springhealthcaremanagement.repositories.user.UserRepository;
import org.example.springhealthcaremanagement.services.address.AddressService;
import org.example.springhealthcaremanagement.services.messages.SmsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
class PatientServiceTest extends SpringUnitBaseTest {
    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GenderRepository genderRepository;
    @Mock
    private AddressService addressService;
    @Mock
    private SmsService smsService;


    @Test
    void save() {
        PatientRequestDto requestDto = new PatientRequestDto();
        requestDto.setUser(UserDto.builder().username("username").build());
        requestDto.setGender(GenderDto.builder().name("MALE").build());
        Patient patient = Patient.builder().user(User.builder().username("username").build())
                .gender(Gender.builder().name(EGender.MALE).build()).build();
        Patient savedPatient = new Patient();
        PatientDto responseDto = new PatientDto();
        when(smsService.createNewContact(any())).thenReturn(true);
        when(patientMapper.requestDtoToEntity(requestDto)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(savedPatient);
        when(patientMapper.toDto(savedPatient)).thenReturn(responseDto);
        when(userRepository.findByUsername(requestDto.getUser().getUsername())).thenReturn(Optional.of(patient.getUser()));
        when(genderRepository.findByName(EGender.valueOf(requestDto.getGender().getName()))).thenReturn(Optional.of(patient.getGender()));

        PatientDto result = patientService.save(requestDto);

        assertEquals(responseDto, result);
    }

    @Test
    void findAll() {
        Patient patient = new Patient();
        PatientDto responseDto = new PatientDto();

        when(patientRepository.findAll()).thenReturn(Collections.singletonList(patient));
        when(patientMapper.toDto(patient)).thenReturn(responseDto);

        List<PatientDto> result = patientService.findAll();

        assertEquals(Collections.singletonList(responseDto), result);
    }

    @Test
    void update() {
        GenderDto genderDto = GenderDto.builder().name("MALE").build();
        Gender gender = Gender.builder().name(EGender.MALE).build();
        PatientDto requestDto = PatientDto.builder().id(1L).gender(genderDto).user(UserDto.builder().email("email").build()).build();
        Patient patient = Patient.builder().id(1L).gender(gender).user(User.builder().email("email").build()).build();
        Patient updatedPatient = Patient.builder().id(1L).gender(gender).user(User.builder().email("email1").build()).build();
        PatientDto responseDto = PatientDto.builder().gender(genderDto).id(1L).user(UserDto.builder().email("email1").build()).build();

        when(userRepository.findByUsername(requestDto.getUser().getUsername())).thenReturn(Optional.of(patient.getUser()));
        when(genderRepository.findByName(EGender.valueOf(requestDto.getGender().getName()))).thenReturn(Optional.of(patient.getGender()));
        when( addressService.update(any(AddressDto.class))).thenReturn(new AddressDto());
        when(patientRepository.findById(requestDto.getId())).thenReturn(Optional.of(patient));
        when(patientRepository.save(any())).thenReturn(updatedPatient);
        when(patientMapper.toEntity(requestDto)).thenReturn(patient);
        when(patientMapper.toDto(updatedPatient)).thenReturn(responseDto);

        PatientDto result = patientService.update(requestDto);

        assertEquals(responseDto, result);
    }

    @Test
    void delete() {
        PatientDto requestDto = new PatientDto();
        requestDto.setId(1L);
        Patient patient = Patient.builder().id(1L).build();
        PatientDto responseDto = PatientDto.builder().id(1L).build();

        when(patientRepository.findById(requestDto.getId())).thenReturn(Optional.of(patient));
        when(patientMapper.toDto(patient)).thenReturn(responseDto);

        PatientDto result = patientService.delete(requestDto);

        assertEquals(responseDto.getId(), result.getId());
        verify(patientRepository).delete(patient);
    }

}
