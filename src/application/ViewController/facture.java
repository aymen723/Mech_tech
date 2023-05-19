package application.ViewController;

import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;

// import com.itextpdf.text.DocumentException;

import application.models.Parts;
import application.models.Rendez_vous;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class facture {

    @FXML
    private Text nom_fact;
    @FXML
    private Text prenom_fact;
    @FXML
    private Text car_fact;

    @FXML
    private Text date_debut_fact;
    @FXML
    private Text vin_text;

    @FXML
    private Text date_fin_fact;

    @FXML
    private Text service_fact;

    // @FXML
    // private TableColumn<Parts, Integer> nom_part_fact;

    // @FXML
    // private TableColumn<Parts, Integer> prix_part_fact;

    // @FXML
    // private TableColumn<Parts, Integer> quant_part_fact;

    // @FXML
    // private TableView<Parts> table_facture;

    @FXML
    private Text prix_fact;

    @FXML
    private Text total_fact;

    private Rendez_vous rdv_local;

    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    int sum;

    @FXML
    private Text parts_price;

    @FXML
    private Button btn_print;

    // @FXML
    // private Button btn_save;

    @FXML
    private GridPane grid;

    @FXML
    private AnchorPane pane;

    @FXML
    private Pane factpane;

    String logoPath = "E:/aymen/javavs/projects/Mech_tech/src/application/ViewController/logo.png";

    public void Gettrdv(Rendez_vous rdv) {

        rdv_local = rdv;
        ObservableList<Parts> list = FXCollections.observableArrayList(rdv_local.getParts());

        nom_fact.setText(rdv_local.getClient_rdv().getNom());
        prenom_fact.setText(rdv_local.getClient_rdv().getPrenom());
        car_fact.setText(rdv_local.getCar_model());
        vin_text.setText(rdv_local.getCar_rdv().getVin());
        date_debut_fact.setText(DATE_FORMAT.format(rdv_local.getDate_debut()));
        date_fin_fact.setText(DATE_FORMAT.format(rdv_local.getDate_fin()));
        service_fact.setText(rdv_local.getService());
        prix_fact.setText(Integer.toString(rdv_local.getPrix()));
        // nom_part_fact.setCellValueFactory(new PropertyValueFactory<>("name"));
        // prix_part_fact.setCellValueFactory(new PropertyValueFactory<>("price"));
        // quant_part_fact.setCellValueFactory(new PropertyValueFactory<>("quntitie"));

        for (int i = 0; i < rdv_local.getParts().size(); i++) {

            sum = (rdv_local.getParts().get(i).getPrice() * rdv_local.getParts().get(i).getQuntitie()) + sum;
        }

        parts_price.setText(Integer.toString(sum));
        total_fact.setText(Integer.toString(rdv_local.getPrix() + sum));
        // table_facture.setFixedCellSize(25);
        // table_facture.setItems(list);

        // table_facture.setPrefHeight(table_facture.getFixedCellSize() *
        // rdv_local.getParts().size() + 47);

    }

    @FXML
    void print(ActionEvent event) {

        // Printer printer = Printer.getDefaultPrinter();
        // PageLayout pageLayout = printer.createPageLayout(Paper.A4,
        // PageOrientation.PORTRAIT,
        // Printer.MarginType.HARDWARE_MINIMUM);

        // // create a job and set the page layout
        // PrinterJob job = PrinterJob.createPrinterJob(printer);
        // job.getJobSettings().setPageLayout(pageLayout);

        // // check if the job was created successfully
        // if (job != null) {
        // // set the content to be printed
        // // job.showPrintDialog(Rdv_details.stage);
        // job.printPage(grid);
        // job.endJob();
        // }

        // try (PDDocument document = new PDDocument()) {
        // PDPage page = new PDPage();
        // document.addPage(page);

        // PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // // Setting the font and size
        // // contentStream.setFont(PDType1Font.class., 12);
        // PDType1Font font = PDType1Font.HELVETICA; // Change the font here

        // contentStream.setFont(font, 12);
        // // Adding content to the page
        // contentStream.beginText();
        // contentStream.newLineAtOffset(25, 1000);
        // contentStream.showText("Invoice Number: " + rdv_local.getCar_rdv().getVin());
        // contentStream.newLine();
        // contentStream.showText("Customer: " + rdv_local.getClient_rdv().getNom());
        // // contentStream.newLine();
        // // contentStream.showText("Total Amount: $" + parts_price.getText());
        // // contentStream.endText();

        // // Closing the content stream
        // contentStream.close();

        // // Saving the document
        // document.save("invoice.pdf");

        // System.out.println("Invoice generated successfully.");
        // } catch (IOException e) {
        // System.err.println("Error generating invoice: " + e.getMessage());
        // }

        // JFileChooser fileChooser = new JFileChooser();
        // fileChooser.setDialogTitle("Save PDF File");
        // int userSelection = fileChooser.showSaveDialog(null);

        // if (userSelection == JFileChooser.APPROVE_OPTION) {
        // File fileToSave = fileChooser.getSelectedFile();

        // try (PDDocument document = new PDDocument()) {
        // PDPage page = new PDPage(PDRectangle.A4);
        // document.addPage(page);

        // int pageh = (int) page.getTrimBox().getHeight();
        // int pagew = (int) page.getTrimBox().getWidth();

        // PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // // Setting the font and size
        // PDType1Font font = PDType1Font.HELVETICA_BOLD;
        // float fontSize = 12;
        // float leading = 1.5f * fontSize;
        // int coll = 2;
        // contentStream.setFont(font, fontSize);
        // contentStream.setLeading(14.5f); // set the size of the newline to something
        // reasonable

        // // Adding content to the page
        // contentStream.beginText();
        // contentStream.newLineAtOffset(25, page.getMediaBox().getHeight() - 25);
        // contentStream.showText(
        // "MECA-TECH" + " " + "Bon de Reglement");
        // contentStream.newLine();
        // contentStream.setStrokingColor(Color.black);
        // contentStream.setLineWidth(1);
        // contentStream.newLine();
        // contentStream.newLine();
        // contentStream.showText(
        // "Client : " + rdv_local.getClient_rdv().getNom() + " "
        // + rdv_local.getClient_rdv().getPrenom());
        // contentStream.newLine();
        // contentStream.showText("Model de Voiture : " + " " +
        // rdv_local.getCar_rdv().getMarque() + " "
        // + rdv_local.getCar_rdv().getModele());
        // contentStream.newLine();
        // contentStream.showText("Vin : " + rdv_local.getCar_rdv().getVin());
        // contentStream.newLine();
        // contentStream
        // .showText("Date de Rendez-vous :" + rdv_local.getDate_debut() + " " +
        // rdv_local.getDate_fin());
        // contentStream.newLine();
        // contentStream
        // .showText("Type de Service: " + rdv_local.getService());
        // contentStream.newLine();
        // contentStream.newLine();

        // if (rdv_local.getParts() != null) {

        // for (int i = 0; i < rdv_local.getParts().size(); i++) {

        // for (int j = 0; i <= 2; i++) {

        // contentStream.showText(rdv_local.getParts().get(j).getName());
        // contentStream.newLine();

        // }
        // }
        // }

        // contentStream.endText();
        // // Closing the content stream
        // contentStream.close();

        // // Saving the document to the selected file
        // document.save(fileToSave);
        // System.out.println("Invoice generated and saved successfully.");
        // } catch (IOException e) {
        // System.err.println("Error generating invoice: " + e.getMessage());
        // }
        // } else {
        // System.out.println("Invoice generation canceled by the user.");
        // }

        try {

            // JFileChooser fileChooser = new JFileChooser();
            // int option = fileChooser.showSaveDialog(null);

            // // if (option == JFileChooser.APPROVE_OPTION) {
            // String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            // Create a new Document
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));

            // Open the Document
            document.open();

            PdfPTable tabletitle = new PdfPTable(3);
            tabletitle.setWidthPercentage(100);

            // Add logo cell
            PdfPCell logoCell = new PdfPCell();
            Image logoImage = Image.getInstance(logoPath);
            logoImage.scaleToFit(100, 100); // Adjust the size of the logo
            logoCell.addElement(logoImage);
            logoCell.setBorder(Rectangle.NO_BORDER);
            tabletitle.addCell(logoCell);
            // Adding content to the Document
            PdfPCell paragraphCell = new PdfPCell();

            Font titleFont = new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1250, BaseFont.EMBEDDED),
                    18);
            Font contentFont = new Font(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED),
                    12);

            paragraphCell.addElement(new Paragraph("MECA-TECH",
                    titleFont));
            // paragraphCell.addElement(new Paragraph("Bon de Reglement",
            // titleFont));

            tabletitle.addCell(paragraphCell);

            document.add(tabletitle);

            document.add(new Paragraph(20f, " "));

            document.add(new Paragraph(
                    "Client : " + rdv_local.getClient_rdv().getNom() + " " + rdv_local.getClient_rdv().getPrenom()));
            document.add(new Paragraph("Model de Voiture : " + " " +
                    rdv_local.getCar_rdv().getMarque() + " "
                    + rdv_local.getCar_rdv().getModele()));
            document.add(new Paragraph("Date de Rendez-vous :" + rdv_local.getDate_debut() + " " +
                    rdv_local.getDate_fin()));
            document.add(new Paragraph("Vin : " + rdv_local.getCar_rdv().getVin()));
            document.add(new Paragraph("Type de Service: " + rdv_local.getService()));
            document.add(new Paragraph(20f, " "));

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);

            // Add table headers
            table.addCell(createCell("Nom", PdfPCell.ALIGN_LEFT));
            table.addCell(createCell("Quantity", PdfPCell.ALIGN_CENTER));
            table.addCell(createCell("Prix", PdfPCell.ALIGN_CENTER));

            // Add table data

            if (rdv_local.getParts() != null) {
                for (Parts row : rdv_local.getParts()) {
                    table.addCell(createCell(row.getName(), PdfPCell.ALIGN_LEFT));
                    table.addCell(createCell(Integer.toString(row.getQuntitie()),
                            PdfPCell.ALIGN_CENTER));
                    table.addCell(createCell(Integer.toString(row.getPrice()),
                            PdfPCell.ALIGN_CENTER));

                }
            }

            // Add the table to the Document

            document.add(table);

            document.add(new Paragraph("Prix total des pices : " + parts_price.getText()));
            document.add(new Paragraph("Prix de service : " + rdv_local.getPrix()));
            document.add(new Paragraph("Prix Total : " + Integer.toString(rdv_local.getPrix() + sum)));

            // Close the Document
            document.close();

            System.out.println("Invoice generated successfully.");
        } catch (Exception e) {
            System.err.println("Error generating invoice: " + e.getMessage());
        }
    }

    private static PdfPCell createCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setHorizontalAlignment(alignment);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }
}