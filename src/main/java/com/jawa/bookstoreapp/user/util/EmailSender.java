package com.jawa.bookstoreapp.user.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    @Autowired
    private final JavaMailSender emailSender;


    public EmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendMail(String toEmail, String subject, String body) {
        if (toEmail != null && !toEmail.isEmpty()) { // Validate toEmail
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("jawaharbhaskaran@gmail.com");
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);

            emailSender.send(message);
            System.out.println("Mail sent to the user successfully!");
        } else {
            System.out.println("Cannot send email: recipient email address is null or empty");
        }
    }
}