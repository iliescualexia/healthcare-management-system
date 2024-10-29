package org.example.springhealthcaremanagement.services.messages;

import org.example.springhealthcaremanagement.entities.appointment.Appointment;
import org.example.springhealthcaremanagement.entities.patient.Patient;

public interface SmsService {
     void importContacts(String fileUrl);
     boolean createNewContact(Patient patient);
     boolean sendSms(Appointment appointment);
}
