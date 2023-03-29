package application;

import org.bson.Document;

import application.controller.AdminController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Add_employe_container_controller {

	@FXML
	private static TextField emailfield;

	@FXML
	private static TextField namefield;

	@FXML
	private static TextField numerofield;

	@FXML
	private static TextField prenomfield;

	@FXML
	private static TextField rolefield;

	@FXML
	private static TextField usernamefield;

	@FXML
	private static TextField email_field_mod;

	@FXML
	private static TextField name_field_mod;

	@FXML
	private static TextField numero_field_mod;

	@FXML
	private static TextField prenom_field_mod;

	@FXML
	private static TextField role_field_mod;

	@FXML
	private static TextField username_field_mod;

	@FXML
	private static Button ajouter_employe;

	@FXML
	private static Button return_back_add;

	@FXML
	private static BorderPane add_container;

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

	public static void ajouter_employer() {
		
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
