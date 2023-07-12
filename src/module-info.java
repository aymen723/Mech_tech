module mecha {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires mongo.java.driver;
	requires javafx.base;
	requires javafx.swing;
	requires itextpdf;

	opens application to javafx.graphics, javafx.fxml, javafx.base;
	opens application.ViewController to javafx.graphics, javafx.fxml, javafx.base;
	opens application.controller to javafx.graphics, javafx.fxml, javafx.base;
	opens application.models to javafx.graphics, javafx.fxml, javafx.base;

}