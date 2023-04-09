package application.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;

import application.controller.AdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Ajouter_client implements Initializable {

    @FXML
    private BorderPane add_container;

    @FXML
    private TextField address_field;

    @FXML
    private Button ajouter_client;

    @FXML
    private TextField email_field;

    @FXML
    private TextField name_field;

    @FXML
    private TextField numero_field;

    @FXML
    private TextField pernom_field;

    @FXML
    private Button return_back;

    @FXML
    void ajouter_client(ActionEvent event) {
        if ((name_field.getText().trim().isEmpty() == false) &&
                (pernom_field.getText().trim().isEmpty() == false) &&
                (email_field.getText().trim().isEmpty() == false) &&
                (address_field.getText().trim().isEmpty() == false) &&
                (numero_field.getText().trim().isEmpty() == false)

        ) {
            Document addclient = new Document("nom", name_field.getText());
            addclient.append("prenom", pernom_field.getText());
            addclient.append("email", email_field.getText());
            addclient.append("adresse", address_field.getText());
            addclient.append("tel", numero_field.getText());

            AdminController.AddClient(addclient);
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Client_dashbord.fxml"));
                add_container.getChildren().removeAll();
                add_container.getChildren().setAll(fxml);

            } catch (Exception e) {
                
            }
        } else {
            if (name_field.getText().trim().isEmpty() == true) {

                name_field.getStyleClass().add("inptempty");
            }
            if (pernom_field.getText().trim().isEmpty() == true) {

                pernom_field.getStyleClass().add("inptempty");
            }
            if (email_field.getText().trim().isEmpty() == true) {

                email_field.getStyleClass().add("inptempty");
            }

            if (address_field.getText().trim().isEmpty() == true) {

                address_field.getStyleClass().add("inptempty");
            }

            if (numero_field.getText().trim().isEmpty() == true) {

                numero_field.getStyleClass().add("inptempty");
            }

        }
    }

    @FXML
    void return_back(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Client_dashbord.fxml"));
            add_container.getChildren().removeAll();
            add_container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'initialize'");

        name_field.textProperty().addListener((observable, oldValue, newValue) -> {
            // Your code here to handle the text field value change
            System.out.println("Text changed from " + oldValue + " to " + newValue);
            if (name_field.getText().trim().isEmpty() == false) {
                name_field.getStyleClass().remove("inptempty");
            }

        });
        pernom_field.textProperty().addListener((observable, oldValue, newValue) -> {
            // Your code here to handle the text field value change
            System.out.println("Text changed from " + oldValue + " to " + newValue);
            if (pernom_field.getText().trim().isEmpty() == false) {
                pernom_field.getStyleClass().remove("inptempty");
            }

        });
        email_field.textProperty().addListener((observable, oldValue, newValue) -> {
            // Your code here to handle the text field value change
            System.out.println("Text changed from " + oldValue + " to " + newValue);
            if (email_field.getText().trim().isEmpty() == false) {
                email_field.getStyleClass().remove("inptempty");
            }

        });
        numero_field.textProperty().addListener((observable, oldValue, newValue) -> {
            // Your code here to handle the text field value change
            System.out.println("Text changed from " + oldValue + " to " + newValue);
            if (numero_field.getText().trim().isEmpty() == false) {
                numero_field.getStyleClass().remove("inptempty");
            }
        });
        address_field.textProperty().addListener((observable, oldValue, newValue) -> {
            // Your code here to handle the text field value change
            System.out.println("Text changed from " + oldValue + " to " + newValue);
            if (address_field.getText().trim().isEmpty() == false) {
                address_field.getStyleClass().remove("inptempty");
            }
        });
    }
}
