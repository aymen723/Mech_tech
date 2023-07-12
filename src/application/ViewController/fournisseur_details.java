package application.ViewController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;

import application.controller.AdminController;
import application.models.Fournisseur;
import application.models.Transaction;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

public class fournisseur_details {

    @FXML
    private TextField address;

    @FXML
    private Button btn_annuler_fournisseur;

    @FXML
    private Button btn_annuler_trans;

    @FXML
    private Button btn_save_fournisseur;

    @FXML
    private Button btn_save_trans;

    @FXML
    private TableColumn<Transaction, String> date_trans_col;

    @FXML
    private TextField email;

    @FXML
    private TextField nom;

    @FXML
    private TextField numero;

    @FXML
    private TextField somme_payee;

    @FXML
    private TableColumn<Transaction, Integer> somme_payee_coll;

    @FXML
    private TextField somme_transaction;

    @FXML
    private TableColumn<Transaction, Integer> somme_transaction_col;

    @FXML
    private TableView<Transaction> table_transaction;
    private Fournisseur fournisseur_local;

    @FXML
    private Text txt_balance;

    @FXML
    private CheckBox mod;

    @FXML
    private TableColumn<Transaction, Void> action_col;

    @FXML
    private BorderPane details_con;

    private ProgressIndicator progressIndicator = new ProgressIndicator();

    ObservableList<Transaction> list_transaction = FXCollections.observableArrayList();

    public void getfournisseur(Fournisseur fournisseur) {
        this.fournisseur_local = fournisseur;
        nom.setEditable(false);
        address.setEditable(false);
        numero.setEditable(false);
        email.setEditable(false);
        nom.setText(fournisseur_local.getName());
        address.setText(fournisseur_local.getAddress());
        numero.setText(fournisseur.getPhone());
        email.setText(fournisseur_local.getEmail());
        txt_balance.setText(Integer.toString(fournisseur_local.getBalance()));
        if ((fournisseur_local.getBalance()) < 0) {
            txt_balance.setStyle("-fx-color: red");

        }

        list_transaction = FXCollections.observableArrayList(fournisseur_local.getTransactions());
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

        date_trans_col.setCellValueFactory(

                cellData -> new SimpleStringProperty(DATE_FORMAT.format(cellData.getValue().getDate_de_transaction())));
        somme_transaction_col.setCellValueFactory(new PropertyValueFactory<>("somme_de_transaction"));
        somme_payee_coll.setCellValueFactory(new PropertyValueFactory<>("somme_payee"));

        action_col.setCellFactory(column -> {

            return new TableCell<Transaction, Void>() {
                private final Button deleteButton = new Button("");

                {

                    deleteButton.setStyle(
                            "-fx-background-radius: 5em; -fx-min-width: 25px; -fx-min-height: 25px; -fx-max-width: 25px; -fx-max-height: 25px; -fx-background-color: transparent; -fx-alignment:CENTER;");
                    Image image = new Image(getClass().getResourceAsStream("Delete.png"));
                    ImageView img = new ImageView(image);
                    img.setFitHeight(25);
                    img.setFitWidth(25);
                    deleteButton.setGraphic(img);

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
                            Transaction transaction = getTableView().getItems().get(getIndex());

                            AdminController.deleteTransaction(transaction, fournisseur_local);
                            getfournisseur(AdminController.findFournisseurbyid(fournisseur_local.getId()));
                            // list = AdminController.fourn();
                            // parts_table.setItems(list);
                            // parts_table.refresh();
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
                        HBox buttonsBox = new HBox(10, deleteButton);
                        getAlignment();
                        buttonsBox.setAlignment(Pos.CENTER);
                        setGraphic(buttonsBox);
                    }
                }
            };
        });

        table_transaction.setItems(list_transaction);
        table_transaction.refresh();

    }

    @FXML
    void annuler_founrisseur(ActionEvent event) {

    }

    @FXML
    void save_fournisseur(ActionEvent event) {

        nom.getStyleClass().remove("inptempty");

        address.getStyleClass().remove("inptempty");

        numero.getStyleClass().remove("inptempty");

        email.getStyleClass().remove("inptempty");

        if ((nom.getText().trim().isEmpty() == false) &&
                (address.getText().trim().isEmpty() == false) &&
                (numero.getText().trim().isEmpty() == false) &&
                (email.getText().trim().isEmpty() == false)) {
            Fournisseur fournisseur = new Fournisseur();
            Document newfournisseur = new Document("nom", nom.getText());
            newfournisseur.append("adresse", address.getText());
            newfournisseur.append("numero", numero.getText());
            newfournisseur.append("email", email.getText());
            fournisseur.setName(nom.getText());
            fournisseur.setAddress(address.getText());
            fournisseur.setPhone(numero.getText());
            fournisseur.setEmail(email.getText());

            Task<Integer> updateTask = new Task<Integer>() {

                @Override
                protected Integer call() throws Exception {

                    AdminController.UpdateFournisseur(newfournisseur, fournisseur_local);

                    getfournisseur(AdminController.findFournisseurbyid(fournisseur_local.getId()));
                    return 0;
                }

            };

            Alert loadingAlert = new Alert(AlertType.INFORMATION);
            loadingAlert.setTitle("Loading");
            loadingAlert.setHeaderText("Please wait...");
            loadingAlert.setContentText("Loading data from the server...");
            loadingAlert.initOwner(details_con.getScene().getWindow());
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

        } else {

            if (nom.getText().trim().isEmpty() == true) {

                nom.getStyleClass().add("inptempty");
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

    private void showErrorAlert(String title, String message) {
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setTitle(title);
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(message);
        errorAlert.initOwner(details_con.getScene().getWindow());
        errorAlert.showAndWait();
    }

    @FXML
    void save_transaction(ActionEvent event) {

        somme_payee.getStyleClass().remove("inptempty");

        somme_transaction.getStyleClass().remove("inptempty");

        if ((somme_payee.getText().trim().isEmpty() == false &&
                somme_payee.getText().matches("[0-9]+")) &&
                (somme_transaction.getText().trim().isEmpty() == false) &&
                somme_transaction.getText().matches("[0-9]+")) {

            Transaction transaction = new Transaction();
            LocalDate date = LocalDate.now();

            Document doctransaction = new Document("_id", new ObjectId());
            doctransaction.append("date_de_transaction", date);
            doctransaction.append("somme_payee", Integer.parseInt(somme_payee.getText()));
            doctransaction.append("somme_de_transaction", Integer.parseInt(somme_transaction.getText()));

            AdminController.addTransaction(doctransaction, fournisseur_local);
            transaction.setDate_de_transaction(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            transaction.setSomme_de_transaction(Integer.parseInt(somme_payee.getText()));
            transaction.setSomme_payee(Integer.parseInt(somme_transaction.getText()));

            fournisseur_local.getTransactions().add(transaction);
            txt_balance.setText(Integer.toString(fournisseur_local.getBalance()));

            // list_transaction =
            // FXCollections.observableArrayList(fournisseur_local.getTransactions());
            // table_transaction.setItems(list_transaction);
            // table_transaction.refresh();

            Fournisseur newfournisseur = AdminController.findFournisseurbyid(fournisseur_local.getId());

            // txt_balance.setText(Integer.toString(newfournisseur.getBalance()));
            // stage.getScene().getWindow().setWidth(element.getScene().getWidth() + 0.001);

            getfournisseur(newfournisseur);
        } else {

            if (somme_payee.getText().trim().isEmpty() == true || !somme_payee.getText().matches("[0-9]+")) {

                somme_payee.getStyleClass().add("inptempty");
            }
            if (somme_transaction.getText().trim().isEmpty() == true
                    || !somme_transaction.getText().matches("[0-9]+")) {

                somme_transaction.getStyleClass().add("inptempty");
            }

        }

    }

    @FXML
    void modifier(ActionEvent event) {
        if (mod.isSelected() == true) {
            nom.setEditable(true);
            address.setEditable(true);
            numero.setEditable(true);
            email.setEditable(true);

        } else {
            nom.setEditable(false);
            address.setEditable(false);
            numero.setEditable(false);
            email.setEditable(false);
        }

    }

}
