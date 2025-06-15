package org.github.gabrielgodoi.gtsolarbackend.services.externals;


import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.config.Globals;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.github.gabrielgodoi.gtsolarbackend.services.externals.pdfgenerator.PDFGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final PDFGeneratorService pdfGeneratorService;
    private final Globals globals;

    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no_reply@gtsolar.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject((subject));
        mailSender.send(message);
        System.out.println("Mail Sent Succesfully...");
    }

    public void sendHtmlEmail(String toEmail, String subject, String htmlBody){
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom("support@gtsolar.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlBody, true); // <- IMPORTANTE: "true" para HTML
            mailSender.send(mimeMessage);
            System.out.println("HTML Mail sent successfully...");
        } catch (MessagingException e) {
            System.out.println("Failed to send HTML email: " + e.getMessage());
            throw new RuntimeException("Falha ao enviar e-mail HTML", e);
        }
    }

    public void sendHtmlEmailWithAttachment(String toEmail, String subject, String htmlBody, Project project) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom("support@gtsolar.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);


            DataSource pdfDataSource = this.pdfGeneratorService.generateProjectPdf(project);
            helper.addAttachment("orcamento_" + project.getId() + ".pdf", pdfDataSource);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
            throw new RuntimeException("Falha ao enviar e-mail com anexo", e);
        }
    }

}