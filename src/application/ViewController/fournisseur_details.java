package application.ViewController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;

import application.controller.AdminController;
import application.models.Fournisseur;
import application.models.Transaction;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
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
                            // AdminController.deletpart(part);
                            // list = AdminController.PartList();
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
        Fournisseur fournisseur = new Fournisseur();
        Document newfournisseur = new Document("nom", nom.getText());
        newfournisseur.append("adresse", address.getText());
        newfournisseur.append("numero", numero.getText());
        newfournisseur.append("email", email.getText());
        fournisseur.setName(nom.getText());
        fournisseur.setAddress(address.getText());
        fournisseur.setPhone(numero.getText());
        fournisseur.setEmail(email.getText());
        AdminController.UpdateFournisseur(newfournisseur, fournisseur_local);
        getfournisseur(AdminController.findFournisseurbyid(fournisseur_local.getId()));
    }

    @FXML
    void save_transaction(ActionEvent event) {

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
