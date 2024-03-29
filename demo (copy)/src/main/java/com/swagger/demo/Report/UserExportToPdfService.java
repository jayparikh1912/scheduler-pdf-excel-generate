package com.swagger.demo.Report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.swagger.demo.User.SystemRepository;
import com.swagger.demo.User.SystemSetting;
import com.swagger.demo.User.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class UserExportToPdfService extends ReportAbstract {

    @Autowired
    UserReportRepo userReportRepo;

    public void writeTableData(PdfPTable table, List<UserDTO> data) {
//        List<UserDTO> list = (List<UserDTO>) data;

        // for auto wide by paper  size
        table.setWidthPercentage(100);
        // cell
        PdfPCell cell = new PdfPCell();
        int number = 0;
        for (UserDTO item : data) {
            number += 1;
            cell.setPhrase(new Phrase(String.valueOf(number), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getUsername(), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getRoles(), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getPermissions(), getFontContent()));
            table.addCell(cell);

            String active = item.getActive() == 1 ? "Active" : "Non Active";
            cell.setPhrase(new Phrase(active, getFontContent()));
            table.addCell(cell);

            String blocked = item.getBlocked() == 1 ? "Blocked" : "Non Blocked";
            cell.setPhrase(new Phrase(blocked, getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getCreatedBy(), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getCreatedDate().toString(), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getUpdatedBy(), getFontContent()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(item.getUpdatedDate().toString(), getFontContent()));
            table.addCell(cell);
        }

    }


    public void exportToPDF(HttpServletResponse response) throws IOException, DocumentException {

        // get all user
        Document document = new Document(PageSize.A4);
        response = initResponseForExportPdf(response, "USER");
        PdfWriter.getInstance(document, response.getOutputStream());
        pdfCreate(document);
    }

    @Autowired
    private SystemRepository systemRepository;
    public String generatePdfToServer() throws IOException, DocumentException {
        String filePath = "/home/raapyd-workstation-1/Documents/temp/a.pdf";
        Path path = Paths.get("/home/raapyd-workstation-1/Documents/temp/");
        Files.createDirectories(path);
        try{
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(filePath)));
            pdfCreate(document);
            document.close();
//        SystemSetting systemSetting =  systemRepository.findByKeyAndIsDeletedIsFalse("USER_PDF_URL");
//        systemSetting.setValue(filePath);
//        systemRepository.save(systemSetting);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return filePath;
    }

    public void pdfCreate(Document document) throws DocumentException {

        // get all user
        List<UserDTO> data = userReportRepo.getUserList();

        // start document
        document.open();

        // title
        Paragraph title = new Paragraph("Report User", getFontTitle());
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        // subtitel
        Paragraph subtitel = new Paragraph("Report Date : " + new Date(), getFontSubtitle());
        subtitel.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(subtitel);

        enterSpace(document);

        // table header
        String[] headers = new String[]{"No", "username", "Roles", "Permission", "Active", "Blocked", "Created By", "Created Date", "Update By", "Update Date"};
        PdfPTable tableHeader = new PdfPTable(10);
        writeTableHeaderPdf(tableHeader, headers);
        document.add(tableHeader);

        // table content
        PdfPTable tableData = new PdfPTable(10);
        writeTableData(tableData, data);
        document.add(tableData);
    }


}
