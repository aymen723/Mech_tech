package application.ViewController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.bson.Document;

import application.controller.AdminController;
import application.models.Fournisseur;
import application.models.Transaction;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

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
    private TableView<?> table_transaction;
    private Fournisseur fournisseur_local;

    @FXML
    private Text txt_balance;

    @FXML
    private CheckBox mod;

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

    }

    @FXML
    void annuler_founrisseur(ActionEvent event) {

    }

    @FXML
    void save_fournisseur(ActionEvent event) {

    }

    @FXML
    void save_transaction(ActionEvent event) {

        // Transaction transaction;

        LocalDate date = LocalDate.now();
        Document transaction = new Document("date_de_transaction", date);
        transaction.append("somme_payee", Integer.parseInt(somme_payee.getText()));
        transaction.append("somme_de_transaction", Integer.parseInt(somme_transaction.getText()));

        AdminController.addTransaction(transaction, fournisseur_local);

        details_con.getScene().getWindow().setWidth(details_con.getScene().getWindow().getWidth() + 0.001);
        // stage.getScene().getWindow().setWidth(element.getScene().getWidth() + 0.001);

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
