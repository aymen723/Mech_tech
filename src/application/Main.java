package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Main extends Application {

	public String url = "mongodb://localhost:27017";



	@Override
	public void start(Stage primaryStage) {

		Parent fxml;
		try {
			fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Login.fxml"));

			Scene scene = new Scene(fxml);
			
			primaryStage.setTitle("Mecha Tech");
			Rectangle shape = new Rectangle(800, 500);
			shape.setArcWidth(20);
			shape.setArcHeight(20);

			// Set the shape as the custom shape for the stage
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			scene.setFill(Color.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.getScene().setFill(Color.TRANSPARENT);
			primaryStage.getScene().getRoot().setClip(shape);
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
