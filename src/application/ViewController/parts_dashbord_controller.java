package application.ViewController;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;

import application.controller.AdminController;
import application.models.Parts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.NumberStringConverter;

public class parts_dashbord_controller implements Initializable {
	@FXML
	private Button mod_btn;

	@FXML
	private Button add_btn;

	@FXML
	private Button deleter_btn;

	@FXML
	private Button selec_btn;

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
	private TableColumn<Parts, String> id;

	@FXML
	private TableView<Parts> parts_table;

	public static Parts part;

	ObservableList<Parts> list = FXCollections.observableArrayList(new Parts("1", "part1", 5, "good", 1000),
			new Parts("2", "part2", 6, "bad", 300));

	public void add_parts(javafx.event.ActionEvent actione) {
		Document newpart = new Document("name", name.getText());
		newpart.append("price", Integer.parseInt(price.getText()));
		newpart.append("quantity", Integer.parseInt(quntitie.getText()));
		newpart.append("description", description.getText());
		AdminController.addpart(newpart);
		System.out.println("hna list mazal");
		list = AdminController.PartList();
		System.out.println("hna wra list");
		parts_table.setItems(list);

	}

	public void selection_parts(javafx.event.ActionEvent actione) {

		System.out.println("test add");

		TableViewSelectionModel<Parts> selectionModel = parts_table.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		// ObservableList<Usermodel> selectedItems = selectionModel.getSelectedItems();
		// System.out.println("hna fl mod " +
		// selectionModel.getSelectedItems().get(0).id);
		Parts part_mod = selectionModel.getSelectedItems().get(0);
		part = selectionModel.getSelectedItems().get(0);
		name.setText(part_mod.getName());
		description.setText(part_mod.getDescription());
		price.setText(Integer.toString(part_mod.getPrice()));
		quntitie.setText(Integer.toString(part_mod.getQuntitie()));

		// price.setText(part_mod.getPrice());

	}

	public void mod_parts(javafx.event.ActionEvent actione) {

		Document updatepart = new Document("name", name.getText());
		updatepart.append("price", Integer.parseInt(price.getText()));
		updatepart.append("quantity", Integer.parseInt(quntitie.getText()));
		updatepart.append("description", description.getText());

		AdminController.updatepart(updatepart);

		System.out.println("hna list mazal");
		list = AdminController.PartList();
		System.out.println("hna wra list");
		parts_table.setItems(list);

	}

	public void dlt_part() {
		TableViewSelectionModel<Parts> selectionModel = parts_table.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		// ObservableList<Usermodel> selectedItems = selectionModel.getSelectedItems();
		// System.out.println("hna fl mod " +
		// selectionModel.getSelectedItems().get(0).id);
		Parts part_mod = selectionModel.getSelectedItems().get(0);
		AdminController.deletpart();

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
