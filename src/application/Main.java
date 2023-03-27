package application;

import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Main extends Application {

	public String url = "mongodb://localhost:27017";

	@Override
	public void start(Stage primaryStage) {

//		Group root = new Group();
		Parent fxml;
		try {
			fxml = FXMLLoader.load(getClass().getResource("dashbord.fxml"));
//			fxml.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());

			Scene scene = new Scene(fxml);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Mecha Tech");
			scene.setFill(Color.TRANSPARENT);

//			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.initStyle(StageStyle.TRANSPARENT);

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
