package application.ViewController;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import org.bson.Document;

import application.controller.AdminController;
import application.models.Clientmodel;
import application.models.Fournisseur;
import application.models.Transaction;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;

public class Fournisseur_Dashboard_controller implements Initializable {
    @FXML
    private TableColumn<Fournisseur, Void> actionsColumn;

    @FXML
    private Button add_btn;

    @FXML
    private TableColumn<Fournisseur, String> adresse_col;

    @FXML
    private Button annl_btn;

    @FXML
    private TableColumn<Fournisseur, Integer> balance_col;

    @FXML
    private TextField address;

    @FXML
    private TableColumn<Fournisseur, String> email_col;

    @FXML
    private Button mod_btn;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<Fournisseur, String> nom_col;

    @FXML
    private TableColumn<Fournisseur, Integer> numero_col;

    @FXML
    private TableView<Fournisseur> fournisseur_table;

    @FXML
    private TextField email;

    @FXML
    private TextField numero;

    @FXML
    private TextField reserch_field;

    @FXML
    private BorderPane fournisseur_con;

    private ProgressIndicator progressIndicator = new ProgressIndicator();

    private Fournisseur local_fFournisseur;

    private FilteredList<Fournisseur> filteredfournisseur;

    ObservableList<Fournisseur> list_fornisseur = FXCollections.observableArrayList();

    @FXML
    void add_fournisseur(ActionEvent event) {

        name.getStyleClass().remove("inptempty");

        address.getStyleClass().remove("inptempty");

        numero.getStyleClass().remove("inptempty");

        email.getStyleClass().remove("inptempty");

        if ((name.getText().trim().isEmpty() == false) &&
                (address.getText().trim().isEmpty() == false) &&
                (numero.getText().trim().isEmpty() == false) &&
                (email.getText().trim().isEmpty() == false)) {
            Fournisseur fournisseur;
            Document newfournisseur = new Document("nom", name.getText());
            newfournisseur.append("adresse", address.getText());
            newfournisseur.append("numero", numero.getText());
            newfournisseur.append("email", email.getText());

            newfournisseur.append("balance", 0);

            newfournisseur.append("Transactions", new ArrayList<Transaction>());

            AdminController.AddFournisseur(newfournisseur);
            list_fornisseur = FXCollections.observableArrayList(AdminController.ListFournisseur());
            fournisseur_table.setItems(list_fornisseur);
            fournisseur_table.refresh();
        } else {
            if (name.getText().trim().isEmpty() == true) {

                name.getStyleClass().add("inptempty");
            }
            if (address.getText().trim().isEmpty() == true) {

                address.getStyleClass().add("inptempty");
            }
            if (numero.getText().trim().isEmpty() == true) {

                numero.getStyleClass().add("inptempty");
            }
            if (email.getText().trim().isEmpty() == true) {

                email.getStyleClass().add("inptempty");
            }

        }

    }

    @FXML
    void annl_mod(ActionEvent event) {

    }

    @FXML
    void mod_parts(ActionEvent event) {

    }

    @FXML
    void name_field(InputMethodEvent event) {

    }

    private void loadData() {
        // Create a task to load the data in the background
        Task<ArrayList<Fournisseur>> loadTask = new Task<ArrayList<Fournisseur>>() {
            @Override
            protected ArrayList<Fournisseur> call() throws Exception {
                // Perform your data loading operation here

                return AdminController.ListFournisseur();
            }
        };

        // Set up loading indicator
        Alert loadingAlert = new Alert(AlertType.INFORMATION);
        loadingAlert.setTitle("Loading");
        loadingAlert.setHeaderText("Please wait...");
        loadingAlert.setContentText("Loading data from the server...");
        loadingAlert.initOwner(fournisseur_table.getScene().getWindow());
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
            ArrayList<Fournisseur> list_f = loadTask.getValue();
            // Update the UI with the loaded data
            Platform.runLater(() -> {
                list_fornisseur = FXCollections.observableArrayList(list_f);

                fournisseur_table.setItems(list_fornisseur);
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
        errorAlert.initOwner(fournisseur_table.getScene().getWindow());
        errorAlert.showAndWait();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // list_fornisseur =
        // FXCollections.observableArrayList(AdminController.ListFournisseur());

        ChangeListener<Scene> chl = new ChangeListener<Scene>() {

            @Override
            public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
                if (newValue != null) {
                    loadData();
                }
            }

        };
        fournisseur_table.sceneProperty().addListener(chl);

        // TODO Auto-generated method stub
        // System.out.println("hna kayn nom" + list_fornisseur.get(0).getName());
        nom_col.setCellValueFactory(new PropertyValueFactory<>("name"));

        adresse_col.setCellValueFactory(new PropertyValueFactory<>("address"));
        balance_col.setCellValueFactory(new PropertyValueFactory<>("balance"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        numero_col.setCellValueFactory(new PropertyValueFactory<>("phone"));

        actionsColumn.setCellFactory(column -> {

            return new TableCell<Fournisseur, Void>() {

                private final Button copybutton = new Button("");
                private final Button deleteButton = new Button("");

                {

                    copybutton.setStyle(
                            "-fx-background-radius: 5em; -fx-min-width: 25px; -fx-min-height: 25px; -fx-max-width: 25px; -fx-max-height: 25px; -fx-background-color: transparent; -fx-alignment:CENTER;");

                    Image image_copy = new Image(getClass().getResourceAsStream("menu.png"));
                    ImageView img_copy = new ImageView(image_copy);
                    img_copy.setFitHeight(25);
                    img_copy.setFitWidth(25);
                    copybutton.setGraphic(img_copy);

                    deleteButton.setStyle(
                            "-fx-background-radius: 5em; -fx-min-width: 25px; -fx-min-height: 25px; -fx-max-width: 25px; -fx-max-height: 25px; -fx-background-color: transparent; -fx-alignment:CENTER;");
                    Image image = new Image(getClass().getResourceAsStream("Delete.png"));
                    ImageView img = new ImageView(image);
                    img.setFitHeight(25);
                    img.setFitWidth(25);
                    deleteButton.setGraphic(img);

                    copybutton.setOnAction(event -> {
                        // ObservableList<Parts> selectedItems =
                        // parts_table.getSelectionModel().getSelectedItems();

                        Fournisseur fournisseur = getTableView().getItems().get(getIndex());
                        // rdv_global = getTableView().getItems().get(getIndex());

                        try {

                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("/application/Viewfxml/fournisseur_details.fxml"));

                            Parent root = loader.load();
                            fournisseur_details fourisseur = loader.getController();

                            fourisseur.getfournisseur(fournisseur);
                            fournisseur_con.getChildren().removeAll();
                            fournisseur_con.getChildren().setAll(root);

                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                    });

                    deleteButton.setOnAction(event -> {

                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation de suppression");
                        DialogPane dialogPane = alert.getDialogPane();
                        dialogPane.setGraphic(null);

                        dialogPane.getStylesheets()
                                .add(getClass().getResource("/application/Viewfxml/part_style.css").toExternalForm());
                        dialogPane.getStyleClass().add("dialog-pane ");

                        alert.initStyle(StageStyle.UNDECORATED);

                        alert.setContentText("cette action ne peut pas être inversé !");

                        ButtonBar buttonBar = (ButtonBar) alert.getDialogPane().lookup(".button-bar");
                        Button cancelButton = (Button) buttonBar.getButtons().get(1);
                        Button deleteButton = (Button) buttonBar.getButtons().get(0);
                        cancelButton.getStyleClass().add("cancel_btn");
                        deleteButton.getStyleClass().add("delet_btn");
                        cancelButton.setText("Annuler");
                        deleteButton.setText("Supprimer");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            Fournisseur fournisseur = getTableView().getItems().get(getIndex());
                            AdminController.deleteFournisseur(fournisseur);
                            list_fornisseur = FXCollections.observableArrayList(AdminController.ListFournisseur());
                            fournisseur_table.setItems(list_fornisseur);
                            fournisseur_table.refresh();
                            // User clicked OK
                        } else {
                            // User clicked Cancel or closed the dialog

                        }

                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox buttonsBox = new HBox(10, copybutton, deleteButton);
                        getAlignment();
                        buttonsBox.setAlignment(Pos.CENTER);
                        setGraphic(buttonsBox);
                    }
                }
            };
        });

        // fournisseur_table.setItems(list_fornisseur);

        filteredfournisseur = new FilteredList<>(list_fornisseur, b -> true);

        // Set the filter Predicate whenever the search text changes
        reserch_field.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<Fournisseur> filteredList = new FilteredList<>(list_fornisseur, data -> true);
            filteredList.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (data.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (data.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(data.getBalance()).contains(lowerCaseFilter)) {
                    return true;
                } else if (data.getAddress().contains(lowerCaseFilter)) {
                    return true;
                } else if (data.getPhone().contains(lowerCaseFilter)) {
                    return true;
                }

                fournisseur_table.refresh();
                return false;
            });
            SortedList<Fournisseur> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(fournisseur_table.comparatorProperty());
            System.out.println(sortedList.size());
            fournisseur_table.setItems(sortedList);
        });

        // Wrap the filtered list in a sorted list
        SortedList<Fournisseur> sortedList = new SortedList<>(filteredfournisseur);

        // Bind the sorted list to the table
        fournisseur_table.setItems(sortedList);
    }
}