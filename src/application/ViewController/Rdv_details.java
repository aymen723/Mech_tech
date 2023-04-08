package application.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.models.Parts;
import application.models.Rendez_vous;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Rdv_details {

    @FXML
    private TextField car_model;

    @FXML
    private TextField nom_client;

    @FXML
    private TextField numero_client;

    @FXML
    private TextField prenom_client;

    @FXML
    private TextField prix;

    @FXML
    private Button parts_rdv;

    @FXML
    private Pane rdv_container;

    ArrayList<Parts> rdv_parts_list;

    private Consumer<Rendez_vous> rdv_callback;

    private Rendez_vous rdv_local;

    @FXML
    private ListView listview_part;

    @FXML
    void add_parts_rdv(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Viewfxml/Rdv_parts.fxml"));

            Parent root = loader.load();
            Rdv_parts rdv_part_con = loader.getController();
            rdv_part_con.add_parts(rdv_local.getParts());
            System.out.println(rdv_part_con);
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Mecha Tech");
            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void getrdv(Rendez_vous rdv) {

        rdv_local = rdv;

        nom_client.setText(rdv.getClient_rdv().getNom());

        if (rdv.getParts() != null) {
            for (int i = 0; i <= rdv_local.getParts().size(); i++) {
                listview_part.getItems().add(rdv_local.getParts().get(i).getName());
            }

            HBox hbox = new HBox(listview_part);
        } else {
            System.out.println("is null");
        }

    }

    public void setlistpart(ArrayList<Parts> list_part) {
        rdv_local.setParts(list_part);
        if (rdv_local.getParts() != null) {
            for (int i = 0; i <= rdv_local.getParts().size(); i++) {
                listview_part.getItems().add(rdv_local.getParts().get(i).getName());
            }

            HBox hbox = new HBox(listview_part);
        } else {
            System.out.println("is null");
        }
    }

    // @Override
    // public void initialize(URL arg0, ResourceBundle arg1) {
    // rdv_callback.accept(rdv_local);

    // System.out.println("hna fl init " + rdv_local.getClient_rdv().getNom());
    // // TODO Auto-generated method stub
    // // throw new UnsupportedOperationException("Unimplemented method
    // 'initialize'");

    // // for (int i = 0; i < rdv_local.getParts().size(); i++) {
    // // listview_part.getItems().add(rdv_local.getParts().get(i).getName() + " " +
    // // rdv_local.getParts().get(i)
    // // .getQuntitie());
    // // }
    // // listView.getItems().add("Item 1");
    // // listView.getItems().add("Item 2");
    // // listView.getItems().add("Item 3");

    // // HBox hbox = new HBox(listview_part);
    // }

}
