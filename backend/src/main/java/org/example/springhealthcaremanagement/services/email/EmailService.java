package org.example.springhealthcaremanagement.services.email;

import org.example.springhealthcaremanagement.entities.appointment.Appointment;
import org.springframework.messaging.MessagingException;

public interface EmailService{
 void sendMail(Appointment appointment);
}
