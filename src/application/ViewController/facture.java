package application.ViewController;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;

// import com.itextpdf.text.DocumentException;

import application.models.Parts;
import application.models.Rendez_vous;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

// import org.apache.pdfbox.pdmodel.PDDocument;
// import org.apache.pdfbox.pdmodel.PDPage;
// import org.apache.pdfbox.pdmodel.PDPageContentStream;
// import org.apache.pdfbox.pdmodel.common.PDRectangle;
// import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfAppearance;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfWriter.PdfBody;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

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
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

            Font headerfont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.WHITE);
            Font titlefont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
            // Create a new Document
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("invoice.pdf"));

            // Open the Document
            document.open();

            // Adding content to the Document

            PdfPTable tableheader = new PdfPTable(3);
            tableheader.setWidthPercentage(100);

            PdfPCell imageCell = new PdfPCell();
            BaseColor bgcolor = new BaseColor(69, 69, 69);
            imageCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            imageCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            imageCell.setPadding(5);
            imageCell.setFixedHeight(40);
            imageCell.setBorder(0);
            imageCell.setBackgroundColor(bgcolor);

            Image image = Image.getInstance("src/pics/Asset 1.png");

            image.scaleToFit(100, 100);

            imageCell.addElement(image);

            PdfPCell boncell = new PdfPCell(new Phrase("Bon de Réglement", headerfont));
            boncell.setBorder(0);
            boncell.setHorizontalAlignment(Element.ALIGN_CENTER);
            boncell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            boncell.setBackgroundColor(bgcolor);

            PdfPCell mecacell = new PdfPCell(new Phrase("MECA-TECH", headerfont));
            mecacell.setBorder(0);
            mecacell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            mecacell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            mecacell.setBackgroundColor(bgcolor);

            tableheader.addCell(imageCell);
            tableheader.addCell(boncell);
            tableheader.addCell(mecacell);

            document.add(tableheader);

            // document.add(new Paragraph(50f, "MECA-TECH" + " " + "Bon de Reglement"));
            document.add(new Paragraph(20f, " "));

            // PdfPTable infotable = new PdfPTable(2);
            // PdfPCell clientcell = new PdfPCell(new Phrase("", titlefont)
            Paragraph clientpr = new Paragraph();
            clientpr.add(new Phrase("Client :  ", titlefont));
            clientpr.add(new Phrase(rdv_local.getClient_rdv().getNom() + "  " + rdv_local.getClient_rdv().getPrenom()));
            document.add(clientpr);

            Paragraph voiturepr = new Paragraph();
            voiturepr.add(new Phrase("Vehicule :  ", titlefont));
            voiturepr.add(new Phrase(rdv_local.getCar_rdv().getMarque() + "  " + rdv_local.getCar_rdv().getModele()));
            document.add(voiturepr);

            Paragraph rdvpr = new Paragraph();
            rdvpr.add(new Phrase("Date de Rendez-vous :  ", titlefont));
            rdvpr.add(new Phrase(DATE_FORMAT.format(rdv_local.getDate_debut()) + " / "
                    + DATE_FORMAT.format(rdv_local.getDate_fin())));
            document.add(rdvpr);

            Paragraph vinpr = new Paragraph();
            vinpr.add(new Phrase("VIN :  ", titlefont));
            vinpr.add(new Phrase(rdv_local.getCar_rdv().getVin()));
            document.add(vinpr);

            Paragraph servicepr = new Paragraph();
            servicepr.add(new Phrase("Type de Service :  ", titlefont));
            servicepr.add(new Phrase(rdv_local.getService()));
            document.add(servicepr);

            // document.add(new Paragraph("Date de Rendez-vous :" +
            // rdv_local.getDate_debut() + " " +
            // rdv_local.getDate_fin()));
            // document.add(new Paragraph("Vin : " + rdv_local.getCar_rdv().getVin()));
            // document.add(new Paragraph("Type de Service: " + rdv_local.getService()));
            document.add(new Paragraph(20f, " "));

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);

            // Add table headers
            table.addCell(createCell("Nom", PdfPCell.ALIGN_CENTER)).setFixedHeight(20);
            ;
            table.addCell(createCell("Quantity", PdfPCell.ALIGN_CENTER)).setFixedHeight(20);
            table.addCell(createCell("Prix", PdfPCell.ALIGN_CENTER)).setFixedHeight(20);

            // Add table data

            if (rdv_local.getParts() != null) {
                for (Parts row : rdv_local.getParts()) {
                    table.addCell(createCell(row.getName(), PdfPCell.ALIGN_CENTER)).setFixedHeight(18);
                    table.addCell(createCell(Integer.toString(row.getQuntitie()),
                            PdfPCell.ALIGN_CENTER)).setFixedHeight(18);
                    table.addCell(createCell(Integer.toString(row.getPrice()),
                            PdfPCell.ALIGN_CENTER)).setFixedHeight(18);

                }
            }

            // Add the table to the Document

            document.add(table);

            Paragraph prixpiecepr = new Paragraph();
            prixpiecepr.add(new Phrase("Prix total des pices :  ", titlefont));
            prixpiecepr.add(new Phrase(parts_price.getText() + " DA"));

            document.add(prixpiecepr);

            Paragraph prixservicepr = new Paragraph();
            prixservicepr.add(new Phrase("Prix de service :  ", titlefont));
            prixservicepr.add(new Phrase(Integer.toString(rdv_local.getPrix()) + " DA"));

            document.add(prixservicepr);

            Paragraph prixtotalpr = new Paragraph();
            prixtotalpr.add(new Phrase("Prix Total :  ", titlefont));
            prixtotalpr.add(new Phrase(Integer.toString(rdv_local.getPrix() + sum) + " DA"));

            document.add(prixtotalpr);

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