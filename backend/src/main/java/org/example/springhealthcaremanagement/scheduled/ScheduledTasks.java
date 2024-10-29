package org.example.springhealthcaremanagement.scheduled;

import lombok.RequiredArgsConstructor;
import org.example.springhealthcaremanagement.entities.appointment.Appointment;
import org.example.springhealthcaremanagement.entities.appointmentstatus.AppointmentStatus;
import org.example.springhealthcaremanagement.entities.appointmentstatus.EAppointmentStatus;
import org.example.springhealthcaremanagement.exception.EntityNotFoundException;
import org.example.springhealthcaremanagement.repositories.appointment.AppointmentRepository;
import org.example.springhealthcaremanagement.repositories.appointmentstatus.AppointmentStatusRepository;
import org.example.springhealthcaremanagement.services.email.EmailService;
import org.example.springhealthcaremanagement.services.messages.SmsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final AppointmentRepository appointmentRepository;
    private final SmsService smsService;
    private final EmailService emailService;
    private final AppointmentStatusRepository appointmentStatusRepository;
    @Scheduled(fixedRate = 120000) // Runs every 2 minutes
    private void updatePastAppointmentsStatus() {
        LocalDateTime currentTime = LocalDateTime.now();
        AppointmentStatus missedStatus = findAppointmentStatusByName("MISSED");
        List<Appointment> pastAppointments = appointmentRepository.findAllByDateTimeBefore(currentTime);

        for (Appointment appointment : pastAppointments) {
            if (appointment.getStatus().getName().equals(EAppointmentStatus.PENDING) || appointment.getStatus().getName().equals(EAppointmentStatus.CONFIRMED)) {
                LocalDateTime oneHourAfterAppointment = appointment.getDateTime().plusHours(1);
                if (currentTime.isAfter(oneHourAfterAppointment)) {
                    appointment.setStatus(missedStatus);
                    appointmentRepository.save(appointment);
                }
            }
        }
    }
    @Scheduled(fixedRate = 120000) // Runs every 2 minutes
    private void sendRemindersForAppointmentsTheNextDay() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime tomorrow = currentTime.plusDays(1);
        AppointmentStatus appointmentStatus = findAppointmentStatusByName("CONFIRMED");
        List<Appointment> appointments = appointmentRepository.findAppointmentByDateTimeBetweenAndStatus(tomorrow.withHour(0).withMinute(0).withSecond(0), tomorrow.withHour(23).withMinute(59).withSecond(59), appointmentStatus);

        for (Appointment appointment : appointments) {
            smsService.sendSms(appointment);
            emailService.sendMail(appointment);
        }
    }
    private AppointmentStatus findAppointmentStatusByName(String name){
        return appointmentStatusRepository.findByName(EAppointmentStatus.valueOf(name)).orElseThrow(() -> new EntityNotFoundException("Appointment status not found"));
    }
}
