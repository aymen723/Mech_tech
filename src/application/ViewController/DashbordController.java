package application.ViewController;

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
import javafx.scene.paint.Color;
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
	Button close_add;

	public void dashbord(javafx.event.ActionEvent actione) throws IOException {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/home_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void parts(javafx.event.ActionEvent actione) throws IOException {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/parts_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

//		Group root = new Group();
//		Parent fxml;
//		try {
//			fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/parts_dashbord.fxml"));
////			fxml.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
//			Stage stage = new Stage();
//			Scene scene = new Scene(fxml);
//			stage.setScene(scene);
//			stage.setTitle("Mecha Tech");
//			scene.setFill(Color.TRANSPARENT);
//
////			primaryStage.initStyle(StageStyle.UNDECORATED);
////			primaryStage.initStyle(StageStyle.TRANSPARENT);
//
////			primaryStage.setResizable(false);
//			stage.show();
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	public void Rendez_vous(javafx.event.ActionEvent actione) throws IOException {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Rendez_vous_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void btn4(javafx.event.ActionEvent actione) throws IOException {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/add_employe_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void btn5(javafx.event.ActionEvent actione) throws IOException {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/btn5_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/home_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

//	public void closing_add_parts(javafx.event.ActionEvent actione) {
//		((Node) (actione.getSource())).getScene().getWindow().hide();
//
//		System.out.println("quitter");
//
//	}

}
