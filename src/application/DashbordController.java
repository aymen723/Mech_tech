package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DashbordController implements Initializable {


  

	@FXML
	Button dashbord_btn;
	@FXML
	Button parts;
	@FXML
	Button Rendez_vous;
	@FXML
	Button btn4;
	@FXML
	Button btn5;
	@FXML
	BorderPane container;
	@FXML
	Button add_btn;
	@FXML
	Button close_add;

    @FXML
    private Button add_btn1;

    @FXML
    private TextField description;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private TextField quntitie;

	public void dashbord(javafx.event.ActionEvent actione) throws IOException {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("home_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void parts(javafx.event.ActionEvent actione) throws IOException {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("parts_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void Rendez_vous(javafx.event.ActionEvent actione) throws IOException {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("Rendez_vous_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void btn4(javafx.event.ActionEvent actione) throws IOException {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("add_employe_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void btn5(javafx.event.ActionEvent actione) throws IOException {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("btn5_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("home_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void add_parts(javafx.event.ActionEvent actione) {

		Parent fxml;
		try {
			fxml = FXMLLoader.load(getClass().getResource("Add_parts.fxml"));
			Stage popup = new Stage();
			Scene scene = new Scene(fxml);
			popup.setScene(scene);
			popup.initStyle(StageStyle.UNDECORATED);
			popup.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void closing_add_parts(javafx.event.ActionEvent actione) {
		((Node) (actione.getSource())).getScene().getWindow().hide();

		System.out.println("quitter");

	}

}
