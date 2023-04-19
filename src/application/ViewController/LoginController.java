package application.ViewController;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Collation;

import application.Connectdatabase;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	private TextField password;
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

	public void connect(ActionEvent event) {

		String usern = username.getText();
		Document filter = new Document("email", usern);
		filter.append("motdepass", password.getText());
		System.out.println("here outside");

		String adminrole = "admin";
		String caissierrole = "caissier";

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

			if (role.equals("admin")) {

				System.out.println("inide admin");

				Parent fxml;
				try {
					fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Dashbord.fxml"));
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

			} else if (role.equals("caissier")) {

			}
		}

	}

	public void testquery(ActionEvent e) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("users");
		FindIterable<Document> iterDoc = collection.find();
		Iterator<Document> it = iterDoc.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
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

	}

}
