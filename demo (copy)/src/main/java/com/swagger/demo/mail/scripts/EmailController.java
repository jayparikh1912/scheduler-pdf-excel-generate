package com.swagger.demo.mail.scripts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class EmailController {

    @Autowired
    private MailService emailService;

    public EmailController(MailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/sendEmail")
    public String sendEmail() throws MessagingException {
        emailService.sendNewMail("jayparikh7174@gmail.com", "Subject right here", "Body right there!");
        return "Email sent successfully!";
    }
}
