package application.ViewController;

import java.text.SimpleDateFormat;

import application.models.Parts;
import application.models.Rendez_vous;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

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

    private Rendez_vous rdv_local;

    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

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

        nom_part_fact.setCellValueFactory(new PropertyValueFactory<>("name"));
        prix_part_fact.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quant_part_fact.setCellValueFactory(new PropertyValueFactory<>("quntitie"));

        table_facture.setItems(list);

    }
}
