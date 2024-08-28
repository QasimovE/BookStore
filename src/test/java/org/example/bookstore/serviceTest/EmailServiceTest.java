package org.example.bookstore.serviceTest;

import org.example.bookstore.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {
    @InjectMocks
    private EmailService emailService;
    @Mock
    private  JavaMailSender javaMailSender;

    @Test
    public void sendEmailTest(){
        SimpleMailMessage msg=new SimpleMailMessage();
        javaMailSender.send(msg);
        emailService.sendEmail();
    }
}
