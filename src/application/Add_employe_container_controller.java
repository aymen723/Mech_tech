package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;

import application.controller.AdminController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Add_employe_container_controller  implements Initializable {

	@FXML
	private  TextField emailfield;

	@FXML
	private  TextField namefield;

	@FXML
	private  TextField numerofield;

	@FXML
	private  TextField prenomfield;

	@FXML
	private  TextField rolefield;

	@FXML
	private  TextField usernamefield;

	@FXML
	private  TextField email_field_mod;

	@FXML
	private  TextField name_field_mod;

	@FXML
	private  TextField numero_field_mod;

	@FXML
	private  TextField prenom_field_mod;

	@FXML
	private  TextField role_field_mod;

	@FXML
	private  TextField username_field_mod;

	@FXML
	private  Button add_emp;

	@FXML
	private  Button return_back_add;

	@FXML
	private  BorderPane add_container;
	

	public void return_back() {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("add_employe_dashbord.fxml"));
			add_container.getChildren().removeAll();
			add_container.getChildren().setAll(fxml);

			
		} catch (Exception e) {
			
		}
		
		

	}

	public void ajouter_ep() {

//		 Document newemp = new Document("email",emailfield.getText());
//		 newemp.append("nom", namefield.getText());
//		 newemp.append("prenom", prenomfield.getText());
//		 newemp.append("numero", numerofield.getText());
//		 newemp.append("role", rolefield.getText());
//	     newemp.append("username", usernamefield.getText());
//		
//	     AdminController.AddEmp(newemp);
	}

	public void modifier_employer() {
		System.out.println("mod_emp");

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("hna fl inti"+add_employer_controller.user.getPassword());
		username_field_mod.setText(add_employer_controller.user.getUsername());
		name_field_mod.setText(add_employer_controller.user.getNom());
		prenom_field_mod.setText(add_employer_controller.user.getPrenom());
		numero_field_mod.setText(add_employer_controller.user.getNumero());
		email_field_mod.setText(add_employer_controller.user.getEmail());
		role_field_mod.setText(add_employer_controller.user.getRole());
	}
	
	
}