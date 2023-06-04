package application.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import application.controller.AdminController;
import application.models.Rendez_vous;
import application.models.Sales;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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
    private TableColumn<Sales, String> total_col;
    @FXML
    private TableColumn<Sales, String> Number_pieces;

    @FXML
    private TextField reserch_field;

    @FXML
    private BorderPane sales_container;

    private ProgressIndicator progressIndicator = new ProgressIndicator();

    private FilteredList<Sales> filteredsales;

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

        // try {
        // Parent fxml =
        // FXMLLoader.load(getClass().getResource("/application/Viewfxml/ajouter_sales.fxml"));
        // Stage stage = new Stage();
        // Scene scene = new Scene(fxml);
        // stage.setScene(scene);
        // stage.setTitle("Mecha Tech");
        // scene.setFill(Color.TRANSPARENT);

        // // primaryStage.initStyle(StageStyle.UNDECORATED);
        // // primaryStage.initStyle(StageStyle.TRANSPARENT);

        // // primaryStage.setResizable(false);
        // stage.show();

        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }

    private void loadData() {
        // Create a task to load the data in the background
        Task<ArrayList<Sales>> loadTask = new Task<ArrayList<Sales>>() {
            @Override
            protected ArrayList<Sales> call() throws Exception {
                // Perform your data loading operation here

                return AdminController.ListSales();
            }
        };

        // Set up loading indicator
        Alert loadingAlert = new Alert(AlertType.INFORMATION);
        loadingAlert.setTitle("Loading");
        loadingAlert.setHeaderText("Please wait...");
        loadingAlert.setContentText("Loading data from the server...");
        loadingAlert.initOwner(sales_table.getScene().getWindow());
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
            ArrayList<Sales> listsale = loadTask.getValue();

            // Update the UI with the loaded data
            Platform.runLater(() -> {
                ObservableList<Sales> list = FXCollections.observableArrayList(listsale);
                sales_table.setItems(list);
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
        errorAlert.initOwner(sales_table.getScene().getWindow());
        errorAlert.showAndWait();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

        // list = FXCollections.observableArrayList(AdminController.ListSales());
        // System.out.println(list.size());

        ChangeListener<Scene> chl = new ChangeListener<Scene>() {

            @Override
            public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
                if (newValue != null) {
                    loadData();
                }
            }

        };
        sales_table.sceneProperty().addListener(chl);

        date_de_vente_col.setCellValueFactory(new PropertyValueFactory<>("date_de_vente"));
        // total_col.setCellValueFactory(new PropertyValueFactory<>("Prenom"));

        Number_pieces.setCellValueFactory(
                cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().nuberofparts())));

        total_col.setCellValueFactory(
                cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().total())));

        action_col.setCellFactory(column -> {

            return new TableCell<Sales, Void>() {

                private final Button deleteButton = new Button("");
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
                            Sales sales = getTableView().getItems().get(getIndex());
                            AdminController.deleteSale(sales);
                            AdminController.update_parts_qtnt_delete(sales.getPartList());
                            list = FXCollections.observableArrayList(AdminController.ListSales());
                            sales_table.setItems(list);
                            sales_table.refresh();
                            // User clicked OK
                        } else {
                            // User clicked Cancel or closed the dialog

                        }

                    });

                    editButton.setOnAction(event -> {
                        Sales sales = getTableView().getItems().get(getIndex());

                        try {

                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("/application/Viewfxml/mod_sales.fxml"));

                            Parent root = loader.load();
                            mod_sales mod_con = loader.getController();

                            mod_con.getsale(sales);
                            sales_container.getChildren().removeAll();
                            sales_container.getChildren().setAll(root);

                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                    });

                }

                @Override
                protected void updateItem(Void item, boolean empty) {

                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox buttonsBox = new HBox(10, editButton, deleteButton);
                        getAlignment();
                        buttonsBox.setAlignment(Pos.CENTER);
                        setGraphic(buttonsBox);
                    }
                }
            };
        });

        // sales_table.setItems(list);

        filteredsales = new FilteredList<>(list, b -> true);

        // Set the filter Predicate whenever the search text changes
        reserch_field.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<Sales> filteredList = new FilteredList<>(list, data -> true);
            filteredList.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                // if (data.getDate_de_vente().contains(lowerCaseFilter)) {
                // return true;
                // }

                sales_table.refresh();
                return false;
            });
            SortedList<Sales> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(sales_table.comparatorProperty());
            System.out.println(sortedList.size());
            sales_table.setItems(sortedList);
        });

        // Wrap the filtered list in a sorted list
        SortedList<Sales> sortedList = new SortedList<>(filteredsales);

        // Bind the sorted list to the table
        sales_table.setItems(sortedList);
    }
}
