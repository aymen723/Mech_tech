package application;

import java.net.URL;


import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class add_employer_controller implements Initializable {

	@FXML
	public TableColumn<Usermodel, String> id = new TableColumn<>("id");

	@FXML
	public TableColumn<Usermodel, String> username = new TableColumn<>("username");

	@FXML
	public TableColumn<Usermodel, String> nom = new TableColumn<>("nom");

	@FXML
	public TableColumn<Usermodel, String> prenom = new TableColumn<>("prenom");

	@FXML
	public TableColumn<Usermodel, String> nuermo = new TableColumn<>("nuermo");

	@FXML
	public TableColumn<Usermodel, String> address = new TableColumn<>("address");

	@FXML
	public TableColumn<Usermodel, String> email = new TableColumn<>("email");

	@FXML
	public TableColumn<Usermodel, String> role = new TableColumn<>("role");

	@FXML
	private TableView<Usermodel> table;

	@FXML
	private Button add_emp;

	@FXML
	private Button mod_emp;
	
	@FXML
	private Button return_back;
	
	
    @FXML
    private BorderPane emp_container;
    
    
    @FXML
    private Button ajouter_employer;

    @FXML
    private TextField emailfield;

    @FXML
    private TextField namefield;

    @FXML
    private TextField numerofield;

    @FXML
    private TextField prenomfield;

    @FXML
    private TextField rolefield;

    @FXML
    private TextField usernamefield;
    


	ObservableList<Usermodel> list = FXCollections.observableArrayList(
			new Usermodel(1, "test", "test", "test", "test", "0011", "test", "test1", "admin"),
			new Usermodel(2, "teszdat", "test", "test", "test", "0022", "test", "test2", "admint"),
			new Usermodel(3, "test3", "test", "test", "test", "0033", "test", "test3", "addd"),
			new Usermodel(4, "test4", "test", "test", "test", "0044", "test", "test4", "slave")

	);

	
	
	public void add_emp_conatiner() {
		System.out.println("test hna1");

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("add_employe_container.fxml"));
			emp_container.getChildren().removeAll();
			emp_container.getChildren().setAll(fxml);
			System.out.println("test hna 2");


		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("test hna 3");


	}
	
	
	public void mod_emp_conatiner() {
		
		System.out.println("test hna1");

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("mod_employe_container.fxml"));
			emp_container.getChildren().removeAll();
			emp_container.getChildren().setAll(fxml);
			System.out.println("test hna 2");


		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("test hna 3");
		
	
////		Group root = new Group();
//		Parent fxml;
//		try {
//			fxml = FXMLLoader.load(getClass().getResource("mod_employe_container.fxml"));
////			fxml.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
//
//			Scene scene = new Scene(fxml);
//			Stage stage = new Stage();
//			stage.setScene(scene);
//			stage.show();
//
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	
	
	public void return_back(javafx.event.ActionEvent event) {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("add_employe_dashbord.fxml"));
			emp_container.getChildren().removeAll();
			emp_container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		username.setCellValueFactory(new PropertyValueFactory<>("username"));
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
		nuermo.setCellValueFactory(new PropertyValueFactory<>("Numero"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		role.setCellValueFactory(new PropertyValueFactory<>("role"));

		table.setItems(list);

	}

}
