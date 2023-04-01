package application;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import application.ViewController.Parts;
import application.controller.AdminController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class parts_dashbord_controller implements Initializable {
	@FXML
	private Button mod_btn;
	@FXML
	Button add_btn;

	@FXML
	private TextField description;

	@FXML
	private TextField name;

	@FXML
	private TextField price;

	@FXML
	private TextField quntitie;

	@FXML
	private TextField reserch_field;

	@FXML
	private TableColumn<Parts, Integer> prix_col;

	@FXML
	private TableColumn<Parts, Integer> quntite_col;

	@FXML
	private TableColumn<Parts, String> nom_col;
	@FXML
	private TableColumn<Parts, String> desc_col;
	
    @FXML
    private TableColumn<Parts , String> id;
    

	@FXML
	private TableView<Parts> parts_table;
	

	ObservableList<Parts> list = FXCollections.observableArrayList(new Parts("1", "part1", 5, "good", 1000),
			new Parts("2", "part2", 6, "bad", 300));
	

	public void add_parts(javafx.event.ActionEvent actione) {
		System.out.println("test add");
	}

	public void mod_parts(javafx.event.ActionEvent actione) {
		System.out.println("test add");
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		System.out.println("hna list mazal");
		 list = AdminController.PartList();
		System.out.println("hna wra list");
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		nom_col.setCellValueFactory(new PropertyValueFactory<>("name"));
		prix_col.setCellValueFactory(new PropertyValueFactory<>("price"));
		quntite_col.setCellValueFactory(new PropertyValueFactory<>("quntitie"));
		desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
		parts_table.setItems(list);

	}

}
