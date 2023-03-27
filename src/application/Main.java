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

public class Main extends Application {

	public String url = "mongodb://localhost:27017";

	@Override
	public void start(Stage primaryStage) {

//		Group root = new Group();
		Parent fxml;
		try {
			fxml = FXMLLoader.load(getClass().getResource("Dashbord.fxml"));
//			fxml.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());

			Scene scene = new Scene(fxml);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Mecha Tech");

//			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.initStyle(StageStyle.TRANSPARENT);

			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
//	 @Override
//	  public void start(Stage primaryStage) {
//
//	    TableView tableView = new TableView();
//
//	    TableColumn<Person, String> column1 = 
//	    new TableColumn<>("First Name");
//	    
//	    column1.setCellValueFactory(
//	        new PropertyValueFactory<>("firstName"));
//
//
//	    TableColumn<Person, String> column2 = 
//	    new TableColumn<>("Last Name");
//	    
//	    column2.setCellValueFactory(
//	        new PropertyValueFactory<>("lastName"));
//
//
//	    tableView.getColumns().add(column1);
//	    tableView.getColumns().add(column2);
//
//	    tableView.getItems().add(
//	      new Person("John", "Doe"));
//	    tableView.getItems().add(
//	      new Person("adzadazdaz", "Deer"));
//
//	    VBox vbox = new VBox(tableView);
//
//	    Scene scene = new Scene(vbox);
//
//	    primaryStage.setScene(scene);
//
//	    primaryStage.show();
//	  }
	public static void main(String[] args) {
		launch(args);
	}
}
