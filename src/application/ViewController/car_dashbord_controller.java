package application.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.bson.Document;

import application.controller.AdminController;
import application.models.Car;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

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
import javafx.scene.control.TableRow;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class car_dashbord_controller implements Initializable {
    @FXML
    private Button mod_btn;

    @FXML
    private Button add_btn;

    @FXML
    private Button annl_btn;

    @FXML
    private TextField marque;

    @FXML
    private TextField modele;

    @FXML
    private TextField couleur;

    @FXML
    private TextField matricule;

    @FXML
    private TextField vin;

    @FXML
    private TableColumn<Car, String> marque_col;

    @FXML
    private TableColumn<Car, String> modele_col;

    @FXML
    private TableColumn<Car, String> couleur_col;
    @FXML
    private TableColumn<Car, String> matricule_col;

    @FXML
    private TableColumn<Car, String> vin_col;

    @FXML
    private TableView<Car> car_table;

    @FXML
    private TableColumn<Car, Void> actionsColumn;

    @FXML
    private BorderPane car_container;

    public static Car car;

    private Text txt;

    ObservableList<Car> list = FXCollections.observableArrayList();

    FilteredList<Car> filteredList = new FilteredList<>(list, b -> true);

    public void add_parts(javafx.event.ActionEvent actione) {
        if (marque.getText().trim().isEmpty() == false &&
                modele.getText().trim().isEmpty() == false &&
                couleur.getText().trim().isEmpty() == false &&
                vin.getText().trim().isEmpty() == false &&
                matricule.getText().trim().isEmpty() == false) {
            Document newcar = new Document("marque", marque.getText());
            newcar.append("matricule", matricule.getText());
            newcar.append("couleur", couleur.getText());
            newcar.append("modele", modele.getText());
            newcar.append("vin", vin.getText());

            AdminController.AddCar(newcar);

            marque.setText("");
            matricule.setText("");
            couleur.setText("");
            modele.setText("");
            vin.setText("");
            System.out.println("hna list mazal");
            list = AdminController.CarLiist();
            System.out.println("hna wra list");
            car_table.setItems(list);
            filteredList = new FilteredList<>(list, b -> true);
            car_table.refresh();
        } else {
            if (marque.getText().trim().isEmpty() == true) {

                marque.getStyleClass().add("inptempty");
            }
            if (matricule.getText().trim().isEmpty() == true) {

                matricule.getStyleClass().add("inptempty");
            }
            if (couleur.getText().trim().isEmpty() == true) {

                couleur.getStyleClass().add("inptempty");
            }

            if (modele.getText().trim().isEmpty() == true) {

                modele.getStyleClass().add("inptempty");
            }

            if (vin.getText().trim().isEmpty() == true) {

                vin.getStyleClass().add("inptempty");
            }

        }

    }

    public void annl_mod() {

        marque.setText("");
        matricule.setText("");
        couleur.setText("");
        modele.setText("");
        vin.setText("");

        car = null;

        mod_btn.setDisable(true);
        add_btn.setDisable(false);
        annl_btn.setDisable(true);
        annl_btn.setVisible(false);

    }

    public void mod_parts(javafx.event.ActionEvent actione) {

        if ((marque.getText().trim().isEmpty() == false) &&
                (matricule.getText().trim().isEmpty() == false) &&
                (couleur.getText().trim().isEmpty() == false) &&
                (modele.getText().trim().isEmpty() == false) &&
                (vin.getText().trim().isEmpty() == false) && car != null) {
            Document updatecar = new Document("name", marque.getText());
            updatecar.append("matricule", matricule.getText());
            updatecar.append("couleur", couleur.getText());
            updatecar.append("modele", modele.getText());
            updatecar.append("vin", vin.getText());

            AdminController.UpdateCar(updatecar, car);

            add_btn.setDisable(false);
            mod_btn.setDisable(true);
            annl_btn.setDisable(true);
            annl_btn.setVisible(false);

            car = null;
            marque.setText("");
            matricule.setText("");
            couleur.setText("");
            modele.setText("");
            vin.setText("");

            System.out.println("hna list mazal");
            list = AdminController.CarLiist();
            System.out.println("hna wra list");
            car_table.setItems(list);
        } else {
            if (marque.getText().trim().isEmpty() == true) {

                marque.getStyleClass().add("inptempty");
            }
            if (matricule.getText().trim().isEmpty() == true) {

                matricule.getStyleClass().add("inptempty");
            }
            if (couleur.getText().trim().isEmpty() == true) {

                couleur.getStyleClass().add("inptempty");
            }

            if (modele.getText().trim().isEmpty() == true) {

                modele.getStyleClass().add("inptempty");
            }
            if (vin.getText().trim().isEmpty() == true) {

                vin.getStyleClass().add("inptempty");
            }

        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        annl_btn.setDisable(true);
        annl_btn.setVisible(false);
        mod_btn.setDisable(true);

        System.out.println("hna list mazal");
        list = AdminController.CarLiist();
        System.out.println("hna wra list");

        marque_col.setCellValueFactory(new PropertyValueFactory<>("marque"));
        modele_col.setCellValueFactory(new PropertyValueFactory<>("modele"));
        couleur_col.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        matricule_col.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        vin_col.setCellValueFactory(new PropertyValueFactory<>("vin"));

        actionsColumn.setCellFactory(column -> {

            return new TableCell<Car, Void>() {

                private final Button editButton = new Button("");

                {

                    editButton.setStyle(
                            "-fx-background-radius: 5em; -fx-min-width: 25px; -fx-min-height: 25px;" +
                                    "-fx-max-width: 25px; -fx-max-height: 25px; -fx-background-color: transparent;" +
                                    "-fx-alignment:CENTER;");
                    Image image_edit = new Image(getClass().getResourceAsStream("menu.png"));
                    ImageView img_edit = new ImageView(image_edit);
                    img_edit.setFitHeight(25);
                    img_edit.setFitWidth(25);
                    editButton.setGraphic(img_edit);

                    editButton.setOnAction(event -> {
                        Car car_edit = getTableView().getItems().get(getIndex());
                        car = car_edit;

                        try {

                            FXMLLoader loader = new FXMLLoader(
                                    getClass().getResource("/application/Viewfxml/car_details.fxml"));

                            Parent root = loader.load();
                            Car_details car_con = loader.getController();
                            // System.out.println(rdv_details_con);

                            // System.out.println(rdv.getDescrption_in());
                            car_con.getcar(car_edit);
                            car_container.getChildren().removeAll();
                            car_container.getChildren().setAll(root);

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
                        HBox buttonsBox = new HBox(10, editButton);
                        getAlignment();
                        buttonsBox.setAlignment(Pos.CENTER);
                        setGraphic(buttonsBox);
                    }
                }
            };
        });

        car_table.setItems(list);

        filteredList = new FilteredList<>(list, b -> true);

        // Set the filter Predicate whenever the search text changes
        // reserch_field.textProperty().addListener((observable, oldValue, newValue) ->
        // {
        // FilteredList<Parts> filteredList = new FilteredList<>(list, data -> true);
        // filteredList.setPredicate(data -> {
        // if (newValue == null || newValue.isEmpty()) {
        // return true;
        // }
        // String lowerCaseFilter = newValue.toLowerCase();
        // if (data.getName().toLowerCase().contains(lowerCaseFilter)) {
        // return true;
        // } else if (data.getDescription().toLowerCase().contains(lowerCaseFilter)) {
        // return true;
        // } else if (String.valueOf(data.getQuntitie()).contains(lowerCaseFilter)) {
        // return true;
        // } else if (String.valueOf(data.getPrice()).contains(lowerCaseFilter)) {
        // return true;
        // }

        // parts_table.refresh();
        // return false;
        // });
        // SortedList<Parts> sortedList = new SortedList<>(filteredList);
        // sortedList.comparatorProperty().bind(parts_table.comparatorProperty());
        // System.out.println(sortedList.size());
        // parts_table.setItems(sortedList);
        // });

        // Wrap the filtered list in a sorted list
        SortedList<Car> sortedList = new SortedList<>(filteredList);

        // Bind the sorted list to the table
        car_table.setItems(sortedList);

        // car_table.setRowFactory(tv -> {
        // TableRow<Car> row = new TableRow<>();
        // row.setOnMouseClicked(event -> {
        // if (!row.isEmpty() && event.getClickCount() == 2) {
        // Car rowData = row.getItem();
        // System.out.println("Double clicked on: " + rowData);
        // }
        // });
        // row.heightProperty().addListener((obs, oldHeight, newHeight) -> {
        // if (newHeight.doubleValue() > oldHeight.doubleValue()) {
        // row.setMinHeight(newHeight.doubleValue());
        // row.setMinHeight(txt.getLayoutBounds().getHeight());
        // // row.setMinHeight(100);
        // }
        // });
        // return row;
        // });
        marque.textProperty().addListener((observable, oldValue, newValue) -> {
            // Your code here to handle the text field value change
            System.out.println("Text changed from " + oldValue + " to " + newValue);
            if (marque.getText().trim().isEmpty() == false) {
                marque.getStyleClass().remove("inptempty");
            }

        });
        modele.textProperty().addListener((observable, oldValue, newValue) -> {
            // Your code here to handle the text field value change
            System.out.println("Text changed from " + oldValue + " to " + newValue);
            if (modele.getText().trim().isEmpty() == false) {
                modele.getStyleClass().remove("inptempty");
            }

        });
        couleur.textProperty().addListener((observable, oldValue, newValue) -> {
            // Your code here to handle the text field value change
            System.out.println("Text changed from " + oldValue + " to " + newValue);
            if (couleur.getText().trim().isEmpty() == false) {
                couleur.getStyleClass().remove("inptempty");
            }

        });
        matricule.textProperty().addListener((observable, oldValue, newValue) -> {
            // Your code here to handle the text field value change
            System.out.println("Text changed from " + oldValue + " to " + newValue);
            if (matricule.getText().trim().isEmpty() == false) {
                matricule.getStyleClass().remove("inptempty");
            }
        });

        vin.textProperty().addListener((observable, oldValue, newValue) -> {
            // Your code here to handle the text field value change
            System.out.println("Text changed from " + oldValue + " to " + newValue);
            if (vin.getText().trim().isEmpty() == false) {
                vin.getStyleClass().remove("inptempty");
            }
        });
    }

}