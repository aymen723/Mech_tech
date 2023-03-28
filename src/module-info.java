module mecha {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.sql;
	requires java.desktop;
	requires mongo.java.driver;


//  requires de.jensd.fx.fontawesomefx.fontawesome;
//	opens application.models;

	
//	opens application to javafx.graphics, javafx.fxml;
	opens application to javafx.graphics, javafx.fxml, javafx.base;

}
