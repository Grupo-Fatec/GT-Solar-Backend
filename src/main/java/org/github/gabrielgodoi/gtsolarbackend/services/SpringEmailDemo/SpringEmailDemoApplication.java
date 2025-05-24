package org.github.gabrielgodoi.gtsolarbackend.services.SpringEmailDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@SpringBootApplication
public class SpringEmailDemoApplication {
    @Autowired
    private EmailSenderService senderService;
    
    public static void main(String[] args) {
        SpringApplication.run(SpringEmailDemoApplication.class, args);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() {
        senderService.sendEmail(
                "vitortctavares@gmail.com",
                "This is the subject",
                "This is the body of the Email");
    }
}
