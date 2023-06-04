package application.ViewController;

import java.io.IOException;
// import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
// import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import application.controller.AdminController;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
// import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
// import javafx.scene.paint.Color;
// import javafx.stage.Stage;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Rdv_veiwcontroller implements Initializable {

	@FXML
	private TableColumn<Rendez_vous, String> date_deut_col;

	@FXML
	private TableColumn<Rendez_vous, String> date_fin_col;

	// @FXML
	// private TableColumn<Rendez_vous, String> desc_col;

	@FXML
	private TableColumn<Rendez_vous, Void> action;

	@FXML
	private TableColumn<Rendez_vous, String> nom_client_col;

	@FXML
	private TableColumn<Rendez_vous, String> num_client_col;

	@FXML
	private TableColumn<Rendez_vous, String> prenom_client_col;

	@FXML
	private TableColumn<Rendez_vous, String> voiture_model_col;

	@FXML
	private TableColumn<Rendez_vous, String> etat_col;

	@FXML
	private TableView<Rendez_vous> rdv_table;

	@FXML
	private Pane table_container;

	@FXML
	private BorderPane rdv_container;

	@FXML
	private Button select_btn;

	@FXML
	private AnchorPane tables;

	@FXML
	private Button ajouter_btn;

	@FXML
	private Button modifier_btn;

	private Rendez_vous rdv_global;

	@FXML
	private TextField search_rdv;

	@FXML
	private Button All_btn;

	@FXML
	private Button month_btn;

	@FXML
	private Button week_btn;

	@FXML
	private Button year_btn;

	private ProgressIndicator progressIndicator = new ProgressIndicator();

	private ArrayList<Rendez_vous> listrdv = new ArrayList<Rendez_vous>();

	private FilteredList<Rendez_vous> filteredRendezVousList;
	// Arraylist<Parts> list =

	LocalDate date = LocalDate.now();

	LocalDate date_debut = date.minusDays(1);
	LocalDate date_fin = date.plusDays(2);
	ObservableList<Rendez_vous> list = FXCollections.observableArrayList();

	public void rdv_ajouter() {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/ajouter_rdv.fxml"));
			rdv_container.getChildren().removeAll();
			rdv_container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

		// try {
		// Parent fxml =
		// FXMLLoader.load(getClass().getResource("/application/Viewfxml/ajouter_rdv.fxml"));
		// Stage stage = new Stage();
		// Scene scene = new Scene(fxml);
		// stage.setScene(scene);
		// stage.setTitle("Mecha Tech");

		// // primaryStage.initStyle(StageStyle.UNDECORATED);
		// // primaryStage.initStyle(StageStyle.TRANSPARENT);

		// // primaryStage.setResizable(false);
		// stage.show();

		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public void rdv_modifier() {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/modifier_rdv.fxml"));
			rdv_container.getChildren().removeAll();
			rdv_container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@FXML
	void All(ActionEvent event) {
		rdv_table.setItems(list);
	}

	@FXML
	void this_month(ActionEvent event) {

		ObservableList<Rendez_vous> rdvs = rdv_table.getItems();
		LocalDate currentDate = LocalDate.now();
		int currentMonth = currentDate.getMonthValue();
		Predicate<Rendez_vous> monthFilter = item -> (item.getDate_debut().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate().getMonthValue() == currentMonth)
				|| (item.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						.getMonthValue() == currentMonth);
		List<Rendez_vous> filtered = rdvs.stream().filter(monthFilter).toList();
		rdvs = FXCollections.observableList(filtered);
		rdv_table.setItems(rdvs);

	}

	@FXML
	void this_week(ActionEvent event) {
		ObservableList<Rendez_vous> rdvs = rdv_table.getItems();
		LocalDate currentDate = LocalDate.now();
		LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY);
		LocalDate endOfWeek = startOfWeek.plusDays(6);
		Predicate<Rendez_vous> weekFilter = item -> {
			LocalDate itemDatedebut = item.getDate_debut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate itemDatefin = item.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			return (!itemDatedebut.isBefore(startOfWeek) && !itemDatedebut.isAfter(endOfWeek))
					|| (!itemDatefin.isBefore(startOfWeek) && !itemDatefin.isAfter(endOfWeek));
		};
		List<Rendez_vous> filtered = rdvs.stream().filter(weekFilter).toList();
		rdvs = FXCollections.observableList(filtered);
		rdv_table.setItems(rdvs);
	}

	@FXML
	void this_year(ActionEvent event) {

		ObservableList<Rendez_vous> rdvs = rdv_table.getItems();
		LocalDate currentDate = LocalDate.now();
		int currentYear = currentDate.getYear();
		Predicate<Rendez_vous> yearFilter = item -> (item.getDate_debut().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate().getYear() == currentYear)
				|| (item.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						.getYear() == currentYear);
		// rdvs.setAll(data.filter(yearFilter));
		List<Rendez_vous> filtered = rdvs.stream().filter(yearFilter).toList();
		rdvs = FXCollections.observableList(filtered);
		rdv_table.setItems(rdvs);

	}

	private Alert createLoadingAlert() {
		Alert alert = new Alert(AlertType.NONE);
		alert.setTitle("Loading");
		alert.setHeaderText("Please wait...");
		// alert.initOwner(year_btn.getScene().getWindow());
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setGraphic(progressIndicator);
		return alert;
	}

	// year_btn.setOnAction(event -> {
	// ObservableList<Rendez_vous> rdvs = rdv_table.getItems();
	// LocalDate currentDate = LocalDate.now();
	// int currentYear = currentDate.getYear();
	// Predicate<Rendez_vous> yearFilter = item ->
	// item.getDate_debut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear()
	// == currentYear;
	// rdvs.setAll(data.filter(yearFilter));
	// });

	private void loadData() {
		// Create a task to load the data in the background
		Task<ArrayList<Rendez_vous>> loadTask = new Task<ArrayList<Rendez_vous>>() {
			@Override
			protected ArrayList<Rendez_vous> call() throws Exception {
				// Perform your data loading operation here

				return AdminController.ListRdv();
			}
		};

		// Set up loading indicator
		Alert loadingAlert = new Alert(AlertType.INFORMATION);
		loadingAlert.setTitle("Loading");
		loadingAlert.setHeaderText("Please wait...");
		loadingAlert.setContentText("Loading data from the server...");
		loadingAlert.initOwner(rdv_table.getScene().getWindow());
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
			listrdv = loadTask.getValue();

			// Update the UI with the loaded data
			Platform.runLater(() -> {
				ObservableList<Rendez_vous> list = FXCollections.observableArrayList(listrdv);
				rdv_table.setItems(list);
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
		errorAlert.initOwner(rdv_table.getScene().getWindow());
		errorAlert.showAndWait();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ChangeListener<Scene> chl = new ChangeListener<Scene>() {

			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				if (newValue != null) {
					loadData();
				}
			}

		};
		rdv_table.sceneProperty().addListener(chl);

		nom_client_col.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getClient_rdv().getNom()));

		prenom_client_col.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getClient_rdv().getNom()));
		num_client_col.setCellValueFactory(

				cellData -> new SimpleStringProperty(cellData.getValue().getClient_rdv().getNumero()));
		voiture_model_col.setCellValueFactory(

				cellData -> new SimpleStringProperty(cellData.getValue().getCar_rdv().getVin()));
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

		date_deut_col.setCellValueFactory(

				cellData -> new SimpleStringProperty(DATE_FORMAT.format(cellData.getValue().getDate_debut())));

		date_fin_col.setCellValueFactory(

				cellData -> new SimpleStringProperty(DATE_FORMAT.format(cellData.getValue().getDate_fin())));

		// date_deut_col.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
		// date_fin_col.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
		etat_col.setCellValueFactory(new PropertyValueFactory<>("etat"));

		action.setCellFactory(column -> {

			return new TableCell<Rendez_vous, Void>() {

				private final Button copybutton = new Button("");

				{

					copybutton.setStyle(
							"-fx-background-radius: 5em; -fx-min-width: 25px; -fx-min-height: 25px; -fx-max-width: 25px; -fx-max-height: 25px; -fx-background-color: transparent; -fx-alignment:CENTER;");

					Image image_copy = new Image(getClass().getResourceAsStream("menu.png"));
					ImageView img_copy = new ImageView(image_copy);
					img_copy.setFitHeight(25);
					img_copy.setFitWidth(25);
					copybutton.setGraphic(img_copy);

					copybutton.setOnAction(event -> {
						// ObservableList<Parts> selectedItems =
						// parts_table.getSelectionModel().getSelectedItems();

						Rendez_vous rdv = getTableView().getItems().get(getIndex());
						// rdv_global = getTableView().getItems().get(getIndex());

						System.out.println(rdv.getCar_rdv().getModele());

						try {

							FXMLLoader loader = new FXMLLoader(
									getClass().getResource("/application/Viewfxml/rdv_details.fxml"));

							Parent root = loader.load();
							Rdv_details rdv_details_con = loader.getController();
							System.out.println(rdv_details_con);

							System.out.println(rdv.getDescrption_in());
							rdv_details_con.getrdv(rdv);
							rdv_container.getChildren().removeAll();
							rdv_container.getChildren().setAll(root);

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
						HBox buttonsBox = new HBox(10, copybutton);
						getAlignment();
						buttonsBox.setAlignment(Pos.CENTER);
						setGraphic(buttonsBox);
					}
				}
			};
		});

		rdv_table.setItems(list);

		filteredRendezVousList = new FilteredList<>(list, b -> true);

		// Set the filter Predicate whenever the search text changes
		search_rdv.textProperty().addListener((observable, oldValue, newValue) -> {
			FilteredList<Rendez_vous> filteredList = new FilteredList<>(list, data -> true);
			filteredList.setPredicate(data -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (data.getClient_rdv().getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (data.getCar_model().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (String.valueOf(data.getCar_rdv().getVin()).contains(lowerCaseFilter)) {
					return true;
				} else if (String.valueOf(data.getDate_debut()).contains(lowerCaseFilter)) {
					return true;
				}

				rdv_table.refresh();
				return false;
			});
			SortedList<Rendez_vous> sortedList = new SortedList<>(filteredList);
			sortedList.comparatorProperty().bind(rdv_table.comparatorProperty());
			System.out.println(sortedList.size());
			rdv_table.setItems(sortedList);
		});

		// Wrap the filtered list in a sorted list
		SortedList<Rendez_vous> sortedList = new SortedList<>(filteredRendezVousList);

		// Bind the sorted list to the table
		rdv_table.setItems(sortedList);

	}
}