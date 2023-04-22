package application.ViewController;

import java.io.IOException;
import java.text.SimpleDateFormat;

// import com.itextpdf.text.DocumentException;
// import com.itextpdf.layout.element;
import application.models.Parts;
import application.models.Rendez_vous;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;



import java.io.IOException;

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
        javafx.print.Printer printer = javafx.print.Printer.getDefaultPrinter();
        javafx.print.PrinterJob job = javafx.print.PrinterJob.createPrinterJob(printer);
        if (job != null) {
            job.printPage(grid);
            job.endJob();
        }
    }

    @FXML
    void save(ActionEvent event)  {

    }

 
}
