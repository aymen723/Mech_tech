package application;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Add_employe_container_controller {

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

	@FXML
	private TextField email_field_mod;

	@FXML
	private TextField name_field_mod;

	@FXML
	private TextField numero_field_mod;

	@FXML
	private TextField prenom_field_mod;

	@FXML
	private TextField role_field_mod;

	@FXML
	private TextField username_field_mod;

	@FXML
	private Button ajouter_employe;

	@FXML
	private Button return_back_add;

	@FXML
	private BorderPane add_container;

	public void return_back() {
		System.out.println("azda");
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("add_employe_dashbord.fxml"));
			add_container.getChildren().removeAll();
			add_container.getChildren().setAll(fxml);
			System.out.println("test hna 2");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void ajouter_employer() {
		// Document newemp = new Document("email",emailfield.getText());
		// newemp.append("nom", namefield.getText());
		// newemp.append("prenom", prenomfield.getText());
		// newemp.append("numero", numerofield.getText());
		// newemp.append("role", rolefield.getText());
		// newemp.append("username", usernamefield.getText());
		
		//  AdminController.AddEmp(newemp);		
		
	}

	public void modifier_employer() {
		System.out.println("mod_emp");

	}
}
