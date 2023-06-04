package application.ViewController;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import application.Connectdatabase;
import application.models.Usermodel;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

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

	@FXML
	private Tooltip passwordvu;

	private Usermodel user = new Usermodel();

	public void connect(ActionEvent event) {

		String usern = username.getText();
		Document filter = new Document("email", usern);
		filter.append("motdepass", password.getText());
		System.out.println("here outside");

		final String adminrole = "Admin";
		final String caissierrole = "Caissier";

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
					// fxml =
					// FXMLLoader.load(getClass().getResource("/application/Viewfxml/caissier_dashbord.fxml"));
					// Stage stage = new Stage();
					// Scene scene = new Scene(fxml);
					// stage.setScene(scene);
					// stage.setTitle("Mecha Tech");
					// scene.setFill(Color.TRANSPARENT);

					// Stage stage_login = (Stage) login_container.getScene().getWindow();

					// stage_login.close();
					// stage.setResizable(false);

					// stage.show();

					System.out.println("berfore loading");

					FXMLLoader loader = new FXMLLoader(
							getClass().getResource("/application/Viewfxml/caissier_dashbord.fxml"));

					Parent root = loader.load();
					caissier_dashbordcontroller dashbord_con = loader.getController();

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

	@FXML
	void connectkey(KeyEvent event) {
		// if (event.getCode() == ) {
		// System.out.println("Key Pressed: " + ke.getCode());
		// // ke.consume(); // <-- stops passing the event to next node
		// }

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		password.setOnKeyTyped(e -> {

			passwordvu.setText(password.getText());
			// passwordvu.show();

		});
	}

}
