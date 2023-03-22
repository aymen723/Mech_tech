module mecha {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.sql;
	requires mongo.java.driver;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
}
