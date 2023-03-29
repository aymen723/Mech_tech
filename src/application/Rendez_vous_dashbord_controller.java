package application;


import java.net.URL;
import java.util.ResourceBundle;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;



public class Rendez_vous_dashbord_controller implements Initializable{
	
	

    @FXML
    private Pane calender_container;

    @FXML
    private BorderPane rendez_container;
    
    
    
    CalendarView calendarView = new CalendarView(); // (1)

    Calendar birthdays = new Calendar("Birthdays"); // (2)
    Calendar holidays = new Calendar("Holidays");



    CalendarSource myCalendarSource = new CalendarSource("My Calendars"); // (4)



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}




}
