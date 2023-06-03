package application.ViewController;

import java.text.SimpleDateFormat;

// import com.itextpdf.text.DocumentException;

import application.models.Parts;
import application.models.Rendez_vous;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
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

    @FXML
    private TextArea note_inp;

    private static Font headerfont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.WHITE);
    private static Font titlefont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
    private static Font tablefont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);

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

    private static PdfPCell createCell(String text, int alignment, String type) {
        PdfPCell cell = new PdfPCell(new Phrase(text, tablefont));
        cell.setHorizontalAlignment(alignment);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(17);
        if (type.equals("header")) {
            cell.setBorderWidth(1);
        }
        if (type.equals("data")) {
            cell.setBorderWidthBottom(0);
            cell.setBorderWidthTop(0);
        }
        return cell;
    }

    @FXML
    void print(ActionEvent event) {

        try {

            FileChooser fileChooser = new FileChooser();

            // Set the extension filter for PDF files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName("BonDeReglement - " +
                    DATE_FORMAT.format(
                            rdv_local.getDate_debut())
                    + " - " + rdv_local.getCar_rdv().getVin() + " .pdf");

            Stage primaryStage = new Stage();
            File file = fileChooser.showSaveDialog(primaryStage);

            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file.getAbsolutePath()));

            document.open();

            Paragraph infopr = new Paragraph();
            infopr.add(new Phrase("Email : ", titlefont));
            infopr.add(new Phrase("MECA-TECH@outlook.com           "));
            infopr.add(new Phrase("Telephone : ", titlefont));
            infopr.add(new Phrase("05-55-24-78-62"));
            infopr.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
            document.add(infopr);

            document.add(new Paragraph(5f, " "));

            PdfPTable tableheader = new PdfPTable(3);
            tableheader.setWidthPercentage(100);

            PdfPCell imageCell = new PdfPCell();
            BaseColor bgcolor = new BaseColor(60, 64, 72);
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

            document.add(new Paragraph(20f, " "));

            Paragraph clientpr = new Paragraph();
            clientpr.add(new Phrase("Client :  ", titlefont));
            clientpr.add(new Phrase(rdv_local.getClient_rdv().getNom() + "  " + rdv_local.getClient_rdv().getPrenom()));
            document.add(clientpr);

            Paragraph voiturepr = new Paragraph();
            voiturepr.add(new Phrase("Vehicule :  ", titlefont));
            voiturepr.add(
                    new Phrase(rdv_local.getCar_rdv().getMarque() + "  " + rdv_local.getCar_rdv().getModele() + "   "));
            voiturepr.add(new Phrase("VIN :  ", titlefont));
            voiturepr.add(new Phrase(rdv_local.getCar_rdv().getVin()));
            document.add(voiturepr);

            Paragraph rdvpr = new Paragraph();
            rdvpr.add(new Phrase("Date de Rendez-vous :  ", titlefont));
            rdvpr.add(new Phrase(DATE_FORMAT.format(rdv_local.getDate_debut()) + " / "
                    + DATE_FORMAT.format(rdv_local.getDate_fin())));
            document.add(rdvpr);

            Paragraph servicepr = new Paragraph();
            servicepr.add(new Phrase("Type de Service :  ", titlefont));
            servicepr.add(new Phrase(rdv_local.getService()));
            document.add(servicepr);

            Paragraph notepr = new Paragraph();
            notepr.add(new Phrase("Note :  ", titlefont));
            notepr.add(new Phrase(note_inp.getText()));
            document.add(notepr);

            document.add(new Paragraph(20f, " "));

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);

            table.addCell(createCell("Nom", PdfPCell.ALIGN_CENTER, "header")).setFixedHeight(20);
            ;
            table.addCell(createCell("Quantity", PdfPCell.ALIGN_CENTER, "header")).setFixedHeight(20);
            table.addCell(createCell("Prix", PdfPCell.ALIGN_CENTER, "header")).setFixedHeight(20);

            if (rdv_local.getParts() != null) {
                for (Parts row : rdv_local.getParts()) {
                    table.addCell(createCell(row.getName(), PdfPCell.ALIGN_CENTER, "data"));
                    table.addCell(createCell(Integer.toString(row.getQuntitie()),
                            PdfPCell.ALIGN_CENTER, "data"));
                    table.addCell(createCell(Integer.toString(row.getPrice()),
                            PdfPCell.ALIGN_CENTER, "data"));

                }
            }

            Paragraph prixpiecepr = new Paragraph();
            prixpiecepr.add(new Phrase("Prix total des pices :  ", titlefont));
            prixpiecepr.add(Chunk.NEWLINE);
            prixpiecepr.add(new Phrase(parts_price.getText() + " DA"));
            table.addCell(prixpiecepr);
            // document.add(prixpiecepr);

            Paragraph prixservicepr = new Paragraph();
            prixservicepr.add(new Phrase("Prix de service :  ", titlefont));
            prixservicepr.add(Chunk.NEWLINE);
            prixservicepr.add(new Phrase(Integer.toString(rdv_local.getPrix()) + " DA"));
            table.addCell(prixservicepr);
            // document.add(prixservicepr);

            Paragraph prixtotalpr = new Paragraph();
            prixtotalpr.add(new Phrase("Prix Total :  ", titlefont));
            prixtotalpr.add(Chunk.NEWLINE);
            prixtotalpr.add(new Phrase(Integer.toString(rdv_local.getPrix() + sum) + " DA"));
            table.addCell(prixtotalpr);
            // document.add(prixtotalpr);

            document.add(table);

            document.close();

            System.out.println("Pdf generated successfully");

        } catch (Exception e) {
            System.err.println("Error generating invoice: " + e.getMessage());
        }
    }

}