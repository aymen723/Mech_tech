package application.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.models.Usermodel;
import javafx.scene.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class caissier_dashbordcontroller implements Initializable {

    @FXML
    private Button dashbord_btn;
    @FXML
    private Button parts;
    @FXML
    private Button Rendez_vous;

    @FXML
    private Button Clients;
    @FXML
    private Button btn_car;

    @FXML
    private Button btn_Fournisseur;
    @FXML
    private BorderPane container;
    @FXML
    private Button close_add;

    @FXML
    private Button btn_profile;

    @FXML
    private Button btn_logout;

    @FXML
    private Button close_button;

    @FXML
    private Button btn_sales;

    @FXML
    private Pane tool_bar;

    private Usermodel user = new Usermodel();

    @FXML
    private Text test;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    void close_window(ActionEvent event) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    public void dashbord(javafx.event.ActionEvent actione) throws IOException {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/home_dashbord.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }

        parts.getStyleClass().remove("afterpress");
        Rendez_vous.getStyleClass().remove("afterpress");
        Clients.getStyleClass().remove("afterpress");
        btn_car.getStyleClass().remove("afterpress");
        btn_Fournisseur.getStyleClass().remove("afterpress");
        btn_sales.getStyleClass().remove("afterpress");

        dashbord_btn.getStyleClass().add("afterpress");

    }

    public void parts(javafx.event.ActionEvent actione) throws IOException {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/parts_dashbord.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);
            System.out
                    .println("container height " + container.getHeight() + " container width " + container.getWidth());

        } catch (Exception e) {
            // TODO: handle exception
        }

        Clients.getStyleClass().remove("afterpress");
        dashbord_btn.getStyleClass().remove("afterpress");
        Rendez_vous.getStyleClass().remove("afterpress");
        btn_car.getStyleClass().remove("afterpress");
        btn_Fournisseur.getStyleClass().remove("afterpress");
        btn_sales.getStyleClass().remove("afterpress");

        parts.getStyleClass().add("afterpress");

    }

    public void Rendez_vous(javafx.event.ActionEvent actione) throws IOException {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Rendez_vous_dashbord.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }

        dashbord_btn.getStyleClass().remove("afterpress");
        parts.getStyleClass().remove("afterpress");
        Clients.getStyleClass().remove("afterpress");
        btn_car.getStyleClass().remove("afterpress");
        btn_Fournisseur.getStyleClass().remove("afterpress");
        btn_sales.getStyleClass().remove("afterpress");

        Rendez_vous.getStyleClass().add("afterpress");

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Rendez_vous_dashbord.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxml);
            stage.setScene(scene);
            stage.setTitle("Mecha Tech");
            scene.setFill(Color.TRANSPARENT);

            // primaryStage.initStyle(StageStyle.UNDECORATED);
            // primaryStage.initStyle(StageStyle.TRANSPARENT);

            // primaryStage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void Clients(javafx.event.ActionEvent actione) throws IOException {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Client_dashbord.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }

        dashbord_btn.getStyleClass().remove("afterpress");
        parts.getStyleClass().remove("afterpress");
        Rendez_vous.getStyleClass().remove("afterpress");
        btn_car.getStyleClass().remove("afterpress");
        btn_Fournisseur.getStyleClass().remove("afterpress");
        btn_sales.getStyleClass().remove("afterpress");

        Clients.getStyleClass().add("afterpress");

    }

    @FXML
    void logout(ActionEvent event) {

        Parent fxml;
        try {
            fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Login.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxml);
            Rectangle shape = new Rectangle(800, 500);
            shape.setArcWidth(20);
            shape.setArcHeight(20);

            // Set the shape as the custom shape for the stage
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.getScene().setFill(Color.TRANSPARENT);
            stage.getScene().getRoot().setClip(shape);
            stage.setResizable(false);
            stage.show();

            Stage dashboard_stage = (Stage) container.getScene().getWindow();

            dashboard_stage.close();

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void car(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Car_dashboard.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }

        dashbord_btn.getStyleClass().remove("afterpress");
        parts.getStyleClass().remove("afterpress");
        Rendez_vous.getStyleClass().remove("afterpress");
        Clients.getStyleClass().remove("afterpress");
        btn_Fournisseur.getStyleClass().remove("afterpress");
        btn_sales.getStyleClass().remove("afterpress");

        btn_car.getStyleClass().add("afterpress");

        // try {
        // Parent fxml =
        // FXMLLoader.load(getClass().getResource("/application/Viewfxml/Car_dashboard.fxml"));
        // Stage stage = new Stage();
        // Scene scene = new Scene(fxml);
        // stage.setScene(scene);
        // // stage.setTitle("Mecha Tech");
        // // scene.setFill(Color.TRANSPARENT);

        // // primaryStage.initStyle(StageStyle.UNDECORATED);
        // // primaryStage.initStyle(StageStyle.TRANSPARENT);

        // // primaryStage.setResizable(false);
        // stage.show();

        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }

    @FXML
    void Fournisseur(ActionEvent event) {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Fournisseur_dashboard.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }

        dashbord_btn.getStyleClass().remove("afterpress");
        parts.getStyleClass().remove("afterpress");
        Rendez_vous.getStyleClass().remove("afterpress");
        Clients.getStyleClass().remove("afterpress");
        btn_car.getStyleClass().remove("afterpress");
        btn_sales.getStyleClass().remove("afterpress");

        btn_Fournisseur.getStyleClass().add("afterpress");
        // Parent fxml;
        // try {
        // fxml =
        // FXMLLoader.load(getClass().getResource("/application/Viewfxml/Fournisseur_dashboard.fxml"));
        // Stage stage = new Stage();
        // Scene scene = new Scene(fxml);
        // stage.setScene(scene);
        // stage.setTitle("Mecha Tech");
        // scene.setFill(Color.TRANSPARENT);

        // Stage stage_login = (Stage) container.getScene().getWindow();

        // stage_login.close();

        // stage.show();

        // } catch (IOException e) {
        // e.printStackTrace();
        // }

    }

    @FXML
    void profile(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/application/Viewfxml/profile.fxml"));

            Parent root = loader.load();
            profile_controller profile_con = loader.getController();

            profile_con.setUser(user);
            container.getChildren().removeAll();
            container.getChildren().setAll(root);

        } catch (Exception e) {
            // TODO: handle exceptionttett
        }

        // try {
        // Parent fxml =
        // FXMLLoader.load(getClass().getResource("/application/Viewfxml/profile.fxml"));
        // Stage stage = new Stage();
        // Scene scene = new Scene(fxml);
        // stage.setScene(scene);
        // stage.setTitle("Mecha Tech");
        // scene.setFill(Color.TRANSPARENT);

        // // primaryStage.initStyle(StageStyle.UNDECORATED);
        // // primaryStage.initStyle(StageStyle.TRANSPARENT);

        // // primaryStage.setResizable(false);
        // stage.show();

        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

    }

    public void setuser(Usermodel userr) {

        this.user = userr;

        btn_profile.setText(user.getNom() + " " + user.getPrenom());

    }

    public Usermodel getuser() {
        return user;
    }

    @FXML
    void sales(ActionEvent event) {

        // try {
        // Parent fxml =
        // FXMLLoader.load(getClass().getResource("/application/Viewfxml/Sales_dashbord.fxml"));
        // container.getChildren().removeAll();
        // container.getChildren().setAll(fxml);

        // } catch (Exception e) {
        // // TODO: handle exception
        // }

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/Sales_dashbord.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxml);
            stage.setScene(scene);
            stage.setTitle("Mecha Tech");
            scene.setFill(Color.TRANSPARENT);

            // primaryStage.initStyle(StageStyle.UNDECORATED);
            // primaryStage.initStyle(StageStyle.TRANSPARENT);

            // primaryStage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        dashbord_btn.getStyleClass().remove("afterpress");
        parts.getStyleClass().remove("afterpress");
        Rendez_vous.getStyleClass().remove("afterpress");
        Clients.getStyleClass().remove("afterpress");
        btn_car.getStyleClass().remove("afterpress");
        btn_Fournisseur.getStyleClass().remove("afterpress");

        btn_sales.getStyleClass().add("afterpress");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/application/Viewfxml/home_dashbord.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);

        } catch (Exception e) {
            // TODO: handle exception
        }

        tool_bar.setOnMousePressed(event ->

        {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        tool_bar.setOnMouseDragged(event -> {
            Stage stage = (Stage) close_button.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

    }

    // public void closing_add_parts(javafx.event.ActionEvent actione) {
    // ((Node) (actione.getSource())).getScene().getWindow().hide();
    //
    // System.out.println("quitter");
    //
    // }

}
