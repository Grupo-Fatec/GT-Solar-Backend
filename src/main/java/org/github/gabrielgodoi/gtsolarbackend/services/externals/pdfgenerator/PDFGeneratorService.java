package org.github.gabrielgodoi.gtsolarbackend.services.externals.pdfgenerator;

import com.itextpdf.text.BaseColor;
import jakarta.activation.DataSource;
import jakarta.mail.util.ByteArrayDataSource;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.github.gabrielgodoi.gtsolarbackend.entities.Supplier.Equipment;
import org.springframework.stereotype.Service;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("This is a title.", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph2 = new Paragraph("This is a paragraph.", fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraph2);
        document.close();
    }

    public DataSource generateProjectPdf(Project project) throws IOException, DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, baos);

        document.open();

        // Define Fonts
        Font fontShopName = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        Font fontShopData = FontFactory.getFont(FontFactory.HELVETICA, 10);
        Font fontQuoteNumber = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        Font fontHeaderLabel = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
        Font fontHeaderValue = FontFactory.getFont(FontFactory.HELVETICA);
        Font fontSectionTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        Font fontTableColumnHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Font fontTableCell = FontFactory.getFont(FontFactory.HELVETICA, 9);
        Font fontTotalsLabel = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        Font fontTotalsValue = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);


        // --- Header Section ---
        PdfPTable headerMainTable = new PdfPTable(2);
        headerMainTable.setWidthPercentage(100);
        headerMainTable.setWidths(new float[]{1f, 2f});
        headerMainTable.setSpacingAfter(10f);
        headerMainTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        // Column 1: Logo
        PdfPCell logoCell = new PdfPCell();
        logoCell.setBorder(Rectangle.NO_BORDER);
        logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        try {
            // Replace with the actual path to your logo image
            // Make sure the logo path is correct and accessible.
            // For example, if it's in resources: getClass().getResource("/logo.png")
            // Or a direct file path: "file:///C:/path/to/logo.png"
            Image logo = Image.getInstance(new URL("https://d1yjjnpx0p53s8.cloudfront.net/styles/logo-original-577x577/s3/062011/gt-solar.png?itok=QQxaX4_0")); // Placeholder path
            logo.scaleToFit(100, 50); // Adjust size as needed
            logoCell.addElement(logo);
        } catch (BadElementException | IOException e) {
            System.err.println("Error loading logo: " + e.getMessage());
            // Add a placeholder or handle the error gracefully
            logoCell.addElement(new Paragraph("Logo Placeholder", fontShopName));
        }
        headerMainTable.addCell(logoCell);

        // Column 2: Shop Info
        PdfPCell shopInfoCell = new PdfPCell();
        shopInfoCell.setBorder(Rectangle.NO_BORDER);
        shopInfoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

        Paragraph shopName = new Paragraph("Loja Homologacao", fontShopName);
        shopName.setAlignment(Element.ALIGN_RIGHT);
        shopInfoCell.addElement(shopName);

        Paragraph shopData = new Paragraph("Dados da loja de homologação.", fontShopData);
        shopData.setAlignment(Element.ALIGN_RIGHT);
        shopInfoCell.addElement(shopData);

        headerMainTable.addCell(shopInfoCell);
        document.add(headerMainTable);

        // --- Quote Number Section ---
        Paragraph quoteNumber = new Paragraph("ORÇAMENTO N° " + project.getId(), fontQuoteNumber);
        quoteNumber.setAlignment(Element.ALIGN_CENTER);
        quoteNumber.setSpacingAfter(15f);
        document.add(quoteNumber);

        // --- Main Information Table ---
        PdfPTable infoTable = new PdfPTable(4); // 4 columns: Label, Value, Label, Value
        infoTable.setWidthPercentage(100);
        infoTable.setWidths(new float[]{0.8f, 1.5f, 0.8f, 1.5f});
        infoTable.setSpacingAfter(20f);

        // Client Name
        infoTable.addCell(createLabeledCell("NOME DO CLIENTE", fontHeaderLabel));
        infoTable.addCell(createValueCell(project.getClient().getName(), fontHeaderValue));

        // Project Name
        infoTable.addCell(createLabeledCell("NOME DO PROJETO", fontHeaderLabel));
        infoTable.addCell(createValueCell(project.getName(), fontHeaderValue));

        // Quote Date
        infoTable.addCell(createLabeledCell("DATA DO ORÇAMENTO", fontHeaderLabel));
        infoTable.addCell(createValueCell("2019-11-06 12:59:06", fontHeaderValue)); // Placeholder for date

        // Quote Validity
        infoTable.addCell(createLabeledCell("VALIDADE DO ORÇAMENTO", fontHeaderLabel));
        infoTable.addCell(createValueCell("10 dias uteis", fontHeaderValue)); // Placeholder for validity

        // Delivery Date
        infoTable.addCell(createLabeledCell("DATA DE ENTREGA", fontHeaderLabel));
        infoTable.addCell(createValueCell("20 dias uteis apos pagamento", fontHeaderValue)); // Placeholder for delivery

        // Add dummy cells to complete the row if needed, or adjust table width/columns
        infoTable.addCell(createLabeledCell("", fontHeaderLabel)); // Empty cell
        infoTable.addCell(createValueCell("", fontHeaderValue)); // Empty cell
        document.add(infoTable);

        // --- Project Items Section ---
        Paragraph projectItemsTitle = new Paragraph("ITENS DO PROJETO", fontSectionTitle);
        projectItemsTitle.setSpacingAfter(5f);
        document.add(projectItemsTitle);

        PdfPTable itemsTable = new PdfPTable(2);
        itemsTable.setWidthPercentage(100);
        itemsTable.setWidths(new float[]{4f, 1f}); // Adjust column widths for "Componente" and "Valor"

        // Column headers with bottom border
        PdfPCell componentHeader = new PdfPCell(new Phrase("Componente", fontTableColumnHeader));
        componentHeader.setBorder(Rectangle.BOTTOM);
        componentHeader.setBorderWidth(1f);
        componentHeader.setPaddingBottom(5f);
        itemsTable.addCell(componentHeader);

        PdfPCell valorHeader = new PdfPCell(new Phrase("Valor", fontTableColumnHeader));
        valorHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
        valorHeader.setBorder(Rectangle.BOTTOM);
        valorHeader.setBorderWidth(1f);
        valorHeader.setPaddingBottom(5f);
        itemsTable.addCell(valorHeader);


        // Add project items (Equipments)
        for (Equipment eq : project.getEquipments()) {
            PdfPCell componentCell = new PdfPCell(new Phrase(eq.getName(), fontTableCell));
            componentCell.setBorder(Rectangle.NO_BORDER);
            componentCell.setPaddingTop(3f);
            componentCell.setPaddingBottom(3f);
            itemsTable.addCell(componentCell);

            PdfPCell priceCell = new PdfPCell(new Phrase("R$ " + String.format("%.2f", eq.getPrice()), fontTableCell));
            priceCell.setBorder(Rectangle.NO_BORDER);
            priceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            priceCell.setPaddingTop(3f);
            priceCell.setPaddingBottom(3f);
            itemsTable.addCell(priceCell);
        }
        document.add(itemsTable);

        // --- Additional Items Section (as per image) ---
        Paragraph additionalItemsTitle = new Paragraph("ITENS ADICIONAIS", fontSectionTitle);
        additionalItemsTitle.setSpacingBefore(15f);
        additionalItemsTitle.setSpacingAfter(5f);
        document.add(additionalItemsTitle);

        PdfPTable additionalItemsTable = new PdfPTable(2);
        additionalItemsTable.setWidthPercentage(100);
        additionalItemsTable.setWidths(new float[]{4f, 1f});

        PdfPCell additionalNameHeader = new PdfPCell(new Phrase("Nome", fontTableColumnHeader));
        additionalNameHeader.setBorder(Rectangle.BOTTOM);
        additionalNameHeader.setBorderWidth(1f);
        additionalNameHeader.setPaddingBottom(5f);
        additionalItemsTable.addCell(additionalNameHeader);

        PdfPCell additionalQuantityHeader = new PdfPCell(new Phrase("Quantidade", fontTableColumnHeader));
        additionalQuantityHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
        additionalQuantityHeader.setBorder(Rectangle.BOTTOM);
        additionalQuantityHeader.setBorderWidth(1f);
        additionalQuantityHeader.setPaddingBottom(5f);
        additionalItemsTable.addCell(additionalQuantityHeader);

        // Add placeholder for additional items if any (loop similar to equipments)
        // For demonstration, adding an empty row
        PdfPCell emptyNameCell = new PdfPCell(new Phrase("", fontTableCell));
        emptyNameCell.setBorder(Rectangle.NO_BORDER);
        PdfPCell emptyQuantityCell = new PdfPCell(new Phrase("", fontTableCell));
        emptyQuantityCell.setBorder(Rectangle.NO_BORDER);
        additionalItemsTable.addCell(emptyNameCell);
        additionalItemsTable.addCell(emptyQuantityCell);

        document.add(additionalItemsTable);


        // --- Observations Section ---
        Paragraph observationsTitle = new Paragraph("OBSERVACOES", fontSectionTitle);
        observationsTitle.setSpacingBefore(20f);
        observationsTitle.setSpacingAfter(5f);
        document.add(observationsTitle);

        // Add a placeholder for actual observations content if needed
        PdfPTable obsContentTable = new PdfPTable(1);
        obsContentTable.setWidthPercentage(100);
        PdfPCell obsContentCell = new PdfPCell(new Phrase("", fontTableCell)); // Placeholder for observation text
        obsContentCell.setBorder(Rectangle.BOX); // Add a box border as seen in the image (though subtle)
        obsContentCell.setBorderWidth(0.5f);
        obsContentCell.setMinimumHeight(50f); // Adjust height as needed
        obsContentTable.addCell(obsContentCell);
        document.add(obsContentTable);


        // --- Totals Section ---
        double subtotal = project.getEquipments().stream()
                .mapToDouble(Equipment::getPrice).sum();

        PdfPTable totalTable = new PdfPTable(2);
        totalTable.setWidthPercentage(50); // Set to 50% width as in the image
        totalTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalTable.setSpacingBefore(20f); // Spacing before the total table

        // Helper method to add total rows
        addTotalRow(totalTable, "SUBTOTAL", subtotal, fontTotalsLabel, fontTotalsValue);
        addTotalRow(totalTable, "FRETE", 0.00, fontTotalsLabel, fontTotalsValue);
        addTotalRow(totalTable, "VALOR ADICIONAL", 0.00, fontTotalsLabel, fontTotalsValue);
        addTotalRow(totalTable, "DESCONTO", 0.00, fontTotalsLabel, fontTotalsValue);

        // Total do Orçamento (with a thicker top border)
        PdfPCell totalLabelCell = new PdfPCell(new Phrase("TOTAL DO ORÇAMENTO", fontTotalsLabel));
        totalLabelCell.setBorder(Rectangle.TOP);
        totalLabelCell.setBorderWidth(1f); // Thicker top border
        totalLabelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        totalLabelCell.setPaddingTop(5f);
        totalLabelCell.setPaddingBottom(5f);
        totalTable.addCell(totalLabelCell);

        PdfPCell totalValueCell = new PdfPCell(new Phrase("R$ " + String.format("%.2f", subtotal), fontTotalsValue));
        totalValueCell.setBorder(Rectangle.TOP);
        totalValueCell.setBorderWidth(1f); // Thicker top border
        totalValueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalValueCell.setPaddingTop(5f);
        totalValueCell.setPaddingBottom(5f);
        totalTable.addCell(totalValueCell);


        document.add(totalTable);
        document.close();

        return new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
    }

    private PdfPCell createCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(0); // Remove padding by default for tighter layout
        return cell;
    }

    // Helper method to create a cell for a label in the info table
    private PdfPCell createLabeledCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingBottom(2f); // Small padding for spacing
        return cell;
    }

    // Helper method to create a cell for a value in the info table, with a bottom border
    private PdfPCell createValueCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.BOTTOM); // Only bottom border
        cell.setBorderWidth(0.5f); // Thin border
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingBottom(2f); // Small padding for spacing
        return cell;
    }

    // Helper method to add a row to the totals table
    private void addTotalRow(PdfPTable table, String label, double value, Font labelFont, Font valueFont) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        labelCell.setPaddingBottom(3f);
        table.addCell(labelCell);

        PdfPCell valueCell = new PdfPCell(new Phrase("R$ " + String.format("%.2f", value), valueFont));
        valueCell.setBorder(Rectangle.NO_BORDER);
        valueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        valueCell.setPaddingBottom(3f);
        table.addCell(valueCell);
    }
}
