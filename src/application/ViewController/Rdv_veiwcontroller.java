package application.ViewController;

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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
// import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
// import javafx.scene.paint.Color;
// import javafx.stage.Stage;

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


	private ArrayList<Rendez_vous> listrdv= AdminController.ListRdv();
	// private FilteredList<Rendez_vous> filteredRendezVousList;
	// Arraylist<Parts> list =

	LocalDate date = LocalDate.now();

	LocalDate date_debut = date.minusDays(1);
	LocalDate date_fin = date.plusDays(2);
	ObservableList<Rendez_vous> list = FXCollections.observableArrayList(listrdv);

	public void rdv_ajouter() {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/ajouter_rdv.fxml"));
			rdv_container.getChildren().removeAll();
			rdv_container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}
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
    Predicate<Rendez_vous> monthFilter = item -> (item.getDate_debut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == currentMonth) || (item.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == currentMonth);
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

        return (!itemDatedebut.isBefore(startOfWeek) && !itemDatedebut.isAfter(endOfWeek)) || (!itemDatefin.isBefore(startOfWeek) && !itemDatefin.isAfter(endOfWeek));
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
    Predicate<Rendez_vous> yearFilter = item -> (item.getDate_debut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == currentYear) 
	|| (item.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == currentYear);
    // rdvs.setAll(data.filter(yearFilter));
	List<Rendez_vous> filtered = rdvs.stream().filter(yearFilter).toList();		
		rdvs = FXCollections.observableList(filtered);
		rdv_table.setItems(rdvs);


	  }
    
	// year_btn.setOnAction(event -> {
	// 	ObservableList<Rendez_vous> rdvs = rdv_table.getItems();
    // LocalDate currentDate = LocalDate.now();
    // int currentYear = currentDate.getYear();
    // Predicate<Rendez_vous> yearFilter = item -> item.getDate_debut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == currentYear;
    // rdvs.setAll(data.filter(yearFilter));
	// });




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		

        
		nom_client_col.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getClient_rdv().getNom()));

		prenom_client_col.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getClient_rdv().getNom()));
		num_client_col.setCellValueFactory(

				cellData -> new SimpleStringProperty(cellData.getValue().getClient_rdv().getNumero()));
		voiture_model_col.setCellValueFactory(new PropertyValueFactory<>("car_model"));
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

					Image image_copy = new Image(getClass().getResourceAsStream("copy.png"));
					ImageView img_copy = new ImageView(image_copy);
					img_copy.setFitHeight(25);
					img_copy.setFitWidth(25);
					copybutton.setGraphic(img_copy);

					copybutton.setOnAction(event -> {
						// ObservableList<Parts> selectedItems =
						// parts_table.getSelectionModel().getSelectedItems();

						Rendez_vous rdv = getTableView().getItems().get(getIndex());
						// rdv_global = getTableView().getItems().get(getIndex());

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

						// try {

						// FXMLLoader loader = new FXMLLoader(
						// getClass().getResource("/application/Viewfxml/rdv_details.fxml"));
						// Parent root = loader.load();

						// Rdv_details rdv_details_con = loader.getController();
						// System.out.println(rdv_details_con);

						// rdv_details_con.getrdv(rdv);
						// Stage stage = new Stage();
						// Scene scene = new Scene(root);
						// stage.setScene(scene);
						// stage.setTitle("Mecha Tech");

						// stage.show();

						// } catch (IOException e) {
						// e.printStackTrace();
						// }

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

	}}


