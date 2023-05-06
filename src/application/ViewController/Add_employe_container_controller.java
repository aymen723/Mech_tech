package application.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;

import application.controller.AdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;

public class Add_employe_container_controller implements Initializable {

	@FXML
	private TextField emailfield;

	@FXML
	private TextField namefield;

	@FXML
	private TextField numerofield;

	@FXML
	private TextField prenomfield;

	@FXML
	private PasswordField passworField;

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

	@FXML
	private CheckBox passwordvue;

	@FXML
	private ChoiceBox<String> role;

	@FXML
	private Tooltip password;

	public void return_back() {
		System.out.println("azda");
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/add_employe_dashbord.fxml"));
			add_container.getChildren().removeAll();
			add_container.getChildren().setAll(fxml);
			System.out.println("test hna 2");

		} catch (Exception e) {
		}
	}

	public void ajouter_employer() {

		Document newemp = new Document("nomutil", usernamefield.getText());
		newemp.append("nom", namefield.getText());
		newemp.append("prenom", prenomfield.getText());
		newemp.append("tel", numerofield.getText());
		newemp.append("role", role.getValue());
		newemp.append("email", emailfield.getText());
		newemp.append("motdepass", passworField.getText());
		AdminController.AddEmp(newemp);

		System.out.println("test hna1");

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/add_employe_dashbord.fxml"));
			add_container.getChildren().removeAll();
			add_container.getChildren().setAll(fxml);
			System.out.println("test hna 2");

		} catch (Exception e) {

		}
		System.out.println("test hna 3");

	}

	@FXML
	void showpassword(ActionEvent event) {
		if (passwordvue.isSelected() == true) {

			Point2D p = passworField.localToScene(passworField.getBoundsInLocal().getMaxX(),
					passworField.getBoundsInLocal().getMaxY());
			password.setText(passworField.getText());
			password.show(passworField,
					p.getX() + add_container.getScene().getX(),
					p.getY() + add_container.getScene().getY());
		} else if (passwordvue.isSelected() == false) {
			password.setText("");
			password.hide();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		role.getItems().add("Admin");
		role.getItems().add("Caissier");
		role.getItems().add("technicien");

		passworField.setOnKeyTyped(e -> {
			if (passwordvue.isSelected() == true) {
				Point2D p = passworField.localToScene(passworField.getBoundsInLocal().getMaxX(),
						passworField.getBoundsInLocal().getMaxY());
				password.setText(passworField.getText());
				password.show(passworField,
						p.getX() + add_container.getScene().getX(),
						p.getY() + add_container.getScene().getY());
			}
		});

	}

}
