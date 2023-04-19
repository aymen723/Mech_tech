package application.ViewController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.bson.Document;
import org.bson.types.ObjectId;

import application.controller.AdminController;
import application.models.Clientmodel;
import application.models.Parts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Ajouter_rdv implements Initializable {

    @FXML
    private Pane add_container;

    @FXML
    private TextField car_model;

    @FXML
    private TextField service_field;

    @FXML
    private ComboBox<Clientmodel> comobbox;

    @FXML
    private CheckBox invite_check;

    @FXML
    private TextArea description_in;

    @FXML
    private TextField nom_client;

    @FXML
    private TextField numero_client;

    @FXML
    private TextField prenom_client;

    @FXML
    private TextField prix;

    @FXML
    private TextField serch;

    @FXML
    private ContextMenu context;

    @FXML
    private DatePicker date_fin_rdv;

    @FXML
    private DatePicker datedebut;

    @FXML
    private Button btn_rdv;

    Clientmodel client_golbal;

    @FXML
    void date_dd(ActionEvent event) {
    }

    @FXML
    void date(ActionEvent event) {
        System.out.println(date_fin_rdv.getValue());
    }

    @FXML
    void add_rdv(ActionEvent event) {

        Clientmodel newclient;
        Document clientrdv = new Document();

        Document newrdv = new Document("date_debut", datedebut.getValue());
        
        newrdv.append("date_fin", date_fin_rdv.getValue());
        newrdv.append("descrption_in", description_in.getText());
        newrdv.append("descrption_out", null);
        newrdv.append("service", service_field.getText());
        newrdv.append("etat", "en attente");

        newrdv.append("car model", car_model.getText());
        newrdv.append("prix", Integer.parseInt(prix.getText()));
        if (invite_check.isSelected() == true) {

            newclient = new Clientmodel(nom_client.getText(), prenom_client.getText(), numero_client.getText());
            clientrdv = new Document("nom", newclient.getNom());
            clientrdv.append("prenom", newclient.getPrenom());
            clientrdv.append("numero", newclient.getNumero());

        } else {
            newclient = new Clientmodel(client_golbal.getId(), client_golbal.getNom(), client_golbal.getPrenom(),
                    client_golbal.getEmail(), client_golbal.getAddresse(), client_golbal.getNumero());
            clientrdv = new Document("_id", new ObjectId(newclient.getId()));
            clientrdv.append("nom", newclient.getNom());
            clientrdv.append("prenom", newclient.getPrenom());
            clientrdv.append("tel", newclient.getNumero());
            clientrdv.append("email", newclient.getEmail());
            clientrdv.append("addresse", newclient.getAddresse());

            // clientrdv = AdminController.findclientbyid(newclient.getId());

        }
        newrdv.append("client", clientrdv);
        newrdv.append("parts", new ArrayList<Parts>());
        AdminController.addrdv(newrdv);
        System.out.println(newrdv);

    }

    ObservableList<Clientmodel> list = FXCollections.observableArrayList(
            new Clientmodel("1", "client1", "cleint1", "5555", "address", "email"),
            new Clientmodel("2", "client2", "cleint2", "5555", "address", "email"));

    @FXML
    void invite_check(ActionEvent event) {

        // System.out.println(invite_check.isSelected());
        if (invite_check.isSelected() == true) {
            serch.setDisable(true);
            nom_client.setEditable(true);
            prenom_client.setEditable(true);
            numero_client.setEditable(true);
        } else {
            serch.setDisable(false);
            nom_client.setEditable(false);
            prenom_client.setEditable(false);
            numero_client.setEditable(false);

        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'initialize'");
        list = AdminController.ListClient();
        serch.setDisable(false);
        nom_client.setEditable(false);
        prenom_client.setEditable(false);
        numero_client.setEditable(false);

        serch.textProperty().addListener((observable, oldValue, newValue) -> {

            context.getItems().clear();
            for (Clientmodel client : list) {
                if (client.getNom().toLowerCase().contains(newValue.toLowerCase())) {
                    MenuItem item = new MenuItem(client.getNom() + " " + client.getPrenom());
                    item.setOnAction(e -> {
                        // do something with selected client
                        System.out.println(client.toString());
                        client_golbal = client;
                        nom_client.setText(client.getNom());
                        prenom_client.setText(client.getPrenom());
                        numero_client.setText(client.getNumero());

                    });

                    context.getItems().add(item);

                }

            }

        });

        // Show context menu below search field when clicked
        serch.setOnMouseClicked(e -> {
            if(serch.getText().trim() != null ){
                
            context.show(serch, Side.BOTTOM, 0, 0);}
        });

        // Hide context menu when search field loses focus
        serch.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                context.hide();
            }
        });

    }

}
