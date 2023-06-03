package application.ViewController;

// import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
// import java.util.ArrayList;
import java.util.ResourceBundle;

import application.controller.AdminController;
import application.models.Parts;
import application.models.Rendez_vous;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
// import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
// import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
// import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
// import javafx.stage.Stage;
import javafx.util.Callback;

public class Rdv_parts implements Initializable {

    @FXML
    private TableView<Parts> part_rdv;

    @FXML
    private TableColumn<Parts, Void> action_col;

    @FXML
    private TableColumn<Parts, String> nom_part_col;

    @FXML
    private TableColumn<Parts, Integer> prix_rdv__col;

    @FXML
    private TableColumn<Parts, Integer> quantite_part_col;

    @FXML
    private Button annl_btn;

    @FXML
    private TextField quntitie;

    @FXML
    private TextField reserch_field;

    @FXML
    private TableColumn<Parts, String> date_achat;

    @FXML
    private TableColumn<Parts, String> desc_col;

    @FXML
    private TableColumn<Parts, String> fournisseur_col;

    @FXML
    private TableColumn<Parts, String> nom_col;
    @FXML
    private TableColumn<Parts, Void> actionsColumn;
    @FXML
    private TableView<Parts> parts_table;

    @FXML
    private TableColumn<Parts, Integer> prix_achat;

    @FXML
    private TableColumn<Parts, Integer> prix_col;

    @FXML
    private TableColumn<Parts, Integer> quntite_col;

    @FXML
    private Button btn_conferm;

    public static Parts part;

    private Text txt;

    @FXML
    private BorderPane rdv_parts_container;

    private ProgressIndicator progressIndicator = new ProgressIndicator();

    // private ArrayList<Parts> parts_list;

    private Rendez_vous rdv_local;

    ObservableList<Parts> list = FXCollections.observableArrayList();

    FilteredList<Parts> filteredList = new FilteredList<>(list, b -> true);

    public void annl_mod() {

        quntitie.setText("");

        part = null;

        annl_btn.setDisable(true);
        annl_btn.setVisible(false);

    }

    public void setrdv(Rendez_vous rdv) {
        this.rdv_local = rdv;

        if (rdv.getParts() != null) {
            list = FXCollections.observableArrayList(rdv_local.getParts());

            nom_part_col.setCellValueFactory(new PropertyValueFactory<>("name"));
            prix_rdv__col.setCellValueFactory(new PropertyValueFactory<>("price"));
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
                                part_rdv.setItems(list);
                                part_rdv.refresh();
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
            part_rdv.setItems(list);
            // for (int i = 0; i < rdv_local.getParts().size(); i++) {
            // listview_part.getItems().add(rdv_local.getParts().get(i).getName());
            // }

            // HBox hbox = new HBox(listview_part);
        } else {
            // System.out.println("is null");
        }

    }

    @FXML
    void conferm() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Viewfxml/rdv_details.fxml"));
            Parent root = loader.load();
            Rdv_details rdv_details_con = loader.getController();
            rdv_details_con.getrdv(rdv_local);

            rdv_parts_container.getChildren().removeAll();
            rdv_parts_container.getChildren().setAll(root);
            rdv_details_con.setContent(root);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    private void loadData() {
        // Create a task to load the data in the background
        Task<ObservableList<Parts>> loadTask = new Task<ObservableList<Parts>>() {
            @Override
            protected ObservableList<Parts> call() throws Exception {
                // Perform your data loading operation here

                return AdminController.PartList();
            }
        };

        // Set up loading indicator
        Alert loadingAlert = new Alert(AlertType.INFORMATION);
        loadingAlert.setTitle("Loading");
        loadingAlert.setHeaderText("Please wait...");
        loadingAlert.setContentText("Loading data from the server...");
        loadingAlert.initOwner(parts_table.getScene().getWindow());
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
            list = loadTask.getValue();

            // Update the UI with the loaded data
            Platform.runLater(() -> {
                // ObservableList<Parts> list = FXCollections.observableArrayList(listrdv);
                parts_table.setItems(list);
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
        errorAlert.initOwner(parts_table.getScene().getWindow());
        errorAlert.showAndWait();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

        // annl_btn.setDisable(true);
        // annl_btn.setVisible(false);

        // System.out.println("hna list mazal");
        // list = AdminController.PartList();
        // System.out.println("hna wra list");

        ChangeListener<Scene> chl = new ChangeListener<Scene>() {

            @Override
            public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
                if (newValue != null) {
                    loadData();
                }
            }

        };
        parts_table.sceneProperty().addListener(chl);
        // nom_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        // prix_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        // quntite_col.setCellValueFactory(new PropertyValueFactory<>("quntitie"));

        // desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));

        // desc_col.setCellFactory(new Callback<TableColumn<Parts, String>,
        // TableCell<Parts, String>>() {
        // @Override
        // public TableCell<Parts, String> call(TableColumn<Parts, String> param) {
        // final TableCell<Parts, String> cell = new TableCell<Parts, String>() {
        // private Text text;

        // @Override
        // public void updateItem(String item, boolean empty) {
        // super.updateItem(item, empty);
        // if (item != null && !empty) {
        // setText(item);
        // text = new Text(item.toString());
        // txt = text;
        // text.setWrappingWidth(desc_col.getWidth());
        // setGraphic(text);

        // } else {
        // setText(null);
        // }
        // }
        // };
        // return cell;
        // }
        // });

        nom_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        prix_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        quntite_col.setCellValueFactory(new PropertyValueFactory<>("quntitie"));
        prix_achat.setCellValueFactory(new PropertyValueFactory<>("buyingprice"));
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

        date_achat.setCellValueFactory(
                cellData -> new SimpleStringProperty(DATE_FORMAT.format(cellData.getValue().getBuyingdate())));

        fournisseur_col.setCellValueFactory(

                cellData -> new SimpleStringProperty(cellData.getValue().getFournisseur().getName()));

        desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));

        desc_col.setCellFactory(new Callback<TableColumn<Parts, String>, TableCell<Parts, String>>() {
            @Override
            public TableCell<Parts, String> call(TableColumn<Parts, String> param) {
                final TableCell<Parts, String> cell = new TableCell<Parts, String>() {
                    private Text text;

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            setText(item);
                            text = new Text(item.toString());
                            txt = text;
                            text.setWrappingWidth(desc_col.getWidth());
                            setGraphic(text);

                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

        actionsColumn.setCellFactory(column -> {

            return new TableCell<Parts, Void>() {

                private final Button addButton = new Button("");

                {

                    addButton.setStyle(
                            "-fx-background-radius: 5em; -fx-min-width: 25px; -fx-min-height: 25px; -fx-max-width: 25px; -fx-max-height: 25px; -fx-background-color: transparent; -fx-alignment:CENTER;");

                    Image image_copy = new Image(getClass().getResourceAsStream("copy.png"));
                    ImageView img_copy = new ImageView(image_copy);
                    img_copy.setFitHeight(25);
                    img_copy.setFitWidth(25);
                    addButton.setGraphic(img_copy);

                    addButton.setOnAction(event -> {
                        // ObservableList<Parts> selectedItems =
                        // parts_table.getSelectionModel().getSelectedItems();

                        Parts part = getTableView().getItems().get(getIndex());
                        if (quntitie.getText().trim().isEmpty() == true) {
                            if (part.getQuntitie() >= 1) {
                                part.setQuntitie(1);
                                rdv_local.getParts().add(part);

                                list = FXCollections.observableArrayList(rdv_local.getParts());
                                part_rdv.setItems(list);
                                part_rdv.refresh();

                            } else {
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Confirmation de suppression");
                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.setGraphic(null);

                                dialogPane.getStylesheets()
                                        .add(getClass().getResource("/application/Viewfxml/part_style.css")
                                                .toExternalForm());
                                dialogPane.getStyleClass().add("dialog-pane ");

                                alert.initStyle(StageStyle.UNDECORATED);

                                alert.setContentText("La quantité est insuffisante !");

                                ButtonBar buttonBar = (ButtonBar) alert.getDialogPane().lookup(".button-bar");
                                Button cancelButton = (Button) buttonBar.getButtons().get(0);
                                cancelButton.getStyleClass().add("cancel_btn");
                                cancelButton.setText("OK");
                                alert.showAndWait();

                            }
                        } else {

                            if (part.getQuntitie() >= Integer.parseInt(quntitie.getText())) {
                                part.setQuntitie(Integer.parseInt(quntitie.getText()));
                                rdv_local.getParts().add(part);

                                list = FXCollections.observableArrayList(rdv_local.getParts());
                                part_rdv.setItems(list);
                                part_rdv.refresh();

                            } else {
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Confirmation de suppression");
                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.setGraphic(null);

                                dialogPane.getStylesheets()
                                        .add(getClass().getResource("/application/Viewfxml/part_style.css")
                                                .toExternalForm());
                                dialogPane.getStyleClass().add("dialog-pane ");

                                alert.initStyle(StageStyle.UNDECORATED);

                                alert.setContentText("La quantité est insuffisante !");

                                ButtonBar buttonBar = (ButtonBar) alert.getDialogPane().lookup(".button-bar");
                                Button cancelButton = (Button) buttonBar.getButtons().get(0);
                                cancelButton.getStyleClass().add("cancel_btn");
                                cancelButton.setText("OK");
                                alert.showAndWait();

                            }

                        }

                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox buttonsBox = new HBox(10, addButton);
                        getAlignment();
                        buttonsBox.setAlignment(Pos.CENTER);
                        setGraphic(buttonsBox);
                    }
                }
            };
        });

        // parts_table.setItems(list);

        filteredList = new FilteredList<>(list, b -> true);

        // Set the filter Predicate whenever the search text changes
        reserch_field.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<Parts> filteredList = new FilteredList<>(list, data -> true);
            filteredList.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (data.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (data.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(data.getQuntitie()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(data.getPrice()).contains(lowerCaseFilter)) {
                    return true;
                }

                parts_table.refresh();
                return false;
            });
            SortedList<Parts> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(parts_table.comparatorProperty());
            System.out.println(sortedList.size());
            parts_table.setItems(sortedList);
        });

        // Wrap the filtered list in a sorted list
        SortedList<Parts> sortedList = new SortedList<>(filteredList);

        // Bind the sorted list to the table
        parts_table.setItems(sortedList);

        parts_table.setRowFactory(tv -> {
            TableRow<Parts> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    Parts rowData = row.getItem();
                    System.out.println("Double clicked on: " + rowData);
                }
            });
            row.heightProperty().addListener((obs, oldHeight, newHeight) -> {
                if (newHeight.doubleValue() > oldHeight.doubleValue()) {
                    row.setMinHeight(newHeight.doubleValue());
                    row.setMinHeight(txt.getLayoutBounds().getHeight());
                    // row.setMinHeight(100);
                }
            });
            return row;
        });

    }
}
