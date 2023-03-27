package application;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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

	public void connect(ActionEvent e) {

		String usern = username.getText();
		Document filter = new Document("username", usern);
		filter.append("password", password.getText());

		MongoCollection collection = Connectdatabase.connectdb("users");
		MongoCursor<Document> cursor = collection.find(filter).iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next().toJson());
		}

	}

	public void testquery(ActionEvent e) {

		MongoCollection collection = Connectdatabase.connectdb("users");
		FindIterable<Document> iterDoc = collection.find();
		Iterator it = iterDoc.iterator();
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
