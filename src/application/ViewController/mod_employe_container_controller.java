package application.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;

import application.controller.AdminController;
import application.models.Usermodel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class mod_employe_container_controller {

    @FXML
    private TextField email_field_mod;

    @FXML
    private TextField name_field_mod;

    @FXML
    private TextField numero_field_mod;

    @FXML
    private TextField prenom_field_mod;

    @FXML
    private TextField password_field_mod;

    @FXML
    private TextField username_field_mod;

    @FXML
    private BorderPane mod_container;

    private Usermodel user_local;

    @FXML
    private ChoiceBox<String> role_select;

    @FXML
    private Button retour_btn;

    public void retour() {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/add_employe_dashbord.fxml"));
            mod_container.getChildren().removeAll();
            mod_container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void modifier_employer() {

        Document newemp = new Document("username", username_field_mod.getText());
        newemp.append("_id", user_local.getId());
        newemp.append("nom", name_field_mod.getText());
        newemp.append("nomutil", username_field_mod.getText());
        newemp.append("prenom", prenom_field_mod.getText());
        newemp.append("motdepass", password_field_mod.getText());
        newemp.append("tel", numero_field_mod.getText());
        newemp.append("role", role_select.getValue());
        newemp.append("email", email_field_mod.getText());
        // newemp.append("password",email_field_mod.getText());
        AdminController.UpdateEmp(newemp);

        System.out.println("test hna1");

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/add_employe_dashbord.fxml"));
            mod_container.getChildren().removeAll();
            mod_container.getChildren().setAll(fxml);
            System.out.println("test hna 2");

        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("test hna 3");

    }

    public void getuser(Usermodel user) {

        // role_select.getItems().add("Admin");
        // role_select.getItems().add("Caissier");
        // role_select.getItems().add("technicien");
        ObservableList<String> options = FXCollections.observableArrayList("Admin",
                "Caissier", "technicien");

        role_select.setItems(options);
        user_local = user;

        username_field_mod.setText(user_local.getUsername());
        name_field_mod.setText(user_local.getNom());
        prenom_field_mod.setText(user_local.getPrenom());
        numero_field_mod.setText(user_local.getNumero());
        email_field_mod.setText(user_local.getEmail());
        password_field_mod.setText(user_local.getPassword());
        role_select.setValue(user_local.getRole());

    }

}