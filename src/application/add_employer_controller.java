package application;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

	ObservableList<Usermodel> list = FXCollections.observableArrayList(
			new Usermodel(1, "test", "test", "test", "test", "0011", "test", "test1", "admin"),
			new Usermodel(2, "teszdat", "test", "test", "test", "0022", "test", "test2", "admint"),
			new Usermodel(3, "test3", "test", "test", "test", "0033", "test", "test3", "addd"),
			new Usermodel(4, "test4", "test", "test", "test", "0044", "test", "test4", "slave")

	);

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
