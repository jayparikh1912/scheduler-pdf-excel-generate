package com.swagger.demo.mail.scripts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }

    public void sendNewMail(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        helper.setFrom("demo@jayparikh.com");

        mailSender.send(message);
    }

    public void sendNewMail(String to, String subject, String htmlBody, String file) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        FileSystemResource pdfFile = new FileSystemResource(file);
        helper.addAttachment("Your_PDF_File.pdf", pdfFile);
        helper.setText(htmlBody, true);
        helper.setFrom("demo@jayparikh.com");

        mailSender.send(message);
    }
}
