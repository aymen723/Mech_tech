package application.ViewController;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import application.Connectdatabase;
import application.models.Usermodel;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class LoginController {

	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button loginbtn;

	@FXML
	BorderPane login_container;



	@FXML
	private Button close_button;



	@FXML
	private Pane tool_bar;

	private Usermodel user = new Usermodel();

	public void connect(ActionEvent event) {

		String usern = username.getText();
		Document filter = new Document("email", usern);
		filter.append("motdepass", password.getText());
		System.out.println("here outside");

		final String adminrole = "admin";
		final String caissierrole = "caissier";

		// Connectdatabase connecterdatabase = new Connectdatabase();
		MongoCollection<Document> collection = Connectdatabase.connectdb("users");
		Document doc = new Document();
		doc.append("email", usern);
		doc.append("motdepass", password.getText());

		System.out.println(doc);

		Document curruser = collection.find(doc).first();

		if (curruser != null) {

			String role = curruser.getString("role");

			System.out.println(curruser.toJson());

			System.out.println(role);

			if (role.equals(adminrole)) {

				System.out.println("inide admin");

				user.setId(curruser.getObjectId("_id").toString());
				user.setNom(curruser.getString("nom"));
				user.setPrenom(curruser.getString("prenom"));
				user.setUsername(curruser.getString("nomutil"));
				user.setPassword(curruser.getString("motdepass"));
				user.setEmail(curruser.getString("email"));
				user.setNumero(curruser.getString("tel"));
				user.setRole(curruser.getString("role"));

				try {

					System.out.println("berfore loading");

					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/application/Viewfxml/Dashbord.fxml"));

					Parent root = loader.load();
					DashbordController dashbord_con = loader.getController();
					

					dashbord_con.setuser(user);
					System.out.println(user.getEmail());

					Stage stage_login = (Stage) login_container.getScene().getWindow();


					

					Stage stage = new Stage();
					Rectangle shape = new Rectangle(1200, 700);
					shape.setArcWidth(20);
					shape.setArcHeight(20);

					// Set the shape as the custom shape for the stage
					stage.initStyle(StageStyle.UNDECORATED);
					stage.initStyle(StageStyle.TRANSPARENT);
					Scene scene = new Scene(root);
					stage.setScene(scene);
					scene.setFill(Color.TRANSPARENT);
					stage.getScene().getRoot().setClip(shape);
					stage_login.close();
					stage.setResizable(false);

					stage.show();

				} catch (Exception e) {
					// TODO: handle exception
				}

			} else if (role.equals(caissierrole)) {
				user.setId(curruser.getObjectId("_id").toString());
				user.setNom(curruser.getString("nom"));
				user.setPrenom(curruser.getString("prenom"));
				user.setUsername(curruser.getString("nomutil"));
				user.setPassword(curruser.getString("motdepass"));
				user.setEmail(curruser.getString("email"));
				user.setNumero(curruser.getString("tel"));
				user.setRole(curruser.getString("role"));
				Parent fxml;
				try {
					fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/caissier_dashbord.fxml"));
					Stage stage = new Stage();
					Scene scene = new Scene(fxml);
					stage.setScene(scene);
					stage.setTitle("Mecha Tech");
					scene.setFill(Color.TRANSPARENT);

					Stage stage_login = (Stage) login_container.getScene().getWindow();

					stage_login.close();
					stage.setResizable(false);

					stage.show();

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	@FXML
	void close_window(ActionEvent event) {
		Stage stage = (Stage) close_button.getScene().getWindow();
		stage.close();

	}

	


	

	

}
