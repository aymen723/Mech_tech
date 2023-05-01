package application.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.controller.AdminController;
import application.models.Clientmodel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Client_dashbord implements Initializable {

    @FXML
    private Button add_btn;

    @FXML
    private TableColumn<Clientmodel, String> address_col;

    @FXML
    private TableColumn<Clientmodel, String> email_col;

    @FXML
    private Button mod_btn;

    @FXML
    private TableColumn<Clientmodel, String> nom_col;

    @FXML
    private TableColumn<Clientmodel, String> numero_col;

    @FXML
    private TableView<Clientmodel> client_table;

    @FXML
    private TableColumn<Clientmodel, String> pernom_col;

    @FXML
    private TextField reserch_field;

    @FXML
    private TableColumn<Clientmodel, Void> action_col;

    @FXML
    private BorderPane client_container;

    public static Clientmodel client;

    ObservableList<Clientmodel> list = FXCollections.observableArrayList(
            new Clientmodel("1", "client1", "cleint1", "5555", "address", "email"),
            new Clientmodel("2", "client2", "cleint2", "5555", "address", "email"));

    FilteredList<Clientmodel> filteredList = new FilteredList<>(list, b -> true);

    @FXML
    void add_client(ActionEvent event) {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/ajouter_client.fxml"));
            client_container.getChildren().removeAll();
            client_container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @FXML
    void mod_client(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'initialize'");

        list = AdminController.ListClient();

        nom_col.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        pernom_col.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        numero_col.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        address_col.setCellValueFactory(new PropertyValueFactory<>("Addresse"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));

        action_col.setCellFactory(column -> {

            return new TableCell<Clientmodel, Void>() {

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
                            Clientmodel client = getTableView().getItems().get(getIndex());
                            AdminController.deletClient(client);
                            list = AdminController.ListClient();
                            client_table.setItems(list);
                            client_table.refresh();
                            // User clicked OK
                        } else {
                            // User clicked Cancel or closed the dialog

                        }

                    });

                    editButton.setOnAction(event -> {
                        client = getTableView().getItems().get(getIndex());
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

                        Clientmodel client = getTableView().getItems().get(getIndex());

                        StringBuilder sb = new StringBuilder();

                        sb.append(client.getNom()).append("," + "\n");
                        sb.append(client.getPrenom()).append("," + "\n");
                        sb.append(client.getAddresse()).append("\n");
                        sb.append(client.getEmail()).append("\n");
                        sb.append(client.getNumero()).append("\n");

                        ClipboardContent content = new ClipboardContent();
                        content.putString(sb.toString());
                        Clipboard.getSystemClipboard().setContent(content);
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

        client_table.setItems(list);

        filteredList = new FilteredList<>(list, b -> true);

        // Set the filter Predicate whenever the search text changes
        reserch_field.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<Clientmodel> filteredList = new FilteredList<>(list, data -> true);
            filteredList.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (data.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (data.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (data.getAddresse().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(data.getEmail()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(data.getNumero()).contains(lowerCaseFilter)) {
                    return true;
                }

                client_table.refresh();
                return false;
            });
            SortedList<Clientmodel> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(client_table.comparatorProperty());
            System.out.println(sortedList.size());
            client_table.setItems(sortedList);
        });

        // Wrap the filtered list in a sorted list
        SortedList<Clientmodel> sortedList = new SortedList<>(filteredList);

        // Bind the sorted list to the table
        client_table.setItems(sortedList);
        
    }
}
