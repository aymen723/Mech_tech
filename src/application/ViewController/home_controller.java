package application.ViewController;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class home_controller implements Initializable {

    @FXML
    private HBox rdv_scroll;

    @FXML
    private HBox part_scroll;
    ObservableList<Rendez_vous> list_rdv = FXCollections.observableArrayList();
    ObservableList<Parts> list_part = FXCollections.observableArrayList();

    ArrayList<Node> list_node = new ArrayList<Node>();

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
                || (item.getDate_debut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                        .isBefore(currentDate))
                || (item.getEtat().equals("en cours"));
        List<Rendez_vous> filtered = list_rdv.stream().filter(rdvfilter).toList();

        Predicate<Parts> partfilter = item -> (item.getQuntitie() <= 5);
        List<Parts> filteredpart = list_part.stream().filter(partfilter).toList();

        try {
            for (int i = 0; i < filtered.size(); i++) {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/application/Viewfxml/rdv_container_dashboard.fxml"));
                Pane root = loader.load();
                if (filtered.get(i).getEtat().equals("en cours")) {
                    // root.setStyle("container-encour ");
                    root.getStyleClass().clear();

                    root.setStyle("container-encour");
                    root.setStyle("-fx-background-color: #F0EB8D ;-fx-background-radius:15 ;-fx-border-radius:15 ");
                } else if (filtered.get(i).getEtat().equals("terminé")) {
                    root.getStyleClass().clear();

                    root.setStyle("container-terminer");
                    root.setStyle("-fx-background-color: #98d8aa ;-fx-background-radius:15 ;-fx-border-radius:15 ");
                }

                Rdv_dashboard_controller rdv_container = loader.getController();

                rdv_container.getrdv(filtered.get(i));

                System.out.println(filtered.get(i).getDate_debut());

                rdv_scroll.getChildren().add(root);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            for (int i = 0; i < filteredpart.size(); i++) {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/application/Viewfxml/part_container.fxml"));
                Pane root = loader.load();

                part_container part_con = loader.getController();

                part_con.getpart(filteredpart.get(i));

                part_scroll.getChildren().add(root);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
