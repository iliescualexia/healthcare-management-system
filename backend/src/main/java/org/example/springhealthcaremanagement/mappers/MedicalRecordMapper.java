package org.example.springhealthcaremanagement.mappers;

import org.example.springhealthcaremanagement.dtos.appointment.AppointmentDto;
import org.example.springhealthcaremanagement.dtos.appointment.AppointmentRequestDto;
import org.example.springhealthcaremanagement.dtos.medicalrecord.MedicalRecordDto;
import org.example.springhealthcaremanagement.dtos.medicalrecord.MedicalRecordRequestDto;
import org.example.springhealthcaremanagement.entities.appointment.Appointment;
import org.example.springhealthcaremanagement.entities.medicalrecord.MedicalRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring",uses = {PatientMapper.class, DoctorMapper.class, MedicationMapper.class})
public interface MedicalRecordMapper {
    @Mapping(source = "dateOfVisit", target = "dateOfVisit", qualifiedByName = "localDateTimeToString")
    MedicalRecordDto toDto(MedicalRecord medicalRecord);

    @Mapping(source = "dateOfVisit", target = "dateOfVisit", qualifiedByName = "stringToLocalDateTime")
    MedicalRecord toEntity(MedicalRecordDto medicalRecordDto);
    @Mapping(source = "dateOfVisit", target = "dateOfVisit", qualifiedByName = "stringToLocalDateTime")
    MedicalRecord requestDtoToEntity(MedicalRecordRequestDto medicalRecordRequestDto);
    @Named("localDateTimeToString")
    default String mapLocalDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    @Named("stringToLocalDateTime")
    default LocalDateTime mapStringToLocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
