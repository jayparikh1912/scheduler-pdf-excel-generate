package com.swagger.demo.Report;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/v1/report/user")
public class UserReportController {

    @Autowired
    UserReportService userReportService;

    @GetMapping("/pdf/all")
    public void exportToPdf(HttpServletResponse response) throws IOException, DocumentException {
        this.userReportService.exportToPdf(response);
    }


    @GetMapping("/excel/all")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        this.userReportService.exportToExcel(response);
    }
}