package org.example.springhealthcaremanagement.mappers;

import org.example.springhealthcaremanagement.dtos.appointmentstatus.AppointmentStatusDto;
import org.example.springhealthcaremanagement.dtos.specialization.SpecializationDto;
import org.example.springhealthcaremanagement.entities.appointmentstatus.AppointmentStatus;
import org.example.springhealthcaremanagement.entities.appointmentstatus.EAppointmentStatus;
import org.example.springhealthcaremanagement.entities.specialization.ESpecialization;
import org.example.springhealthcaremanagement.entities.specialization.Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentStatusMapper {
    @Mapping(source = "name", target = "name")
    AppointmentStatusDto toDto(AppointmentStatus appointmentStatus);
    @Mapping(source = "name", target = "name")
    AppointmentStatus toEntity(AppointmentStatusDto appointmentStatusDto);

    default String map(EAppointmentStatus eAppointmentStatus) {
        return eAppointmentStatus != null ? eAppointmentStatus.name() : null;
    }

    default EAppointmentStatus map(String eAppointmentStatus) {
        return eAppointmentStatus != null ? EAppointmentStatus.valueOf(eAppointmentStatus) : null;
    }
}
