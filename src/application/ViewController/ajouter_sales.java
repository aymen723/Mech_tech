package application.ViewController;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.bson.Document;
import org.bson.types.ObjectId;

import application.controller.AdminController;
import application.models.Fournisseur;
import application.models.Parts;
import application.models.Sales;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

public class ajouter_sales implements Initializable {

    @FXML
    private TableColumn<Parts, Void> action_col_sale;

    @FXML
    private TableColumn<Parts, Void> actionsColumn;

    @FXML
    private TableColumn<Parts, String> date_achat;

    @FXML
    private TableColumn<Parts, String> desc_col;

    @FXML
    private TableColumn<Parts, String> fournisseur_col;

    @FXML
    private TableColumn<Parts, String> nom_col;

    @FXML
    private TableView<Parts> parts_table;

    @FXML
    private TableColumn<Parts, Integer> prix_achat;

    @FXML
    private TableColumn<Parts, Integer> prix_col;

    @FXML
    private TableColumn<Parts, Integer> quntite_col;

    @FXML
    private TableColumn<Parts, String> sale_part_datedachat;

    @FXML
    private TableColumn<Parts, String> sale_part_desc;

    @FXML
    private TableColumn<Parts, String> sale_part_fournisseur;

    @FXML
    private TableColumn<Parts, String> sale_part_nom;

    @FXML
    private TableColumn<Parts, String> sale_part_prix;

    @FXML
    private TableColumn<Parts, String> sale_part_prixdachat;

    @FXML
    private TableColumn<Parts, Integer> sale_part_quntite;

    @FXML
    private TableView<Parts> sales_table;

    @FXML
    private TextField quanitie;

    @FXML
    private TextField search;

    private Text txt;

    @FXML
    private Button btn_retour;

    @FXML
    private BorderPane addsale_container;

    // private Sales sale_local;

    ObservableList<Parts> list_parts = FXCollections.observableArrayList();
    ObservableList<Parts> sales_part = FXCollections.observableArrayList();
    ArrayList<Parts> array_part = new ArrayList<Parts>();
    FilteredList<Parts> filteredList = new FilteredList<>(list_parts, b -> true);

    @FXML
    void retour(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Sales_dashbord.fxml"));
            addsale_container.getChildren().removeAll();
            addsale_container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void confermier(ActionEvent event) {

        LocalDate date_sal = LocalDate.now();
        Date date = Date.from(date_sal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        // sale_local.setDate_de_vente(date);

        if (sales_part.isEmpty() == false) {
            Document newsale = new Document("date_de_vente", date);

            List<Document> myDocuments = new ArrayList<Document>();
            for (int i = 0; i < sales_part.size(); i++) {
                Parts part = sales_part.get(i);
                Document addpart = new Document("_id", new ObjectId(part.getId()));
                addpart.append("name", part.getName());
                addpart.append("price", part.getPrice());
                addpart.append("quantity", part.getQuntitie());
                addpart.append("prix_achat", part.getBuyingprice());
                addpart.append("date_de_vente", part.getBuyingdate());

                Fournisseur fournisseur = part.getFournisseur();
                Document docfournisseur = new Document("_id", new ObjectId(fournisseur.getId()));
                docfournisseur.append("name", fournisseur.getName());
                docfournisseur.append("adresse", fournisseur.getAddress());
                docfournisseur.append("email", fournisseur.getEmail());
                docfournisseur.append("numero", fournisseur.getPhone());

                addpart.append("fournisseur", docfournisseur);

                myDocuments.add(addpart);

                System.out.println(myDocuments.get(i));
            }

            newsale.append("parts", myDocuments);

            AdminController.AddSale(newsale);
            AdminController.update_parts_qtnt(array_part);

        }

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Sales_dashbord.fxml"));
            addsale_container.getChildren().removeAll();
            addsale_container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // LocalDate date_sal = LocalDate.now();
        // Date date =
        // Date.from(date_sal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        // sale_local.setDate_de_vente(date);

        list_parts = AdminController.PartList();
        // this is the collums for the parts table
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

        // this are collums for parts sales

        sale_part_nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        sale_part_prix.setCellValueFactory(new PropertyValueFactory<>("price"));
        sale_part_quntite.setCellValueFactory(new PropertyValueFactory<>("quntitie"));
        sale_part_prixdachat.setCellValueFactory(new PropertyValueFactory<>("buyingprice"));

        sale_part_datedachat.setCellValueFactory(
                cellData -> new SimpleStringProperty(DATE_FORMAT.format(cellData.getValue().getBuyingdate())));

        sale_part_fournisseur.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getFournisseur().getName()));

        sale_part_desc.setCellValueFactory(new PropertyValueFactory<>("description"));

        sale_part_desc.setCellFactory(new Callback<TableColumn<Parts, String>, TableCell<Parts, String>>() {
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

                        if (quanitie.getText().trim().isEmpty() == true) {

                            part.setQuntitie(1);
                            sales_part.add(part);
                            array_part.add(part);

                        } else {
                            part.setQuntitie(Integer.parseInt(quanitie.getText()));
                            sales_part.add(part);
                            array_part.add(part);

                        }
                        sales_table.setItems(sales_part);
                        sales_table.refresh();

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

        action_col_sale.setCellFactory(column -> {

            return new TableCell<Parts, Void>() {

                private final Button deleteButton = new Button("");

                {

                    deleteButton.setStyle(
                            "-fx-background-radius: 5em; -fx-min-width: 25px; -fx-min-height: 25px; -fx-max-width: 25px; -fx-max-height: 25px; -fx-background-color: transparent; -fx-alignment:CENTER;");

                    Image image_copy = new Image(getClass().getResourceAsStream("Delete.png"));
                    ImageView img_copy = new ImageView(image_copy);
                    img_copy.setFitHeight(25);
                    img_copy.setFitWidth(25);
                    deleteButton.setGraphic(img_copy);

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
                            Parts part = getTableView().getItems().get(getIndex());
                            sales_part.remove(part);
                            array_part.remove(part);
                            sales_table.setItems(sales_part);
                            sales_table.refresh();
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
                        HBox buttonsBox = new HBox(10, deleteButton);
                        getAlignment();
                        buttonsBox.setAlignment(Pos.CENTER);
                        setGraphic(buttonsBox);
                    }
                }
            };
        });

        parts_table.setItems(list_parts);

        filteredList = new FilteredList<>(list_parts, b -> true);

        // Set the filter Predicate whenever the search text changes
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<Parts> filteredList = new FilteredList<>(list_parts, data -> true);
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
    }

}
