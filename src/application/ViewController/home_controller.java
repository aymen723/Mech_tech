package application.ViewController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.mongodb.client.model.Collation;

import application.controller.AdminController;
import application.models.Parts;
import application.models.Rendez_vous;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class home_controller implements Initializable {

    @FXML
    private HBox rdv_hbox;

    @FXML
    private HBox part_hbox;

    @FXML
    private Button new_client;

    @FXML
    private Button new_rdv;

    @FXML
    private Button new_sale;

    @FXML
    private BorderPane container;
    @FXML
    private ScrollPane rdv_scroll, part_scroll;

    ObservableList<Rendez_vous> list_rdv = FXCollections.observableArrayList();
    ObservableList<Parts> list_part = FXCollections.observableArrayList();

    ArrayList<Node> list_node = new ArrayList<Node>();

    @FXML
    void new_client(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/ajouter_client.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void new_rdv(ActionEvent event) {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/ajouter_rdv.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void new_sale(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/ajouter_sales.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'initialize'");

        list_rdv = FXCollections.observableArrayList(AdminController.ListRdv());
        list_part = FXCollections.observableArrayList(AdminController.PartList());
        LocalDate currentDate = LocalDate.now();
        System.out.println("test date" + currentDate);

        int currentMonth = currentDate.getMonthValue();
        Predicate<Rendez_vous> rdvfilter = item -> (item.getDate_debut().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate().equals(currentDate))
                || (item.getEtat().equals("en cours"));
        List<Rendez_vous> filteredRdv = list_rdv.stream().filter(rdvfilter).toList();

        Predicate<Parts> partfilter = item -> (item.getQuntitie() <= 5);
        List<Parts> filteredpart = list_part.stream().filter(partfilter).toList();

        for (Rendez_vous rdv : filteredRdv) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/application/Viewfxml/rdv_container_dashboard.fxml"));
                Pane root = loader.load();
                if (rdv.getEtat().equals("en cours")) {
                    root.setStyle("-fx-background-color: #F0EB8D;-fx-background-radius: 15;-fx-border-radius: 15");
                } else if (rdv.getEtat().equals("terminé")) {
                    root.setStyle("-fx-background-color: #98d8aa;-fx-background-radius: 15;-fx-border-radius: 15");
                }

                Rdv_dashboard_controller rdv_container = loader.getController();
                rdv_container.getrdv(rdv);
                rdv_hbox.getChildren().add(root);
            } catch (IOException e) {
                // Handle the exception
                e.printStackTrace();
            }
        }

        for (Parts part : filteredpart) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Viewfxml/part_container.fxml"));
                Pane root = loader.load();
                part_container part_con = loader.getController();
                part_con.getpart(part);
                part_hbox.getChildren().add(root);
            } catch (IOException e) {
                // Handle the exception
                e.printStackTrace();
            }
        }

    }
}