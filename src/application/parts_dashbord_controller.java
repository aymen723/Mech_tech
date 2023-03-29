package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class parts_dashbord_controller {
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
	private TableColumn<?, ?> prix_col;

	@FXML
	private TableColumn<?, ?> quntite_col;

	@FXML
	private TableColumn<?, ?> nom_col;
	
	@FXML
	private TableColumn<?, ?> desc_col;

	@FXML
	private TableView<?> parts_table;

	public void add_parts(javafx.event.ActionEvent actione) {
		System.out.println("test add");
	}

	public void mod_parts(javafx.event.ActionEvent actione) {
		System.out.println("test add");
	}
}
