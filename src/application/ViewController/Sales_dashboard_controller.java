package application.ViewController;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import application.controller.AdminController;
import application.models.Sales;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Sales_dashboard_controller implements Initializable {
    @FXML
    private TableColumn<Sales, Void> action_col;

    @FXML
    private Button add_btn;

    @FXML
    private BorderPane client_container;

    @FXML
    private TableView<Sales> sales_table;

    @FXML
    private TableColumn<Sales, Date> date_de_vente_col;

    @FXML
    private Button mod_btn;

    @FXML
    private TableColumn<Sales, String> total_col;
    @FXML
    private TableColumn<Sales, String> Number_pieces;

    @FXML
    private TextField reserch_field;

    @FXML
    private BorderPane sales_container;

    ObservableList<Sales> list = FXCollections.observableArrayList();

    @FXML
    void add_sales(ActionEvent event) {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/ajouter_sales.fxml"));
            sales_container.getChildren().removeAll();
            sales_container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/ajouter_sales.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxml);
            stage.setScene(scene);
            stage.setTitle("Mecha Tech");
            scene.setFill(Color.TRANSPARENT);

            // primaryStage.initStyle(StageStyle.UNDECORATED);
            // primaryStage.initStyle(StageStyle.TRANSPARENT);

            // primaryStage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void mod_sales(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

        list = FXCollections.observableArrayList(AdminController.ListSales());
        System.out.println(list.size());

        date_de_vente_col.setCellValueFactory(new PropertyValueFactory<>("date_de_vente"));
        // total_col.setCellValueFactory(new PropertyValueFactory<>("Prenom"));

        Number_pieces.setCellValueFactory(
                cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().nuberofparts())));

        total_col.setCellValueFactory(
                cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().total())));

        action_col.setCellFactory(column -> {

            return new TableCell<Sales, Void>() {

                private final Button deleteButton = new Button("");
                private final Button copybutton = new Button("");
                private final Button editButton = new Button("");

                {

                    deleteButton.setStyle(
                            "-fx-background-radius: 5em; -fx-min-width: 25px; -fx-min-height: 25px; -fx-max-width: 25px; -fx-max-height: 25px; -fx-background-color: transparent; -fx-alignment:CENTER;");
                    Image image = new Image(getClass().getResourceAsStream("Delete.png"));
                    ImageView img = new ImageView(image);
                    img.setFitHeight(25);
                    img.setFitWidth(25);
                    deleteButton.setGraphic(img);

                    editButton.setStyle(
                            "-fx-background-radius: 5em; -fx-min-width: 25px; -fx-min-height: 25px; -fx-max-width: 25px; -fx-max-height: 25px; -fx-background-color: transparent; -fx-alignment:CENTER;");
                    Image image_edit = new Image(getClass().getResourceAsStream("Edit.png"));
                    ImageView img_edit = new ImageView(image_edit);
                    img_edit.setFitHeight(25);
                    img_edit.setFitWidth(25);
                    editButton.setGraphic(img_edit);

                    copybutton.setStyle(
                            "-fx-background-radius: 5em; -fx-min-width: 25px; -fx-min-height: 25px; -fx-max-width: 25px; -fx-max-height: 25px; -fx-background-color: transparent; -fx-alignment:CENTER;");

                    Image image_copy = new Image(getClass().getResourceAsStream("copy.png"));
                    ImageView img_copy = new ImageView(image_copy);
                    img_copy.setFitHeight(25);
                    img_copy.setFitWidth(25);
                    copybutton.setGraphic(img_copy);

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
                            // Clientmodel client = getTableView().getItems().get(getIndex());
                            // AdminController.deletClient(client);
                            // list = AdminController.ListClient();
                            // client_table.setItems(list);
                            // client_table.refresh();
                            // User clicked OK
                        } else {
                            // User clicked Cancel or closed the dialog

                        }

                    });

                    editButton.setOnAction(event -> {
                        // client = getTableView().getItems().get(getIndex());
                        try {
                            Parent fxml = FXMLLoader
                                    .load(getClass().getResource("/application/Viewfxml/modifier_client.fxml"));
                            client_container.getChildren().removeAll();
                            client_container.getChildren().setAll(fxml);

                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                    });

                    copybutton.setOnAction(event -> {
                        // ObservableList<Parts> selectedItems =
                        // parts_table.getSelectionModel().getSelectedItems();

                        // Clientmodel client = getTableView().getItems().get(getIndex());

                        // StringBuilder sb = new StringBuilder();

                        // sb.append(client.getNom()).append("," + "\n");
                        // sb.append(client.getPrenom()).append("," + "\n");
                        // sb.append(client.getAddresse()).append("\n");
                        // sb.append(client.getEmail()).append("\n");
                        // sb.append(client.getNumero()).append("\n");

                        // ClipboardContent content = new ClipboardContent();
                        // content.putString(sb.toString());
                        // Clipboard.getSystemClipboard().setContent(content);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {

                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox buttonsBox = new HBox(10, editButton, deleteButton, copybutton);
                        getAlignment();
                        buttonsBox.setAlignment(Pos.CENTER);
                        setGraphic(buttonsBox);
                    }
                }
            };
        });

        sales_table.setItems(list);
    }
}
