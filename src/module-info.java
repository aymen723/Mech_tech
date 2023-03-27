module mecha {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.sql;
	requires mongo.java.driver;
	requires java.desktop;
//    requires de.jensd.fx.fontawesomefx.fontawesome;
//	opens application.models;

	
//	opens application to javafx.graphics, javafx.fxml;
	opens application to javafx.graphics, javafx.fxml, javafx.base;

}
