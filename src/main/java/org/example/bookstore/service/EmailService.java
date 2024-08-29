package org.example.bookstore.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class EmailService {
    private final JavaMailSender javaMailSender;

    public void sendEmail(){
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setFrom("YOUR_EMAIL_FROM");
        msg.setTo("YOUR_EMAIL_TO");
        msg.setSubject("Book");
        msg.setText("New Book");
        javaMailSender.send(msg);
    }

}
