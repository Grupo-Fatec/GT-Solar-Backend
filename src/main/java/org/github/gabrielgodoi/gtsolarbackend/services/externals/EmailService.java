package org.github.gabrielgodoi.gtsolarbackend.services.externals;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        // Set the sender's email address
        // Note: In a real application, you would typically set this to a configured email address
        message.setFrom("your-email@example.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject((subject));

        mailSender.send(message);

        System.out.println("Mail Sent Succesfully...");
    }
}