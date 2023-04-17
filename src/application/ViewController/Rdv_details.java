package application.ViewController;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import org.bson.Document;
import org.bson.types.ObjectId;

import application.controller.AdminController;
import application.models.Clientmodel;
import application.models.Parts;
import application.models.Rendez_vous;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Rdv_details {

    @FXML
    private DatePicker date_fin_rdv;

    @FXML
    private DatePicker date_debut_rdv;
    @FXML
    private TextField service;

    @FXML
    private TextField car_model;

    @FXML
    private TextField nom_client;

    @FXML
    private TextField numero_client;

    @FXML
    private TextField prenom_client;

    @FXML
    private TextField prix;

    @FXML
    private Button parts_rdv;

    @FXML
    private Button supp_btn;
    @FXML
    private Button fact_tbn;
    @FXML
    private Button eng_btn;

    @FXML
    private Pane rdv_container;

    ArrayList<Parts> rdv_parts_list;

    private Rendez_vous rdv_local;

    @FXML
    private TextArea description_in;

    @FXML
    private TextArea description_out;

    @FXML
    private TableView<Parts> listview_part;

    @FXML
    private TableColumn<Parts, Void> action_col;

    @FXML
    private TableColumn<Parts, String> nom_part_col;

    @FXML
    private TableColumn<Parts, Integer> prix_col;

    @FXML
    private TableColumn<Parts, Integer> quantite_part_col;

    @FXML
    private Text etat_label;

    ObservableList<Parts> list = FXCollections.observableArrayList();

    @FXML
    void add_parts_rdv(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Viewfxml/Rdv_parts.fxml"));

            Parent root = loader.load();
            Rdv_parts rdv_part_con = loader.getController();
            rdv_part_con.setrdv(rdv_local);
            rdv_container.getChildren().removeAll();
            rdv_container.getChildren().setAll(root);
            // Stage stage = new Stage();
            // Scene scene = new Scene(root);
            // stage.setScene(scene);
            // stage.setTitle("Mecha Tech");
            // stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void getrdv(Rendez_vous rdv) {

        rdv_local = rdv;

        System.out.println(rdv.getDescrption_in());
        nom_client.setText(rdv.getClient_rdv().getNom());
        date_debut_rdv.setValue(rdv.getDate_debut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        date_fin_rdv.setValue(rdv.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        car_model.setText(rdv.getCar_model());
        prix.setText(Integer.toString(rdv.getPrix()));
        service.setText(rdv.getService());
        prenom_client.setText(rdv.getClient_rdv().getPrenom());
        numero_client.setText(rdv.getClient_rdv().getNumero());
        description_in.setText(rdv.getDescrption_in());
        description_out.setText(rdv.getDescrption_out());
        etat_label.setText(rdv.getEtat());

        if (rdv.getParts() != null) {
            list = FXCollections.observableArrayList(rdv_local.getParts());

            nom_part_col.setCellValueFactory(new PropertyValueFactory<>("name"));
            prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
            quantite_part_col.setCellValueFactory(new PropertyValueFactory<>("quntitie"));

            action_col.setCellFactory(column -> {

                return new TableCell<Parts, Void>() {

                    private final Button deletebutton = new Button("");

                    {

                        deletebutton.setStyle(
                                "-fx-background-radius: 5em; -fx-min-width: 25px; -fx-min-height: 25px; -fx-max-width: 25px; -fx-max-height: 25px; -fx-background-color: transparent; -fx-alignment:CENTER;");

                        Image image_copy = new Image(getClass().getResourceAsStream("Delete.png"));
                        ImageView img_copy = new ImageView(image_copy);
                        img_copy.setFitHeight(25);
                        img_copy.setFitWidth(25);
                        deletebutton.setGraphic(img_copy);

                        deletebutton.setOnAction(event -> {
                            // ObservableList<Parts> selectedItems =
                            // parts_table.getSelectionModel().getSelectedItems();

                        });

                        deletebutton.setOnAction(event -> {

                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppression");
                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.setGraphic(null);

                            dialogPane.getStylesheets()
                                    .add(getClass().getResource("/application/Viewfxml/part_style.css")
                                            .toExternalForm());
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

                                rdv_local.getParts().remove(getIndex());
                                list = FXCollections.observableArrayList(rdv_local.getParts());
                                // Parts part = getTableView().getItems().get(getIndex());
                                // AdminController.deletpart(part);
                                // list = AdminController.PartList();
                                listview_part.setItems(list);
                                listview_part.refresh();
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
                            HBox buttonsBox = new HBox(10, deletebutton);
                            getAlignment();
                            buttonsBox.setAlignment(Pos.CENTER);
                            setGraphic(buttonsBox);
                        }
                    }
                };
            });
            listview_part.setItems(list);
            // for (int i = 0; i < rdv_local.getParts().size(); i++) {
            // listview_part.getItems().add(rdv_local.getParts().get(i).getName());
            // }

            // HBox hbox = new HBox(listview_part);
        } else {
            // System.out.println("is null");
        }

    }

    public void setContent(Node node) throws IOException {
        System.out.println("setcontent is executed");

        rdv_container.getChildren().setAll(node);

        System.out.println("setcontent is executed after");
    }

    @FXML
    void enregistre(ActionEvent event) {

        Clientmodel newclient = rdv_local.getClient_rdv();
        Document clientrdv;

        Document newrdv = new Document("date_debut", date_debut_rdv.getValue());
        newrdv.append("date_fin", date_fin_rdv.getValue());
        // newrdv.append("description", description.getText());
        newrdv.append("car model", car_model.getText());
        newrdv.append("prix", prix.getText());

        if (rdv_local.getClient_rdv().getEmail() != null) {
            newclient = new Clientmodel(rdv_local.getClient_rdv().getId(), rdv_local.getClient_rdv().getNom(),
                    rdv_local.getClient_rdv().getPrenom(),
                    rdv_local.getClient_rdv().getEmail(), rdv_local.getClient_rdv().getAddresse(),
                    rdv_local.getClient_rdv().getNumero());
            clientrdv = new Document("_id", new ObjectId(newclient.getId()));
            clientrdv.append("nom", newclient.getNom());
            clientrdv.append("prenom", newclient.getPrenom());
            clientrdv.append("tel", newclient.getNumero());
            clientrdv.append("email", newclient.getEmail());
            clientrdv.append("addresse", newclient.getAddresse());
        } else {
            newclient = new Clientmodel(nom_client.getText(), prenom_client.getText(), numero_client.getText());
            clientrdv = new Document("nom", newclient.getNom());
            clientrdv.append("prenom", newclient.getPrenom());
            clientrdv.append("numero", newclient.getNumero());
        }

        clientrdv.append("client", clientrdv);

        int i = 0;
        // for (i = 0; i < rdv_local.getParts().size(); i++) {
        // System.out.println("this is parts");

        // System.out.println(rdv_local.getParts().get(i).getName());
        // }

        // clientrdv.append("parts", rdv_local.getParts());

        List<Document> myDocuments = new ArrayList<Document>();
        for (i = 0; i < rdv_local.getParts().size(); i++) {
            Parts part = rdv_local.getParts().get(i);
            Document addpart = new Document("_id", new ObjectId(part.getId()));
            addpart.append("name", part.getName());
            addpart.append("price", part.getPrice());
            addpart.append("quantity", part.getQuntitie());
            // addpart.append("description", part.getDescription());
            myDocuments.add(addpart);

            System.out.println(myDocuments.get(i));
        }
        newrdv.append("parts", myDocuments);
        AdminController.UpdateRdv(newrdv, rdv_local);

    }

    @FXML
    void factur(ActionEvent event) {

    }

    @FXML
    void supp(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setGraphic(null);

        dialogPane.getStylesheets()
                .add(getClass().getResource("/application/Viewfxml/part_style.css")
                        .toExternalForm());
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

            AdminController.deletrdv(rdv_local);

            try {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/application/Viewfxml/Rendez_vous_dashbord.fxml"));
                Parent root = loader.load();

                rdv_container.getChildren().removeAll();
                rdv_container.getChildren().setAll(root);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            // User clicked Cancel or closed the dialog

        }

    }

}
