package org.example.springhealthcaremanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.details.UserDetailsImpl;
import org.example.springhealthcaremanagement.dtos.address.AddressDto;
import org.example.springhealthcaremanagement.dtos.address.AddressRequestDto;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorRegisterDto;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorRequestDto;
import org.example.springhealthcaremanagement.dtos.gender.GenderDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientRegisterDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientRequestDto;
import org.example.springhealthcaremanagement.dtos.security.JwtResponse;
import org.example.springhealthcaremanagement.dtos.security.LoginRequest;
import org.example.springhealthcaremanagement.dtos.security.MessageResponse;
import org.example.springhealthcaremanagement.dtos.security.SignupRequest;
import org.example.springhealthcaremanagement.dtos.specialization.SpecializationDto;
import org.example.springhealthcaremanagement.dtos.user.UserDto;
import org.example.springhealthcaremanagement.services.address.AddressService;
import org.example.springhealthcaremanagement.services.doctor.DoctorService;
import org.example.springhealthcaremanagement.services.patient.PatientService;
import org.example.springhealthcaremanagement.services.security.AuthService;
import org.example.springhealthcaremanagement.services.security.JwtUtilsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.Set;
import java.util.stream.Collectors;

import static org.example.springhealthcaremanagement.globals.UrlMapping.*;


@RequestMapping(AUTH)
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtilsService jwtUtilsService;
    private final PatientService patientService;
    private final AddressService addressService;
    private final DoctorService doctorService;

    @PostMapping(SIGN_IN)
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authService.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtilsService.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthority().getAuthority();
        return ResponseEntity.ok(
                JwtResponse.builder()
                        .token(jwt)
                        .id(userDetails.getId())
                        .username(userDetails.getUsername())
                        .email(userDetails.getEmail())
                        .role(role)
                        .build());
    }

    @PostMapping(SIGN_UP)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (authService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (authService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        UserDto userDto  = authService.register(signUpRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
    @PostMapping(SIGN_UP_PATIENT)
    public ResponseEntity<?> registerPatient(@Valid @RequestBody PatientRegisterDto patientRegisterDto) {
        if (authService.existsByUsername(patientRegisterDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (authService.existsByEmail(patientRegisterDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        SignupRequest signUpRequest = SignupRequest.builder()
                .role(patientRegisterDto.getRole())
                .password(patientRegisterDto.getPassword())
                .email(patientRegisterDto.getEmail())
                .username(patientRegisterDto.getUsername())
                .build();
        UserDto userDto  = authService.register(signUpRequest);

        AddressRequestDto addressRequestDto = AddressRequestDto.builder()
                .city(patientRegisterDto.getCity())
                .county(patientRegisterDto.getCounty())
                .country(patientRegisterDto.getCountry())
                .streetAddress(patientRegisterDto.getStreetAddress())
                .postalCode(patientRegisterDto.getPostalCode())
                .build();
        AddressDto addressDto = addressService.save(addressRequestDto);
        GenderDto genderDto = GenderDto.builder()
                .name(patientRegisterDto.getGender())
                .build();
        PatientRequestDto patientRequestDto = PatientRequestDto
                .builder()
                .firstName(patientRegisterDto.getFirstName())
                .lastName(patientRegisterDto.getLastName())
                .phoneNumber(patientRegisterDto.getPhoneNumber())
                .address(addressDto)
                .user(userDto)
                .gender(genderDto)
                .build();
        patientService.save(patientRequestDto);
        return ResponseEntity.ok(new MessageResponse("Patient registered successfully"));
    }
    @PostMapping(SIGN_UP_DOCTOR)
    public ResponseEntity<?> registerDoctor(@Valid @RequestBody DoctorRegisterDto doctorRegisterDto) {
        if (authService.existsByUsername(doctorRegisterDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (authService.existsByEmail(doctorRegisterDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        SignupRequest signUpRequest = SignupRequest.builder()
                .role(doctorRegisterDto.getRole())
                .password(doctorRegisterDto.getPassword())
                .email(doctorRegisterDto.getEmail())
                .username(doctorRegisterDto.getUsername())
                .build();
        UserDto userDto  = authService.register(signUpRequest);

        GenderDto genderDto = GenderDto.builder()
                .name(doctorRegisterDto.getGender())
                .build();
        Set<SpecializationDto> specializationDtos = doctorRegisterDto.getSpecializations().stream()
                .map(specialization -> SpecializationDto.builder().name(specialization).build())
                .collect(Collectors.toSet());
        DoctorRequestDto doctorRequestDto = DoctorRequestDto
                .builder()
                .yearsOfExperience(doctorRegisterDto.getYearsOfExperience())
                .biography(doctorRegisterDto.getBiography())
                .specializations(specializationDtos)
                .firstName(doctorRegisterDto.getFirstName())
                .lastName(doctorRegisterDto.getLastName())
                .user(userDto)
                .gender(genderDto)
                .build();
        doctorService.save(doctorRequestDto);
        return ResponseEntity.ok(new MessageResponse("Doctor registered successfully"));
    }
}