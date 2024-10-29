package org.example.springhealthcaremanagement.mappers;

import org.example.springhealthcaremanagement.dtos.appointment.AppointmentDto;
import org.example.springhealthcaremanagement.dtos.appointment.AppointmentRequestDto;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorRequestDto;
import org.example.springhealthcaremanagement.entities.appointment.Appointment;
import org.example.springhealthcaremanagement.entities.doctor.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring",uses = {PatientMapper.class, DoctorMapper.class, AppointmentStatusMapper.class})
public interface AppointmentMapper {
    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "localDateTimeToString")
    AppointmentDto toDto(Appointment appointment);

    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "stringToLocalDateTime")
    Appointment toEntity(AppointmentDto appointmentDto);
    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "stringToLocalDateTime")
    Appointment requestDtoToEntity(AppointmentRequestDto appointmentRequestDto);

    @Named("localDateTimeToString")
    default String mapLocalDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    @Named("stringToLocalDateTime")
    default LocalDateTime mapStringToLocalDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}