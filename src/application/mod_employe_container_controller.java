package application;

import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;

import application.controller.AdminController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class mod_employe_container_controller implements Initializable {

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

	public void modifier_employer() {
		Document newemp = new Document("username", username_field_mod.getText());
		// newemp.append("nom", namefield.getText());
		// newemp.append("prenom", prenomfield.getText());
		// newemp.append("numero", numerofield.getText());
		// newemp.append("role", rolefield.getText());
		// newemp.append("email",emailfield.getText());
		newemp.append("password", email_field_mod.getText());
		AdminController.UpdateEmp(newemp);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("hna fl inti" + add_employer_controller.user.getPassword());
		System.out.println("conflict fix");
		username_field_mod.setText(add_employer_controller.user.getUsername());
		name_field_mod.setText(add_employer_controller.user.getNom());
		prenom_field_mod.setText(add_employer_controller.user.getPrenom());
		numero_field_mod.setText(add_employer_controller.user.getNumero());
		email_field_mod.setText(add_employer_controller.user.getEmail());
		role_field_mod.setText(add_employer_controller.user.getRole());
	}
}
