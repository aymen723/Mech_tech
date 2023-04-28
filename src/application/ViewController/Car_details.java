package application.ViewController;

import java.text.SimpleDateFormat;
import java.util.Date;

import application.controller.AdminController;
import application.models.Car;
import application.models.Rendez_vous;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.beans.property.SimpleStringProperty;

public class Car_details {

    @FXML
    private TableColumn<Rendez_vous, Void> action_col;

    @FXML
    private Button btn_mod;

    @FXML
    private Button btn_supprime;

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

    private Car car_local;

    ObservableList<Rendez_vous> list_rdv = FXCollections.observableArrayList();

    public void getcar(Car car) {
        this.car_local = car;

        marque.setText(car_local.getMarque());
        modele.setText(car_local.getModele());
        matricule.setText(car_local.getMatricule());
        couleur.setText(car_local.getCouleur());
        vin.setText(car_local.getVin());

        list_rdv = FXCollections.observableArrayList(AdminController.RdvListCar(car_local.getVin()));

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

                        try {

                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("/application/Viewfxml/rdv_details.fxml"));

                            Parent root = loader.load();
                            Rdv_details rdv_details_con = loader.getController();
                            System.out.println(rdv_details_con);

                            System.out.println(rdv.getDescrption_in());
                            rdv_details_con.getrdv(rdv);
                            rdv_container.getChildren().removeAll();
                            rdv_container.getChildren().setAll(root);

                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                        // try {

                        // FXMLLoader loader = new FXMLLoader(
                        // getClass().getResource("/application/Viewfxml/rdv_details.fxml"));
                        // Parent root = loader.load();

                        // Rdv_details rdv_details_con = loader.getController();
                        // System.out.println(rdv_details_con);

                        // rdv_details_con.getrdv(rdv);
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

        car_rdv.setItems(list_rdv);

    }

    @FXML
    void modifier(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) {

    }

    @FXML
    void supprime(ActionEvent event) {

    }

}
