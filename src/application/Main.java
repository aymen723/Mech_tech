package application;

import java.io.IOException;






import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.paint.Color;

public class Main extends Application {

	public String url = "mongodb://localhost:27017";

	@Override
	public void start(Stage primaryStage) {

//		Group root = new Group();
		Parent fxml;
		try {
			fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Dashbord.fxml"));
//			fxml.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
           
			Scene scene = new Scene(fxml);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Mecha Tech");
			scene.setFill(Color.TRANSPARENT);

//			primaryStage.initStyle(StageStyle.UNDECORATED);
//			primaryStage.initStyle(StageStyle.TRANSPARENT);

			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		

		launch(args);
		
	}
}
