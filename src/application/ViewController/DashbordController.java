package application.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.models.Usermodel;
import javafx.scene.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DashbordController implements Initializable {

	@FXML
	Button dashbord_btn;
	@FXML
	Button parts;
	@FXML
	Button Rendez_vous;
	@FXML
	Button Employés;
	@FXML
	Button Clients;
	@FXML
	BorderPane container;
	@FXML
	Button close_add;

	@FXML
	private Button btn_profile;

	@FXML
	private Button btn_logout;

	private Usermodel user = new Usermodel();

	@FXML
	private Text test;

	public void dashbord(javafx.event.ActionEvent actione) throws IOException {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/home_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

		parts.getStyleClass().remove("afterpress");
		Rendez_vous.getStyleClass().remove("afterpress");
		Employés.getStyleClass().remove("afterpress");
		Clients.getStyleClass().remove("afterpress");
		dashbord_btn.getStyleClass().add("afterpress");

	}

	public void parts(javafx.event.ActionEvent actione) throws IOException {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/parts_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

		Employés.getStyleClass().remove("afterpress");
		Clients.getStyleClass().remove("afterpress");
		dashbord_btn.getStyleClass().remove("afterpress");
		Rendez_vous.getStyleClass().remove("afterpress");

		parts.getStyleClass().add("afterpress");

	}

	public void Rendez_vous(javafx.event.ActionEvent actione) throws IOException {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Rendez_vous_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

		dashbord_btn.getStyleClass().remove("afterpress");
		parts.getStyleClass().remove("afterpress");
		Employés.getStyleClass().remove("afterpress");
		Clients.getStyleClass().remove("afterpress");

		Rendez_vous.getStyleClass().add("afterpress");

		// try {
		// Parent fxml =
		// FXMLLoader.load(getClass().getResource("/application/Viewfxml/Rendez_vous_dashbord.fxml"));
		// Stage stage = new Stage();
		// Scene scene = new Scene(fxml);
		// stage.setScene(scene);
		// stage.setTitle("Mecha Tech");
		// scene.setFill(Color.TRANSPARENT);

		// // primaryStage.initStyle(StageStyle.UNDECORATED);
		// // primaryStage.initStyle(StageStyle.TRANSPARENT);

		// // primaryStage.setResizable(false);
		// stage.show();

		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public void Employés(javafx.event.ActionEvent actione) throws IOException {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/add_employe_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

		dashbord_btn.getStyleClass().remove("afterpress");
		parts.getStyleClass().remove("afterpress");
		Rendez_vous.getStyleClass().remove("afterpress");
		Clients.getStyleClass().remove("afterpress");

		Employés.getStyleClass().add("afterpress");
	}

	public void Clients(javafx.event.ActionEvent actione) throws IOException {

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Client_dashbord.fxml"));
			container.getChildren().removeAll();
			container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

		dashbord_btn.getStyleClass().remove("afterpress");
		parts.getStyleClass().remove("afterpress");
		Rendez_vous.getStyleClass().remove("afterpress");
		Employés.getStyleClass().remove("afterpress");

		Clients.getStyleClass().add("afterpress");

	}

	@FXML
	void logout(ActionEvent event) {

		Parent fxml;
		try {
			fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Login.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(fxml);
			stage.setScene(scene);
			stage.setTitle("Mecha Tech");
			scene.setFill(Color.TRANSPARENT);

			Stage stage_login = (Stage) container.getScene().getWindow();

			stage_login.close();

			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void profile(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/application/Viewfxml/profile.fxml"));

			Parent root = loader.load();
			profile_controller profile_con = loader.getController();

			profile_con.setUser(user);
			container.getChildren().removeAll();
			container.getChildren().setAll(root);

		} catch (Exception e) {
			// TODO: handle exceptionttett
		}

		// try {
		// Parent fxml =
		// FXMLLoader.load(getClass().getResource("/application/Viewfxml/profile.fxml"));
		// Stage stage = new Stage();
		// Scene scene = new Scene(fxml);
		// stage.setScene(scene);
		// stage.setTitle("Mecha Tech");
		// scene.setFill(Color.TRANSPARENT);

		// // primaryStage.initStyle(StageStyle.UNDECORATED);
		// // primaryStage.initStyle(StageStyle.TRANSPARENT);

		// // primaryStage.setResizable(false);
		// stage.show();

		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public void setuser(Usermodel userr) {

		this.user = userr;

		btn_profile.setText(user.getNom() + " " + user.getPrenom());

	}

	public Usermodel getuser() {
		return user;
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

	// public void closing_add_parts(javafx.event.ActionEvent actione) {
	// ((Node) (actione.getSource())).getScene().getWindow().hide();
	//
	// System.out.println("quitter");
	//
	// }

}
