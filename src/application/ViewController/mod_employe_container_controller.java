package application.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;

import application.controller.AdminController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

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
    
    @FXML
    private BorderPane mod_container;

    public void modifier_employer() {
    	
		Document newemp = new Document("username", username_field_mod.getText());
		newemp.append("_id",add_employer_controller.user.id );
		newemp.append("nom", name_field_mod.getText());
		newemp.append("nomutil", username_field_mod.getText());
        newemp.append("prenom", prenom_field_mod.getText());
		newemp.append("tel", numero_field_mod.getText());
		newemp.append("role", role_field_mod.getText());
		newemp.append("email",email_field_mod.getText());
		// newemp.append("password",email_field_mod.getText());
		 AdminController.UpdateEmp(newemp);
		 
		 
			System.out.println("test hna1");

			try {
				Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/add_employe_dashbord.fxml"));
				mod_container.getChildren().removeAll();
				mod_container.getChildren().setAll(fxml);
				System.out.println("test hna 2");

			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("test hna 3");
	}




    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        System.out.println("hna fl inti" + add_employer_controller.user.getPassword());
        username_field_mod.setText(add_employer_controller.user.getUsername());
        name_field_mod.setText(add_employer_controller.user.getNom());
        prenom_field_mod.setText(add_employer_controller.user.getPrenom());
        numero_field_mod.setText(add_employer_controller.user.getNumero());
        email_field_mod.setText(add_employer_controller.user.getEmail());
        role_field_mod.setText(add_employer_controller.user.getRole());
    }
}