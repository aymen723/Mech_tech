package application.ViewController;

import org.bson.Document;

import application.controller.AdminController;
import application.models.Usermodel;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;

public class profile_controller {

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

    private ProgressIndicator progressIndicator = new ProgressIndicator();

    private Usermodel user = new Usermodel();

    public Usermodel getUser() {
        return user;

    }

    public void setUser(Usermodel userr) {
        this.user = userr;

        username_field_mod.setText(user.getUsername());
        name_field_mod.setText(user.getNom());
        prenom_field_mod.setText(user.getPrenom());
        numero_field_mod.setText(user.getNumero());
        email_field_mod.setText(user.getEmail());
        password_field_mod.setText(user.getPassword());
        role_select.setValue(user.getRole());

    }

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
        newemp.append("_id", user.getId());
        newemp.append("nom", name_field_mod.getText());
        newemp.append("nomutil", username_field_mod.getText());
        newemp.append("prenom", prenom_field_mod.getText());
        newemp.append("motdepass", password_field_mod.getText());
        newemp.append("tel", numero_field_mod.getText());
        newemp.append("role", role_select.getValue());
        newemp.append("email", email_field_mod.getText());
        // newemp.append("password",email_field_mod.getText());

        Task<Integer> updateTask = new Task<Integer>() {

            @Override
            protected Integer call() throws Exception {

                AdminController.UpdateEmp(newemp, user);
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

        System.out.println("test hna1");

    }

    private void showErrorAlert(String title, String message) {
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setTitle(title);
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(message);
        errorAlert.initOwner(mod_container.getScene().getWindow());
        errorAlert.showAndWait();
    }

}
