package application.ViewController;

import java.text.SimpleDateFormat;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Parts_page_controller {

    @FXML
    private Text nom_fact;
    @FXML
    private Text prenom_fact;

    @FXML
    private Text vin_text;

    @FXML
    private TableColumn<Parts, Integer> nom_part_fact;

    @FXML
    private TableColumn<Parts, Integer> prix_part_fact;

    @FXML
    private TableColumn<Parts, Integer> quant_part_fact;

    @FXML
    private TableView<Parts> table_facture;

    private Rendez_vous rdv_local;

    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    int sum;

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
        vin_text.setText(rdv_local.getCar_rdv().getVin());
        nom_part_fact.setCellValueFactory(new PropertyValueFactory<>("name"));
        prix_part_fact.setCellValueFactory(new PropertyValueFactory<>("price"));
        quant_part_fact.setCellValueFactory(new PropertyValueFactory<>("quntitie"));

        for (int i = 0; i < rdv_local.getParts().size(); i++) {

            sum = (rdv_local.getParts().get(i).getPrice() * rdv_local.getParts().get(i).getQuntitie()) + sum;
        }
        // table_facture.setFixedCellSize(22);
        table_facture.setItems(list);

        table_facture.setPrefHeight(table_facture.getFixedCellSize() * rdv_local.getParts().size() + 45);

    }

    @FXML
    void print(ActionEvent event) {

        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT,
                Printer.MarginType.HARDWARE_MINIMUM);

        // create a job and set the page layout
        PrinterJob job = PrinterJob.createPrinterJob(printer);
        job.getJobSettings().setPageLayout(pageLayout);

        // check if the job was created successfully
        if (job != null) {
            // set the content to be printed
            // job.showPrintDialog(Rdv_details.stage);
            job.printPage(grid);
            job.endJob();
        }
    }

}
