package org.example.springhealthcaremanagement.services.doctor;

import org.example.springhealthcaremanagement.core.SpringUnitBaseTest;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorDto;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorRequestDto;
import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.dtos.specialization.SpecializationDto;
import org.example.springhealthcaremanagement.dtos.user.UserDto;
import org.example.springhealthcaremanagement.dtos.user.UserRequestDto;
import org.example.springhealthcaremanagement.entities.doctor.Doctor;
import org.example.springhealthcaremanagement.entities.gender.EGender;
import org.example.springhealthcaremanagement.entities.gender.Gender;
import org.example.springhealthcaremanagement.entities.user.User;
import org.example.springhealthcaremanagement.mappers.DoctorMapper;
import org.example.springhealthcaremanagement.repositories.doctor.DoctorRepository;
import org.example.springhealthcaremanagement.repositories.gender.GenderRepository;
import org.example.springhealthcaremanagement.repositories.specialization.SpecializationRepository;
import org.example.springhealthcaremanagement.repositories.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DoctorServiceTest extends SpringUnitBaseTest {
    @InjectMocks
    private DoctorServiceImpl doctorService;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private DoctorMapper doctorMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SpecializationRepository specializationRepository;
    @Mock
    private GenderRepository genderRepository;

    @Test
    void save() {
        UserDto userDto = UserDto.builder()
                .username("username")
                .build();
        DoctorRequestDto requestDto = DoctorRequestDto.builder()
                .user(userDto)
                .gender(GenderDto.builder().name("MALE").build())
                .specializations(new HashSet<>())
                .build();
        Doctor doctor = Doctor.builder()
                .user(User.builder().username("username").build())
                .gender(Gender.builder().name(EGender.valueOf("MALE")).build())
                .specializations(new HashSet<>())
                .build();
        Doctor savedDoctor = new Doctor();
        DoctorDto responseDto = new DoctorDto();

        when(doctorMapper.requestDtoToEntity(requestDto)).thenReturn(doctor);
        when(doctorRepository.save(doctor)).thenReturn(savedDoctor);
        when(doctorMapper.toDto(savedDoctor)).thenReturn(responseDto);
        when(userRepository.findByUsername(requestDto.getUser().getUsername())).thenReturn(Optional.of(doctor.getUser()));
        when(genderRepository.findByName(EGender.valueOf(requestDto.getGender().getName()))).thenReturn(Optional.of(doctor.getGender()));
        DoctorDto result = doctorService.save(requestDto);

        assertEquals(responseDto, result);
    }
    @Test
    void findAll() {
        Doctor doctor = new Doctor();
        DoctorDto responseDto = new DoctorDto();

        when(doctorRepository.findAll()).thenReturn(Collections.singletonList(doctor));
        when(doctorMapper.toDto(doctor)).thenReturn(responseDto);

        List<DoctorDto> result = doctorService.findAll();

        assertEquals(Collections.singletonList(responseDto), result);
    }

    @Test
    void update() {
        UserDto userDto = UserDto.builder()
                .username("username")
                .build();
        DoctorDto requestDto = DoctorDto.builder()
                .user(UserRequestDto.builder().username("username").build())
                .gender(GenderDto.builder().name("MALE").build())
                .specializations(new HashSet<>())
                .build();
        Doctor doctor = Doctor.builder()
                .user(User.builder().username("username").build())
                .gender(Gender.builder().name(EGender.valueOf("MALE")).build())
                .specializations(new HashSet<>())
                .build();
        Doctor updatedDoctor = Doctor.builder()
                .user(User.builder().username("username").build())
                .gender(Gender.builder().name(EGender.valueOf("MALE")).build())
                .specializations(new HashSet<>())
                .build();
        DoctorDto responseDto = DoctorDto.builder()
                .user(UserRequestDto.builder().username("username").build())
                .gender(GenderDto.builder().name("MALE").build())
                .specializations(new HashSet<>())
                .build();

        when(doctorRepository.findById(doctor.getId())).thenReturn(Optional.of(doctor));
        when(doctorRepository.save(any())).thenReturn(updatedDoctor);
        when(doctorMapper.toDto(updatedDoctor)).thenReturn(responseDto);
        when(genderRepository.findByName(EGender.valueOf(requestDto.getGender().getName()))).thenReturn(Optional.of(doctor.getGender()));
        when(userRepository.findByUsername(requestDto.getUser().getUsername())).thenReturn(Optional.of(doctor.getUser()));
        when(doctorMapper.toEntity(requestDto)).thenReturn(doctor);
        DoctorDto result = doctorService.update(requestDto);

        assertEquals(responseDto, result);
    }

    @Test
    void delete() {
        DoctorDto requestDto = new DoctorDto();
        requestDto.setId(1L);
        Doctor doctor = Doctor.builder().id(requestDto.getId()).build();
        DoctorDto responseDto = DoctorDto.builder().id(requestDto.getId()).build();

        when(doctorRepository.findById(requestDto.getId())).thenReturn(Optional.of(doctor));
        when(doctorMapper.toDto(doctor)).thenReturn(responseDto);

        DoctorDto result = doctorService.delete(requestDto);

        assertEquals(responseDto.getId(), result.getId());
        verify(doctorRepository).delete(doctor);
    }

}