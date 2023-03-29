package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

	public void ajouter_employer() {
		System.out.println("add_emp");
	}

	
	public void modifier_employer() {
		System.out.println("mod_emp");

	}
}
