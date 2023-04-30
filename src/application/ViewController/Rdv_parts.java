package application.ViewController;

// import java.io.IOException;
import java.net.URL;
// import java.util.ArrayList;
import java.util.ResourceBundle;

import application.controller.AdminController;
import application.models.Parts;
import application.models.Rendez_vous;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
// import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
// import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
// import javafx.scene.paint.Color;
import javafx.scene.text.Text;
// import javafx.stage.Stage;
import javafx.util.Callback;

public class Rdv_parts implements Initializable {

    @FXML
    private Button add_btn;

    @FXML
    private Button annl_btn;

    @FXML
    private TextField quntitie;

    @FXML
    private TextField reserch_field;

    @FXML
    private TableColumn<Parts, Integer> prix_col;

    @FXML
    private TableColumn<Parts, Integer> quntite_col;

    @FXML
    private TableColumn<Parts, String> nom_col;
    @FXML
    private TableColumn<Parts, String> desc_col;

    @FXML
    private TableColumn<Parts, String> id;

    @FXML
    private TableView<Parts> parts_table;

    @FXML
    private Button btn_conferm;

    public static Parts part;

    private Text txt;

    @FXML
    private BorderPane rdv_parts_container;

    // private ArrayList<Parts> parts_list;

    private Rendez_vous rdv_local;

    ObservableList<Parts> list = FXCollections.observableArrayList();

    FilteredList<Parts> filteredList = new FilteredList<>(list, b -> true);

    public void add_parts() {

        TableViewSelectionModel<Parts> selectionModel = parts_table.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        Parts part = selectionModel.getSelectedItems().get(0);

        part.setQuntitie(Integer.parseInt(quntitie.getText()));
        System.out.println(part.getName() + " is added");

        rdv_local.getParts().add(part);
    }

    public void annl_mod() {

        quntitie.setText("");

        part = null;

        add_btn.setDisable(false);
        annl_btn.setDisable(true);
        annl_btn.setVisible(false);

    }

    public void setrdv(Rendez_vous rdv) {
        this.rdv_local = rdv;

    }

    @FXML
    void conferm() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Viewfxml/rdv_details.fxml"));
            Parent root = loader.load();
            System.out.println(rdv_local.getClient_rdv().getNom() + " is the one to be sent");
            Rdv_details rdv_details_con = loader.getController();
            rdv_details_con.getrdv(rdv_local);
            System.out.println(rdv_local.getClient_rdv().getNom() + " is the one sent");
            rdv_parts_container.getChildren().removeAll();
            rdv_parts_container.getChildren().setAll(root);
            rdv_details_con.setContent(root);

        } catch (Exception e) {
            // TODO: handle exception
        }

        // Parent fxml;
        // try {
        // // fxml =
        // //
        // FXMLLoader.load(getClass().getResource("/application/Viewfxml/rdv_details.fxml"));
        // FXMLLoader loader = new
        // FXMLLoader(getClass().getResource("/application/Viewfxml/rdv_details.fxml"));
        // Parent root = loader.load();
        // System.out.println(rdv_local.getClient_rdv().getNom() + " is the one to be
        // sent");
        // Rdv_details rdv_details_con = loader.getController();
        // rdv_details_con.getrdv(rdv_local);
        // System.out.println(rdv_local.getClient_rdv().getNom() + " is the one sent");

        // Stage stage = new Stage();
        // Scene scene = new Scene(root);
        // stage.setScene(scene);
        // stage.setTitle("Mecha Tech");
        // scene.setFill(Color.TRANSPARENT);

        // stage.show();

        // } catch (IOException e) {

        // e.printStackTrace();
        // }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

        // annl_btn.setDisable(true);
        // annl_btn.setVisible(false);

        System.out.println("hna list mazal");
        list = AdminController.PartList();
        System.out.println("hna wra list");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        prix_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        quntite_col.setCellValueFactory(new PropertyValueFactory<>("quntitie"));

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

        parts_table.setItems(list);

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
