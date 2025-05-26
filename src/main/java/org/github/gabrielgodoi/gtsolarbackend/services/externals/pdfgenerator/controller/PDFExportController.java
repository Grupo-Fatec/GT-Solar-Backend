package org.github.gabrielgodoi.gtsolarbackend.services.externals.pdfgenerator.controller;

import java.util.Date;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletResponse;
import org.github.gabrielgodoi.gtsolarbackend.services.externals.pdfgenerator.PDFGeneratorService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pdf")
public class PDFExportController {

    private final PDFGeneratorService pdfGeneratorService;

    // public PDFExportController(PDFGeneratorService pdfGeneratorService) {
    //     this.pdfGeneratorService = pdfGeneratorService;
    // }

    @GetMapping("/generate")
    @ResponseBody
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response);
    }

}
