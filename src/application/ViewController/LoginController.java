package application.ViewController;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Collation;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button loginbtn;

	@FXML
	private TextField datebase_string;

	@FXML
	private Button btn_enre_database;

	@FXML
	private Button btn_mod_database;

	@FXML
	BorderPane login_container;

	String dbstring;

	@FXML
	private CheckBox passwordvue;

	@FXML
	private Tooltip tltip;

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
					System.out.println("berfong func");

					dashbord_con.setuser(user);
					System.out.println(user.getEmail());

					Stage stage_login = (Stage) login_container.getScene().getWindow();

					System.out.println("after loading");

					Stage stage = new Stage();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage_login.close();

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

					stage.show();

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

	@FXML
	void showpassword(ActionEvent event) {
		if (passwordvue.isSelected() == true) {

			Point2D p = password.localToScene(password.getBoundsInLocal().getMaxX(),
					password.getBoundsInLocal().getMaxY());
			tltip.setText(password.getText());
			tltip.show(password,
					p.getX() + login_container.getScene().getX()
							+ 120,
					p.getY() + login_container.getScene().getY() + 120);
		} else if (passwordvue.isSelected() == false) {
			tltip.setText("");
			tltip.hide();

		}

	}

	@FXML
	void enregistre_database(ActionEvent event) throws SocketException {

		Connectdatabase.Getstringdb(datebase_string.getText());

		Connectdatabase.testdb();

		datebase_string.setDisable(true);
		btn_enre_database.setDisable(true);
	}

	@FXML
	void mod_database(ActionEvent event) {
		datebase_string.setDisable(false);
		btn_enre_database.setDisable(false);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		datebase_string.setDisable(true);
		btn_enre_database.setDisable(true);

		password.setOnKeyTyped(e -> {
			if (passwordvue.isSelected() == true) {
				Point2D p = password.localToScene(password.getBoundsInLocal().getMaxX(),
						password.getBoundsInLocal().getMaxY());
				tltip.setText(password.getText());
				tltip.show(password,
						p.getX() + login_container.getScene().getX() + 120,
						p.getY() + login_container.getScene().getY() + 120);
			}
		});

	}

}
