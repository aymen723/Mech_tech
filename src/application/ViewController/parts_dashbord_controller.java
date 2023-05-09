package application.ViewController;

// import java.io.IOException;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
// import java.util.Iterator;
import java.util.ResourceBundle;
// import java.util.jar.Attributes.Name;

import org.bson.Document;
import org.bson.types.ObjectId;

import application.controller.AdminController;
import application.models.Fournisseur;
import application.models.Parts;
import javafx.beans.property.SimpleStringProperty;
// import javafx.beans.binding.Bindings;
// import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Callback;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class parts_dashbord_controller implements Initializable {
	@FXML
	private Button mod_btn;

	@FXML
	private Button add_btn;

	@FXML
	private Button annl_btn;

	@FXML
	private TextField description;

	@FXML
	private TextField name;

	@FXML
	private TextField price;

	@FXML
	private TextField quntitie;

	@FXML
	private TextField reserch_field;

	@FXML
	private TextField prixdachat;

	@FXML
	private DatePicker date;
	@FXML
	private TextField fournisseur_inp;

	@FXML
	private ContextMenu list_fornisseur;

	@FXML
	private TableColumn<Parts, Integer> prix_col;

	@FXML
	private TableColumn<Parts, Integer> quntite_col;

	@FXML
	private TableColumn<Parts, String> nom_col;
	@FXML
	private TableColumn<Parts, String> desc_col;

	@FXML
	private TableColumn<Parts, String> fournisseur_col;

	@FXML
	private TableColumn<Parts, Integer> prix_achat;
	@FXML
	private TableColumn<Parts, String> date_achat;
	@FXML
	private TableView<Parts> parts_table;

	@FXML
	private TableColumn<Parts, Void> actionsColumn;

	public static Parts part;

	private Text txt;

	private Fournisseur fournissur_local;

	ObservableList<Parts> list = FXCollections.observableArrayList();
	ObservableList<Fournisseur> list_fournisseur = FXCollections.observableArrayList();

	FilteredList<Parts> filteredList = new FilteredList<>(list, b -> true);

	public void add_parts(javafx.event.ActionEvent actione) {
		// System.out.println(name.getText().trim().isEmpty());
		if (name.getText().trim().isEmpty() == false &&
				description.getText().trim().isEmpty() == false &&
				quntitie.getText().trim().isEmpty() == false &&
				price.getText().trim().isEmpty() == false) {
			Document newpart = new Document("name", name.getText());
			newpart.append("price", Integer.parseInt(price.getText()));
			newpart.append("quantity", Integer.parseInt(quntitie.getText()));
			newpart.append("description", description.getText());
			newpart.append("buyingprice", Integer.parseInt(prixdachat.getText()));
			newpart.append("buyingdate", date.getValue());

			Document newfournisseur = new Document("_id", new ObjectId(fournissur_local.getId()));
			newfournisseur.append("nom", fournissur_local.getName());
			newfournisseur.append("adresse", fournissur_local.getAddress());
			newfournisseur.append("numero", fournissur_local.getPhone());
			newfournisseur.append("email", fournissur_local.getEmail());

			newpart.append("fournisseur", newfournisseur);

			AdminController.addpart(newpart);

			name.setText("");
			price.setText("");
			quntitie.setText("");
			description.setText("");
			System.out.println("hna list mazal");
			list = AdminController.PartList();
			System.out.println("hna wra list");
			parts_table.setItems(list);
			// filteredList = new FilteredList<>(list, b -> true);
			parts_table.refresh();
		} else {
			if (name.getText().trim().isEmpty() == true) {

				name.getStyleClass().add("inptempty");
			}
			if (description.getText().trim().isEmpty() == true) {

				description.getStyleClass().add("inptempty");
			}
			if (quntitie.getText().trim().isEmpty() == true) {

				quntitie.getStyleClass().add("inptempty");
			}

			if (price.getText().trim().isEmpty() == true) {

				price.getStyleClass().add("inptempty");
			}

		}

	}

	public void name_field(InputMethodEvent ev) {
		System.out.println("test hna fi name");

	}

	public void annl_mod() {

		name.setText("");
		price.setText("");
		quntitie.setText("");
		description.setText("");

		part = null;

		mod_btn.setDisable(true);
		add_btn.setDisable(false);
		annl_btn.setDisable(true);
		annl_btn.setVisible(false);

	}

	public void mod_parts(javafx.event.ActionEvent actione) {

		if ((name.getText().trim().isEmpty() == false) &&
				(description.getText().trim().isEmpty() == false) &&
				(price.getText().trim().isEmpty() == false) &&
				(quntitie.getText().trim().isEmpty() == false) && part != null) {
			// Document updatepart = new Document("name", name.getText());
			// updatepart.append("price", Integer.parseInt(price.getText()));
			// updatepart.append("quantity", Integer.parseInt(quntitie.getText()));
			// updatepart.append("description", description.getText());

			Document updatepart = new Document("name", name.getText());
			updatepart.append("price", Integer.parseInt(price.getText()));
			updatepart.append("quantity", Integer.parseInt(quntitie.getText()));
			updatepart.append("description", description.getText());
			updatepart.append("buyingprice", Integer.parseInt(prixdachat.getText()));
			updatepart.append("buyingdate", date.getValue());

			Document newfournisseur = new Document("_id", new ObjectId(fournissur_local.getId()));
			newfournisseur.append("nom", fournissur_local.getName());
			newfournisseur.append("adresse", fournissur_local.getAddress());
			newfournisseur.append("numero", fournissur_local.getPhone());
			newfournisseur.append("email", fournissur_local.getEmail());

			updatepart.append("fournisseur", newfournisseur);

			AdminController.updatepart(updatepart, part);

			add_btn.setDisable(false);
			mod_btn.setDisable(true);
			annl_btn.setDisable(true);
			annl_btn.setVisible(false);

			part = null;
			name.setText("");
			price.setText("");
			quntitie.setText("");
			description.setText("");
			fournisseur_inp.setText("");
			prixdachat.setText("");

			System.out.println("hna list mazal");
			list = AdminController.PartList();
			System.out.println("hna wra list");
			parts_table.setItems(list);
		} else {
			if (name.getText().trim().isEmpty() == true) {

				name.getStyleClass().add("inptempty");
			}
			if (description.getText().trim().isEmpty() == true) {

				description.getStyleClass().add("inptempty");
			}
			if (quntitie.getText().trim().isEmpty() == true) {

				quntitie.getStyleClass().add("inptempty");
			}

			if (price.getText().trim().isEmpty() == true) {

				price.getStyleClass().add("inptempty");
			}

		}

	}

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		annl_btn.setDisable(true);
		annl_btn.setVisible(false);
		mod_btn.setDisable(true);

		System.out.println("hna list mazal");
		list = AdminController.PartList();
		list_fournisseur = FXCollections.observableArrayList(AdminController.ListFournisseur());

		System.out.println("hna wra list");

		fournisseur_inp.textProperty().addListener((observable, oldValue, newValue) -> {
			list_fornisseur.getItems().clear();
			for (Fournisseur fournisseur : list_fournisseur) {
				if (fournisseur.getName().toLowerCase().contains(newValue.toLowerCase())) {
					MenuItem item = new MenuItem(fournisseur.getName());
					item.setOnAction(e -> {
						// do something with selected client
						// System.out.println(client.toString());
						fournissur_local = fournisseur;

						fournisseur_inp.setText(fournisseur.getName());
						;

					});

					list_fornisseur.getItems().add(item);

				}

			}

		});

		fournisseur_inp.setOnKeyTyped(e -> {
			if (fournisseur_inp.getText().trim() != null) {

				list_fornisseur.show(fournisseur_inp, Side.BOTTOM, 0, 0);
			}
		});

		// Hide context menu when search field loses focus
		fournisseur_inp.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) {
				list_fornisseur.hide();
			}
		});

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

				private final Button editButton = new Button("");
				private final Button deleteButton = new Button("");
				private final Button copybutton = new Button("");

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

					editButton.setOnAction(event -> {
						Parts Part_edit = getTableView().getItems().get(getIndex());
						part = Part_edit;

						Date date_l = part.getBuyingdate();
						LocalDate localDate = date_l.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						name.setText(Part_edit.getName());
						description.setText(Part_edit.getDescription());
						price.setText(Integer.toString(Part_edit.getPrice()));
						quntitie.setText(Integer.toString(Part_edit.getQuntitie()));
						date.setValue(localDate);
						prixdachat.setText(Integer.toString(part.getBuyingprice()));
						fournisseur_inp.setText(part.getFournisseur().getName());
						fournissur_local = part.getFournisseur();
						mod_btn.setDisable(false);
						add_btn.setDisable(true);
						annl_btn.setDisable(false);
						annl_btn.setVisible(true);

					});

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
							AdminController.deletpart(part);
							list = AdminController.PartList();
							parts_table.setItems(list);
							parts_table.refresh();
							// User clicked OK
						} else {
							// User clicked Cancel or closed the dialog

						}

					});

					copybutton.setOnAction(event -> {
						// ObservableList<Parts> selectedItems =
						// parts_table.getSelectionModel().getSelectedItems();

						Parts part = getTableView().getItems().get(getIndex());

						StringBuilder sb = new StringBuilder();

						sb.append(part.getDescription()).append("," + "\n");
						sb.append(part.getPrice()).append("," + "\n");
						sb.append(part.getQuntitie()).append("," + "\n");
						sb.append(part.getName()).append("\n");

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
		name.textProperty().addListener((observable, oldValue, newValue) -> {
			// Your code here to handle the text field value change
			System.out.println("Text changed from " + oldValue + " to " + newValue);
			if (name.getText().trim().isEmpty() == false) {
				name.getStyleClass().remove("inptempty");
			}

		});
		description.textProperty().addListener((observable, oldValue, newValue) -> {
			// Your code here to handle the text field value change
			System.out.println("Text changed from " + oldValue + " to " + newValue);
			if (description.getText().trim().isEmpty() == false) {
				description.getStyleClass().remove("inptempty");
			}

		});
		quntitie.textProperty().addListener((observable, oldValue, newValue) -> {
			// Your code here to handle the text field value change
			System.out.println("Text changed from " + oldValue + " to " + newValue);
			if (quntitie.getText().trim().isEmpty() == false) {
				quntitie.getStyleClass().remove("inptempty");
			}

		});
		price.textProperty().addListener((observable, oldValue, newValue) -> {
			// Your code here to handle the text field value change
			System.out.println("Text changed from " + oldValue + " to " + newValue);
			if (price.getText().trim().isEmpty() == false) {
				price.getStyleClass().remove("inptempty");
			}
		});

	}
}