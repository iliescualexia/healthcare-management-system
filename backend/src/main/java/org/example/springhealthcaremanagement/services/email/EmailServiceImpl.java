package org.example.springhealthcaremanagement.services.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.example.springhealthcaremanagement.entities.appointment.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService{
    private JavaMailSender emailSender;
    @Override
    public void sendMail(Appointment appointment){
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("alexiailiescu.projects@gmail.com");
            helper.setTo(appointment.getPatient().getUser().getEmail());
            helper.setSubject("Reminder Appointment");
            helper.setText("Hello " +appointment.getPatient().getFirstName()+" "+appointment.getPatient().getLastName()
                    +  ", this is a reminder for your appointment on " + appointment.getDateTime() + " with Dr. " + appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName(), true);
            emailSender.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }

    }
}
