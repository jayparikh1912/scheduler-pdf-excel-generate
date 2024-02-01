package com.swagger.demo.User;

import com.swagger.demo.mail.scripts.MailService;
import com.swagger.demo.mail.templates.EmailTemplates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepo repo;


    @Autowired
    private MailService emailService;

    @PostMapping("/save")
    private String save(@RequestBody User user) {
        repo.save(user);
        return "saved";
    }

    @GetMapping("/emailVerify/{username}")
    public String verifyEmail(@PathVariable String username) throws MessagingException {
        User user = repo.findByUsername(username);
        emailService.sendNewMail(user.getEmail(), "Verify email", EmailTemplates.GITHUB_EMAIL_VERIFICATION);
        return "Email sent successfully!";
    }
}
