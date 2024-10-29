package org.example.springhealthcaremanagement.services.medicalrecord;

import org.example.springhealthcaremanagement.core.SpringUnitBaseTest;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorDto;
import org.example.springhealthcaremanagement.dtos.medicalrecord.MedicalRecordDto;
import org.example.springhealthcaremanagement.dtos.medicalrecord.MedicalRecordRequestDto;
import org.example.springhealthcaremanagement.dtos.medication.MedicationDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientDto;
import org.example.springhealthcaremanagement.entities.doctor.Doctor;
import org.example.springhealthcaremanagement.entities.medicalrecord.MedicalRecord;
import org.example.springhealthcaremanagement.entities.medication.Medication;
import org.example.springhealthcaremanagement.entities.patient.Patient;
import org.example.springhealthcaremanagement.entities.user.User;
import org.example.springhealthcaremanagement.mappers.MedicalRecordMapper;
import org.example.springhealthcaremanagement.repositories.doctor.DoctorRepository;
import org.example.springhealthcaremanagement.repositories.medicalrecord.MedicalRecordRepository;
import org.example.springhealthcaremanagement.repositories.medication.MedicationRepository;
import org.example.springhealthcaremanagement.repositories.patient.PatientRepository;
import org.example.springhealthcaremanagement.repositories.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MedicalRecordServiceTest extends SpringUnitBaseTest {
    @InjectMocks
    private MedicalRecordServiceImpl medicalRecordService;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private MedicalRecordMapper medicalRecordMapper;

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private UserRepository userRepository;
    @Test
    void save() {
        MedicalRecordRequestDto requestDto = new MedicalRecordRequestDto();
        requestDto.setDoctorUsername("doctor");
        requestDto.setPatientUsername("patient");
        requestDto.setPrescribedMedication(List.of(MedicationDto.builder().id(1L).build()));

        User doctorUser = User.builder().id(1L).build();
        User patientUser = User.builder().id(1L).build();
        Doctor doctor = Doctor.builder().id(1L).user(doctorUser).build();
        Patient patient = Patient.builder().id(1L).user(patientUser).build();
        Medication medication = Medication.builder().id(1L).build();

        MedicalRecord medicalRecord = MedicalRecord.builder()
                .doctor(doctor)
                .patient(patient)
                .prescribedMedication(List.of(medication))
                .build();

        MedicalRecord savedMedicalRecord = MedicalRecord.builder().id(1L).build();
        MedicalRecordDto responseDto = new MedicalRecordDto();

        when(medicalRecordMapper.requestDtoToEntity(requestDto)).thenReturn(medicalRecord);
        when(userRepository.findByUsername(requestDto.getDoctorUsername())).thenReturn(Optional.of(doctorUser));
        when(userRepository.findByUsername(requestDto.getPatientUsername())).thenReturn(Optional.of(patientUser));
        when(doctorRepository.findByUser(any())).thenReturn(Optional.of(doctor));
        when(patientRepository.findByUser(any())).thenReturn(Optional.of(patient));
        when(patientRepository.findById(any())).thenReturn(Optional.of(patient));
        when(medicationRepository.findById(any())).thenReturn(Optional.of(medication));
        when(medicalRecordRepository.save(any())).thenReturn(savedMedicalRecord);
        when(medicalRecordMapper.toDto(savedMedicalRecord)).thenReturn(responseDto);

        // Act
        MedicalRecordDto result = medicalRecordService.save(requestDto);

        // Assert
        assertEquals(responseDto, result);
    }

    @Test
    void update() {
        MedicalRecordDto requestDto = MedicalRecordDto.builder().id(1L).build();
        MedicalRecord medicalRecord = MedicalRecord.builder().id(1L).build();
        MedicalRecord updatedMedicalRecord = MedicalRecord.builder().id(1L).build();
        MedicalRecordDto responseDto = MedicalRecordDto.builder().id(1L).build();
        when(medicalRecordRepository.findById(requestDto.getId())).thenReturn(Optional.of(medicalRecord));
        when(medicalRecordRepository.save(any())).thenReturn(updatedMedicalRecord);
        when(medicalRecordMapper.toDto(updatedMedicalRecord)).thenReturn(responseDto);
        MedicalRecordDto result = medicalRecordService.update(requestDto);
        assertEquals(responseDto, result);
    }

    @Test
    void delete() {
        MedicalRecordDto requestDto = MedicalRecordDto.builder().id(1L).build();
        MedicalRecord medicalRecord = MedicalRecord.builder().id(1L).build();
        MedicalRecordDto responseDto = MedicalRecordDto.builder().id(1L).build();
        when(medicalRecordRepository.findById(requestDto.getId())).thenReturn(Optional.of(medicalRecord));
        when(medicalRecordMapper.toDto(medicalRecord)).thenReturn(responseDto);
        MedicalRecordDto result = medicalRecordService.delete(requestDto);
        assertEquals(responseDto.getId(), result.getId());
        assertEquals(0,medicalRecordRepository.findAll().size());
    }

    @Test
    void findAll() {
        MedicalRecord medicalRecord = new MedicalRecord();
        MedicalRecordDto responseDto = new MedicalRecordDto();
        when(medicalRecordRepository.findAll()).thenReturn(Collections.singletonList(medicalRecord));
        when(medicalRecordMapper.toDto(medicalRecord)).thenReturn(responseDto);
        List<MedicalRecordDto> result = medicalRecordService.findAll();
        assertEquals(Collections.singletonList(responseDto), result);
    }
}