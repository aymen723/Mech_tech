package application.ViewController;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import application.models.Parts;
import application.models.Rendez_vous;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.application.Platform;
// import javafx.embed.swing.SwingFXUtils;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class facture {

    @FXML
    private Text nom_fact;
    @FXML
    private Text prenom_fact;
    @FXML
    private Text car_fact;

    @FXML
    private Text descrp_fact;

    @FXML
    private Text date_debut_fact;

    @FXML
    private Text date_fin_fact;

    @FXML
    private Text service_fact;

    @FXML
    private TableColumn<Parts, Integer> nom_part_fact;

    @FXML
    private TableColumn<Parts, Integer> prix_part_fact;

    @FXML
    private TableColumn<Parts, Integer> quant_part_fact;

    @FXML
    private TableView<Parts> table_facture;

    @FXML
    private Text prix_fact;

    private Rendez_vous rdv_local;

    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    int sum;

    @FXML
    private Text parts_price;

    @FXML
    private Button btn_print;

    @FXML
    private Button btn_save;

    @FXML
    private GridPane grid;

    public void Gettrdv(Rendez_vous rdv) {

        rdv_local = rdv;
        ObservableList<Parts> list = FXCollections.observableArrayList(rdv_local.getParts());

        nom_fact.setText(rdv_local.getClient_rdv().getNom());
        prenom_fact.setText(rdv_local.getClient_rdv().getPrenom());
        car_fact.setText(rdv_local.getCar_model());
        descrp_fact.setText(rdv_local.getDescrption_in());

        date_debut_fact.setText(DATE_FORMAT.format(rdv_local.getDate_debut()));
        date_fin_fact.setText(DATE_FORMAT.format(rdv_local.getDate_fin()));
        service_fact.setText(rdv_local.getService());
        prix_fact.setText(Integer.toString(rdv_local.getPrix()));
        nom_part_fact.setCellValueFactory(new PropertyValueFactory<>("name"));
        prix_part_fact.setCellValueFactory(new PropertyValueFactory<>("price"));
        quant_part_fact.setCellValueFactory(new PropertyValueFactory<>("quntitie"));

        for (int i = 0; i < rdv_local.getParts().size(); i++) {

            sum = (rdv_local.getParts().get(i).getPrice() * rdv_local.getParts().get(i).getQuntitie()) + sum;
        }

        parts_price.setText(Integer.toString(sum));
        table_facture.setItems(list);

    }

    @FXML
    void print(ActionEvent event) {
        // Print the report using the default printer
        javafx.print.Printer printer = javafx.print.Printer.getDefaultPrinter();
        javafx.print.PrinterJob job = javafx.print.PrinterJob.createPrinterJob(printer);
        if (job != null) {
            job.printPage(nom_fact);
            job.endJob();
        }
    }

    @FXML
    void save(ActionEvent event) throws IOException, DocumentException {
        // Use a FileChooser to prompt the user for the save location
        // FileChooser fileChooser = new FileChooser();
        // fileChooser.setTitle("Save Report As PDF");
        // File file = fileChooser.showSaveDialog(nom_fact.getScene().getWindow());
        // if (file != null) {
        // // Create a new PDF document and writer
        // Document document = new Document();
        // try {
        // PdfWriter.getInstance(document, new FileOutputStream(file));
        // } catch (FileNotFoundException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (DocumentException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        // // Open the document and add the report text as a paragraph
        // document.open();
        // document.add(new Paragraph(nom_fact.getText()));

        // // Close the document
        // document.close();
        // }
        // facture.saveAsPDF(grid, "test.pdf");

    }

    // public static void saveAsPDF(Node nodeToPrint, String filePath) {
    // Platform.runLater(() -> {
    // Printer printer = Printer.getDefaultPrinter();
    // PageLayout pageLayout = printer.createPageLayout(Paper.A4,
    // PageOrientation.PORTRAIT,
    // Printer.MarginType.DEFAULT);

    // double scaleX = pageLayout.getPrintableWidth() /
    // nodeToPrint.getBoundsInParent().getWidth();
    // double scaleY = scaleX;

    // WritableImage image = nodeToPrint.snapshot(new SnapshotParameters(), null);
    // Stage dummy = new Stage();
    // Scene scene = new Scene(new HBox());
    // dummy.setScene(scene);

    // File file = new File(filePath);
    // try {
    // file.createNewFile();
    // FileOutputStream fos = new FileOutputStream(file);

    // double dpi = Toolkit.getDefaultToolkit().getScreenResolution();
    // int pixelWidth = (int) Math.round(scaleX * image.getWidth());
    // int pixelHeight = (int) Math.round(scaleY * image.getHeight());

    // // BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
    // BufferedImage bufferedResized = new BufferedImage(pixelWidth, pixelHeight,
    // bufferedImage.getType());
    // Graphics2D g = bufferedResized.createGraphics();
    // g.drawImage(bufferedImage, 0, 0, pixelWidth, pixelHeight, null);
    // g.dispose();

    // ImageIO.write(bufferedResized, "png", fos);
    // fos.flush();
    // fos.close();

    // PrinterJob job = PrinterJob.createPrinterJob();
    // if (job != null) {
    // job.getJobSettings().setPageLayout(pageLayout);
    // if (job.showPrintDialog(dummy)) {
    // boolean success = job.printPage(nodeToPrint);
    // if (success) {
    // job.endJob();
    // }
    // }
    // }
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // });
    // }
}
