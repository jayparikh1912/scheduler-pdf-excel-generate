package com.swagger.demo.Scheduler;
import com.itextpdf.text.DocumentException;
import com.swagger.demo.Report.UserExportToPdfService;
import com.swagger.demo.Report.UserReportService;
import com.swagger.demo.User.User;
import com.swagger.demo.User.UserRepo;
import com.swagger.demo.mail.scripts.MailService;
import com.swagger.demo.mail.templates.EmailTemplates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class ScheduledTasks {
    @Autowired
    private MailService emailService;
    @Autowired
    private UserRepo repo;
    String filePath = "/Documents/temp/user.pdf";

    @Autowired
    UserReportService userReportService;
    @Autowired
    UserExportToPdfService service;
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(cron = "30 54 17 1 * *")
    public void performTask() throws MessagingException, DocumentException, IOException {
        User user = repo.findByUsername("jayyyp19");
        logger.error("Task performed at {}", LocalDateTime.now());
//        emailService.sendNewMail(user.getEmail(), "Verify email", EmailTemplates.GITHUB_EMAIL_VERIFICATION);
        String path = service.generatePdfToServer();
        emailService.sendNewMail(user.getEmail(), "Verify email", EmailTemplates.GITHUB_EMAIL_VERIFICATION,path);
    }
}
