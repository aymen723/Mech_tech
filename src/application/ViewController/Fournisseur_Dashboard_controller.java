package application.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.bson.Document;

import application.controller.AdminController;
import application.models.Fournisseur;
import application.models.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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

    private Fournisseur local_fFournisseur;

    ObservableList<Fournisseur> list_fornisseur = FXCollections.observableArrayList();

    @FXML
    void add_fournisseur(ActionEvent event) {

        Fournisseur fournisseur;
        Document newfournisseur = new Document("nom", name.getText());
        newfournisseur.append("adresse", address.getText());
        newfournisseur.append("numero", numero.getText());
        newfournisseur.append("email", email.getText());

        newfournisseur.append("balance", 0);

        newfournisseur.append("Transactions", new ArrayList<Transaction>());

        AdminController.AddFournisseur(newfournisseur);

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        list_fornisseur = FXCollections.observableArrayList(AdminController.ListFournisseur());

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

                        // try {

                        // FXMLLoader loader = new FXMLLoader(
                        // getClass().getResource("/application/Viewfxml/fournisseur_details.fxml"));

                        // Parent root = loader.load();
                        // fournisseur_details fourisseur_con = loader.getController();
                        // fourisseur_con.getfournisseur(fournisseur);
                        // Stage stage = new Stage();
                        // Scene scene = new Scene(root);
                        // stage.setScene(scene);
                        // stage.setTitle("Mecha Tech");

                        // stage.show();

                        // } catch (IOException e) {
                        // e.printStackTrace();
                        // }

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

        fournisseur_table.setItems(list_fornisseur);
    }
}