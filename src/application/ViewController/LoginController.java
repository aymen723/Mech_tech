package application.ViewController;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import application.Connectdatabase;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

	@FXML
	TextField username;
	@FXML
	TextField password;
	@FXML
	Button loginbtn;
	@FXML
	Text serverstats;

	public void connect(ActionEvent event) {

		String usern = username.getText();
		String role = null;
		Document filter = new Document("email", usern);
		filter.append("motdepass", password.getText());
		System.out.println("here outside");

		// Connectdatabase connecterdatabase = new Connectdatabase();
		MongoCollection<Document> collection = Connectdatabase.connectdb("users");
		MongoCursor<Document> cursor = collection.find(filter).iterator();
		while (cursor.hasNext()) {
			System.out.println("here in boucle");
			System.out.println(cursor.next().getString("role"));
			role = cursor.next().getString("role");

		}

		System.out.println("outside the loop" + role);

		if (role != null) {

			System.out.println("inside null");
			if (role == "admin") {

				System.out.println("inide admin");
				Parent fxml;
				try {
					fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Dashbord.fxml"));
					Stage stage = new Stage();
					Scene scene = new Scene(fxml);
					stage.setScene(scene);
					stage.setTitle("Mecha Tech");
					scene.setFill(Color.TRANSPARENT);

					stage.show();

				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if (role == "caissier") {

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (Connectdatabase.connectdb("users") != null) {
			serverstats.setText("Server is up");
			serverstats.setFill(Color.GREEN);
		} else {
			serverstats.setText("Server is Down");
			serverstats.setFill(Color.RED);

		}

	}

}
