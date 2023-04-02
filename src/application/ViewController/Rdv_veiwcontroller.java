package application.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Rdv_veiwcontroller implements Initializable {

	@FXML
	private Pane table_container;

	@FXML
	private BorderPane rdv_container;

	@FXML
	private ChoiceBox<String> table_list;

	@FXML
	private Button select_btn;

	@FXML
	private AnchorPane tables;

	@FXML
	private Button ajouter_btn;

	@FXML
	private Button modifier_btn;

	public void rdv_ajouter() {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/ajouter_rdv.fxml"));
			rdv_container.getChildren().removeAll();
			rdv_container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void rdv_modifier() {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/modifier_rdv.fxml"));
			rdv_container.getChildren().removeAll();
			rdv_container.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void selected_table() {

		String value = (String) table_list.getValue();
		System.out.println("/application/Viewfxml/" + value);

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/" + value));
			tables.getChildren().removeAll();
			tables.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		table_list.getItems().add("rdv_days.fxml");
		table_list.getItems().add("rdv_month.fxml");
		table_list.getItems().add("rdv_week.fxml");

		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/rdv_days.fxml"));
			tables.getChildren().removeAll();
			tables.getChildren().setAll(fxml);

		} catch (Exception e) {
			// TODO: handle exception
		}

		table_list.setOnAction((event) -> {
			int selectedIndex = table_list.getSelectionModel().getSelectedIndex();
			Object selectedItem = table_list.getSelectionModel().getSelectedItem();
			try {
				Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/" + selectedItem));
				tables.getChildren().removeAll();
				tables.getChildren().setAll(fxml);

			} catch (Exception e) {
				// TODO: handle exception
			}

		});

	}

}
