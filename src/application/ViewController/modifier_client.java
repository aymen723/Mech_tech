package application.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;

import application.controller.AdminController;
import application.models.Clientmodel;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;

public class modifier_client implements Initializable {
    @FXML
    private BorderPane mod_container;

    @FXML
    private TextField address_field;

    @FXML
    private TextField email_field;

    @FXML
    private Button modifier_client;

    @FXML
    private TextField name_field;

    @FXML
    private TextField numero_field;

    @FXML
    private TextField pernom_field;

    @FXML
    private Button return_back;

    private ProgressIndicator progressIndicator = new ProgressIndicator();

    @FXML
    void modifierClient(ActionEvent event) {
        if ((name_field.getText().trim().isEmpty() == false) &&
                (pernom_field.getText().trim().isEmpty() == false) &&
                (email_field.getText().trim().isEmpty() == false) &&
                (address_field.getText().trim().isEmpty() == false) &&
                (numero_field.getText().trim().isEmpty() == false) &&
                (Client_dashbord.client != null)

        ) {

            System.out.println(Client_dashbord.client.getId());

            Document newemp = new Document("_id", Client_dashbord.client.getId());
            newemp.append("nom", name_field.getText());
            newemp.append("prenom", pernom_field.getText());
            newemp.append("email", email_field.getText());
            newemp.append("tel", numero_field.getText());
            newemp.append("adresse", address_field.getText());

            Task<Integer> updateTask = new Task<Integer>() {

                @Override
                protected Integer call() throws Exception {

                    AdminController.UpdateClient(newemp, Client_dashbord.client);
                    return 0;
                }

            };

            Alert loadingAlert = new Alert(AlertType.INFORMATION);
            loadingAlert.setTitle("Loading");
            loadingAlert.setHeaderText("Please wait...");
            loadingAlert.setContentText("Loading data from the server...");
            loadingAlert.initOwner(mod_container.getScene().getWindow());
            loadingAlert.setGraphic(progressIndicator);
            DialogPane dialogPane = loadingAlert.getDialogPane();
            dialogPane.getStylesheets()
                    .add(getClass().getResource("/application/Viewfxml/part_style.css")
                            .toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane ");

            loadingAlert.initStyle(StageStyle.UNDECORATED);

            // Show the loading indicator and start the data loading task
            loadingAlert.show();
            Thread dataThread = new Thread(updateTask);
            dataThread.start();

            // Handle task completion
            updateTask.setOnSucceeded(ev -> {
                Platform.runLater(() -> {
                    loadingAlert.close();
                });
            });

            // Handle task failure
            updateTask.setOnFailed(ev -> {
                // Display an error message
                Platform.runLater(() -> {
                    loadingAlert.close();
                    showErrorAlert("Data Loading Error", "Failed to load data from the server.");
                });
            });

            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Client_dashbord.fxml"));
                mod_container.getChildren().removeAll();
                mod_container.getChildren().setAll(fxml);

            } catch (Exception e) {
                // TODO: handle exception
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

    private void showErrorAlert(String title, String message) {
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setTitle(title);
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(message);
        errorAlert.initOwner(mod_container.getScene().getWindow());
        errorAlert.showAndWait();
    }

    @FXML
    void return_back(ActionEvent event) {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Client_dashbord.fxml"));
            mod_container.getChildren().removeAll();
            mod_container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'initialize'");

        name_field.setText(Client_dashbord.client.getNom());
        pernom_field.setText(Client_dashbord.client.getPrenom());
        address_field.setText(Client_dashbord.client.getAddresse());
        email_field.setText(Client_dashbord.client.getEmail());
        numero_field.setText(Client_dashbord.client.getNumero());

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
