package application.ViewController;

import application.models.Parts;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.text.Text;

public class part_container {

    @FXML
    private Text txt_nom;

    @FXML
    private Text txt_qun;

    private Parts part;

    public void getpart(Parts part) {

        this.part = part;
        txt_nom.setText(part.getName());
        txt_qun.setText(Integer.toString(part.getQuntitie()));

    }

    public Parent getRoot() {
        return txt_nom.getParent();
    }


}
