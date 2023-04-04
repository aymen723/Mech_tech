package application.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class modifier_rdv {

    @FXML
    private Pane mod_container;

    public void retour() {
        System.out.println("azda");
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Rendez_vous_dashbord.fxml"));
            mod_container.getChildren().removeAll();
            mod_container.getChildren().setAll(fxml);
            System.out.println("test hna 2");

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
