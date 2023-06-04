package application.ViewController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;

import application.controller.AdminController;
import application.models.Car;
import application.models.Rendez_vous;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Car_details {

    @FXML
    private TableColumn<Rendez_vous, Void> action_col;

    @FXML
    private Button btn_mod;

    @FXML
    private TableView<Rendez_vous> car_rdv;

    @FXML
    private TextField couleur;

    @FXML
    private TableColumn<Rendez_vous, String> dd_col;

    @FXML
    private TableColumn<Rendez_vous, String> df_col;

    @FXML
    private TableColumn<Rendez_vous, String> etat_col;

    @FXML
    private TextField marque;

    @FXML
    private TextField matricule;

    @FXML
    private TextField modele;

    @FXML
    private TableColumn<Rendez_vous, String> nom_col;

    @FXML
    private TableColumn<Rendez_vous, String> prenom_col;

    @FXML
    private Pane rdv_container;

    @FXML
    private Button retour;

    @FXML
    private TextField vin;

    private ProgressIndicator progressIndicator = new ProgressIndicator();

    private Car car_local;

    ObservableList<Rendez_vous> list_rdv = FXCollections.observableArrayList();

    private void loadData() {
        // Create a task to load the data in the background
        Task<ArrayList<Rendez_vous>> loadTask = new Task<ArrayList<Rendez_vous>>() {
            @Override
            protected ArrayList<Rendez_vous> call() throws Exception {
                // Perform your data loading operation here
                Thread.sleep(2000);
                return AdminController.RdvListCar(car_local.getVin());
            }
        };

        // Set up loading indicator
        Alert loadingAlert = new Alert(AlertType.INFORMATION);
        loadingAlert.setTitle("Loading");
        loadingAlert.setHeaderText("Please wait...");
        loadingAlert.setContentText("Loading data from the server...");
        loadingAlert.initOwner(car_rdv.getScene().getWindow());
        loadingAlert.setGraphic(progressIndicator);
        DialogPane dialogPane = loadingAlert.getDialogPane();
        dialogPane.getStylesheets()
                .add(getClass().getResource("/application/Viewfxml/part_style.css")
                        .toExternalForm());
        dialogPane.getStyleClass().add("dialog-pane ");

        loadingAlert.initStyle(StageStyle.UNDECORATED);

        // Show the loading indicator and start the data loading task
        loadingAlert.show();
        Thread dataThread = new Thread(loadTask);
        dataThread.start();

        // Handle task completion
        loadTask.setOnSucceeded(event -> {
            // Retrieve the loaded data from the task
            ArrayList<Rendez_vous> listrdv = loadTask.getValue();

            // Update the UI with the loaded data
            Platform.runLater(() -> {
                ObservableList<Rendez_vous> list = FXCollections.observableArrayList(listrdv);
                car_rdv.setItems(list);
                loadingAlert.close();
            });
        });

        // Handle task failure
        loadTask.setOnFailed(event -> {
            // Display an error message
            Platform.runLater(() -> {
                loadingAlert.close();
                showErrorAlert("Data Loading Error", "Failed to load data from the server.");
            });
        });
    }

    private void showErrorAlert(String title, String message) {
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setTitle(title);
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(message);
        errorAlert.initOwner(car_rdv.getScene().getWindow());
        errorAlert.showAndWait();
    }

    public void getcar(Car car) {
        this.car_local = car;

        marque.setText(car_local.getMarque());
        modele.setText(car_local.getModele());
        matricule.setText(car_local.getMatricule());
        couleur.setText(car_local.getCouleur());
        vin.setText(car_local.getVin());

        // list_rdv =
        // FXCollections.observableArrayList(AdminController.RdvListCar(car_local.getVin()));

        ChangeListener<Scene> chl = new ChangeListener<Scene>() {

            @Override
            public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
                if (newValue != null) {
                    loadData();
                }
            }

        };
        car_rdv.sceneProperty().addListener(chl);

        nom_col.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getClient_rdv().getNom()));

        prenom_col.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getClient_rdv().getNom()));
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

        dd_col.setCellValueFactory(

                cellData -> new SimpleStringProperty(DATE_FORMAT.format(cellData.getValue().getDate_debut())));

        df_col.setCellValueFactory(

                cellData -> new SimpleStringProperty(DATE_FORMAT.format(cellData.getValue().getDate_fin())));
        etat_col.setCellValueFactory(new PropertyValueFactory<>("etat"));

        action_col.setCellFactory(column -> {

            return new TableCell<Rendez_vous, Void>() {

                private final Button copybutton = new Button("");

                {

                    copybutton.setStyle(
                            "-fx-background-radius: 5em; -fx-min-width: 25px; -fx-min-height: 25px; -fx-max-width: 25px; -fx-max-height: 25px; -fx-background-color: transparent; -fx-alignment:CENTER;");

                    Image image_copy = new Image(getClass().getResourceAsStream("copy.png"));
                    ImageView img_copy = new ImageView(image_copy);
                    img_copy.setFitHeight(25);
                    img_copy.setFitWidth(25);
                    copybutton.setGraphic(img_copy);

                    copybutton.setOnAction(event -> {
                        // ObservableList<Parts> selectedItems =
                        // parts_table.getSelectionModel().getSelectedItems();

                        Rendez_vous rdv = getTableView().getItems().get(getIndex());
                        // rdv_global = getTableView().getItems().get(getIndex());

                        System.out.println(rdv.getCar_rdv().getModele());

                        // try {

                        // FXMLLoader loader = new FXMLLoader(
                        // getClass().getResource("/application/Viewfxml/rdv_details.fxml"));

                        // Parent root = loader.load();
                        // Rdv_details rdv_details_con = loader.getController();
                        // System.out.println(rdv_details_con);

                        // System.out.println(rdv.getDescrption_in());
                        // rdv_details_con.getrdv(rdv);
                        // rdv_container.getChildren().removeAll();
                        // rdv_container.getChildren().setAll(root);

                        // } catch (Exception e) {
                        // // TODO: handle exception
                        // }

                        try {

                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("/application/Viewfxml/rdv_details.fxml"));
                            Parent root = loader.load();

                            Rdv_details rdv_details_con = loader.getController();
                            System.out.println(rdv_details_con);

                            rdv_details_con.getrdv(rdv);
                            Stage stage = new Stage();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle("Mecha Tech");

                            stage.show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox buttonsBox = new HBox(10, copybutton);
                        getAlignment();
                        buttonsBox.setAlignment(Pos.CENTER);
                        setGraphic(buttonsBox);
                    }
                }
            };
        });

        // car_rdv.setItems(list_rdv);

    }

    @FXML
    void modifier(ActionEvent event) {
        Document updatecar = new Document("_id", new ObjectId(car_local.getId()));
        updatecar.append("marque", marque.getText());
        updatecar.append("modele", modele.getText());
        updatecar.append("couleur", couleur.getText());
        updatecar.append("matricule", matricule.getText());
        updatecar.append("vin", vin.getText());

        AdminController.UpdateCar(updatecar, car_local);

    }

    @FXML
    void retour(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Car_Dashboard.fxml"));
            rdv_container.getChildren().removeAll();
            rdv_container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
