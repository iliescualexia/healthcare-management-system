package org.example.springhealthcaremanagement.dtos.appointment;

import jakarta.persistence.*;
import lombok.*;
import org.example.springhealthcaremanagement.dtos.appointmentstatus.AppointmentStatusDto;
import org.example.springhealthcaremanagement.dtos.doctor.DoctorDto;
import org.example.springhealthcaremanagement.dtos.patient.PatientDto;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private Long id;
    private PatientDto patient;
    private DoctorDto doctor;
    private String dateTime;
    private String reason;
    private AppointmentStatusDto status;

}
