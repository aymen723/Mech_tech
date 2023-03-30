package application;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Rendez_vous_dashbord_controller {

	@FXML
	private Pane calender_container;

	@FXML
	private BorderPane rendez_container;

	public void test() throws IOException {

	try {
		CalendarView calendarView = new CalendarView(); // (1)

		Calendar birthdays = new Calendar("Birthdays"); // (2)
		Calendar holidays = new Calendar("Holidays");

		Stage stg = new Stage();
		Scene scene = new Scene(calendarView);
		stg.setScene(scene);
		stg.setTitle("Mecha Tech");
		// scene.setFill(Color.TRANSPARENT);

		// primaryStage.initStyle(StageStyle.UNDECORATED);
		// primaryStage.initStyle(StageStyle.TRANSPARENT);

		stg.setResizable(false);
		stg.show();
		
	} catch (Exception e) {
		System.out.println(e);
		// TODO: handle exception
	}

	}

}
