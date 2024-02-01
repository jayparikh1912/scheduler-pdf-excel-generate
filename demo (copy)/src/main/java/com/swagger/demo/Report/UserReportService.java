package com.swagger.demo.Report;

import com.itextpdf.text.DocumentException;
import com.swagger.demo.User.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class UserReportService {

    @Autowired
    UserExportToPdfService userExportToPdfService;

    @Autowired
    UserExportToExcelService userExportToExcelService;

    @Autowired
    UserReportRepo userReportRepo;


    public void exportToPdf(HttpServletResponse response) throws IOException, DocumentException {
        // export to pdf
        userExportToPdfService.exportToPDF(response);
    }


    public void exportToExcel(HttpServletResponse response) throws IOException {
        // get all user
        List<UserDTO> data = userReportRepo.getUserList();

        // export to pdf
        userExportToExcelService.exportToExcel(response, data);

    }


}
