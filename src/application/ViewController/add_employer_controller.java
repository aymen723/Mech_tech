package application.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.controller.AdminController;
import application.models.Usermodel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class add_employer_controller implements Initializable {

	@FXML
	public TableColumn<Usermodel, String> username = new TableColumn<>("username");

	@FXML
	public TableColumn<Usermodel, String> nom = new TableColumn<>("nom");

	@FXML
	public TableColumn<Usermodel, String> prenom = new TableColumn<>("prenom");

	@FXML
	public TableColumn<Usermodel, String> nuermo = new TableColumn<>("nuermo");

	@FXML
	public TableColumn<Usermodel, String> email = new TableColumn<>("email");

	@FXML
	public TableColumn<Usermodel, String> role = new TableColumn<>("role");

	@FXML
	public TableColumn<Usermodel, Void> action_col;

	@FXML
	private TableView<Usermodel> table;

	@FXML
	private Button add_emp;

	@FXML
	private Button mod_emp;

	@FXML
	private Button delete_btn;

	@FXML
	private BorderPane emp_container;

	@FXML
	private TextField search_field;

	@FXML
	private Button ajouter_employer;

	public static Usermodel user;

	private Usermodel user_local;

	ObservableList<Usermodel> list = FXCollections.observableArrayList(
			new Usermodel("1", "test", "test", "test", "test", "0011", "test", "test1", "admin"),
			new Usermodel("2", "teszdat", "test", "test", "test", "0022", "test", "test2", "admint"),
			new Usermodel("3", "test3", "test", "test", "test", "0033", "test", "test3", "addd"),
			new Usermodel("4", "test4", "test", "test", "test", "0044", "test", "test4", "slave"));

	FilteredList<Usermodel> filteredList = new FilteredList<>(list, b -> true);

	public void add_emp_conatiner() {
		System.out.println("test hna1");

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/add_employe_container.fxml"));
			emp_container.getChildren().removeAll();
			emp_container.getChildren().setAll(fxml);
			System.out.println("test hna 2");

		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("test hna 3");

	}

	public void mod_emp_conatiner() {
		System.out.println("test hna 3");
		TableViewSelectionModel<Usermodel> selectionModel = table.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		System.out.println("hna fl mod " + selectionModel.getSelectedItems().get(0).getId());
		Usermodel user_mod = selectionModel.getSelectedItems().get(0);
		user = user_mod;
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/mod_employe_container.fxml"));
			emp_container.getChildren().removeAll();
			emp_container.getChildren().setAll(fxml);
			System.out.println("test hna 2");

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// public void return_back(javafx.event.ActionEvent event) {
	//
	// try {
	// Parent fxml =
	// FXMLLoader.load(getClass().getResource("add_employe_dashbord.fxml"));
	// emp_container.getChildren().removeAll();
	// emp_container.getChildren().setAll(fxml);
	//
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("hna list mazal");
		list = AdminController.EmpLiist();
		System.out.println("hna wra list");

		username.setCellValueFactory(new PropertyValueFactory<>("username"));
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
		nuermo.setCellValueFactory(new PropertyValueFactory<>("numero"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		role.setCellValueFactory(new PropertyValueFactory<>("role"));

		action_col.setCellFactory(column -> {

			return new TableCell<Usermodel, Void>() {

				private final Button editButton = new Button("");
				private final Button deleteButton = new Button("");
				private final Button copybutton = new Button("");

				{

					// deleteButton.getStylesheets()
					// .add(getClass().getResource("/application/Viewfxml/button_table.css").toExternalForm());
					// deleteButton.getStyleClass().add("delete");
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
						Usermodel user_edit = getTableView().getItems().get(getIndex());
						try {
							FXMLLoader loader = new FXMLLoader(
									getClass().getResource("/application/Viewfxml/mod_employe_container.fxml"));
							Parent root = loader.load();
							mod_employe_container_controller moduser = loader.getController();
							moduser.getuser(user_edit);
							emp_container.getChildren().removeAll();
							emp_container.getChildren().setAll(root);

						} catch (Exception e) {
							// TODO: handle exception
						}

					});

					deleteButton.setOnAction(event -> {

						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Confirmation de suppression");
						DialogPane dialogPane = alert.getDialogPane();
						dialogPane.setGraphic(null);

						dialogPane.getStylesheets()
								.add(getClass().getResource("/application/Viewfxml/part_style.css").toExternalForm());
						dialogPane.getStyleClass().add("dialog-pane");

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
							Usermodel user = getTableView().getItems().get(getIndex());
							AdminController.deletemp(user);
							list = AdminController.EmpLiist();
							table.setItems(list);
							table.refresh();
						} else {
							// User clicked Cancel or closed the dialog

						}

					});

					copybutton.setOnAction(event -> {
						ObservableList<Usermodel> selectedItems = table.getSelectionModel().getSelectedItems();

						Usermodel user = getTableView().getItems().get(getIndex());

						StringBuilder sb = new StringBuilder();

						sb.append(user.getNom()).append("," + "\n");
						sb.append(user.getPrenom()).append("," + "\n");
						sb.append(user.getEmail()).append("," + "\n");
						sb.append(user.getRole()).append("\n");

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

		table.setItems(list);

		filteredList = new FilteredList<>(list, b -> true);

		// Set the filter Predicate whenever the search text changes
		search_field.textProperty().addListener((observable, oldValue, newValue) -> {
			FilteredList<Usermodel> filteredList = new FilteredList<>(list, data -> true);
			filteredList.setPredicate(data -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (data.getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (data.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (String.valueOf(data.getUsername()).contains(lowerCaseFilter)) {
					return true;
				} else if (String.valueOf(data.getEmail()).contains(lowerCaseFilter)) {
					return true;
				} else if (String.valueOf(data.getNumero()).contains(lowerCaseFilter)) {
					return true;
				} else if (String.valueOf(data.getAddress()).contains(lowerCaseFilter)) {
					return true;
				} else if (String.valueOf(data.getRole()).contains(lowerCaseFilter)) {
					return true;
				}

				table.refresh();
				return false;
			});
			SortedList<Usermodel> sortedList = new SortedList<>(filteredList);
			sortedList.comparatorProperty().bind(table.comparatorProperty());
			System.out.println(sortedList.size());
			table.setItems(sortedList);
		});

		// Wrap the filtered list in a sorted list
		SortedList<Usermodel> sortedList = new SortedList<>(filteredList);

		// Bind the sorted list to the table
		table.setItems(sortedList);

	}

}
