package application.ViewController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import application.controller.AdminController;
import application.models.Clientmodel;
import application.models.Parts;
import application.models.Rendez_vous;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Rdv_dashboard_controller {

    @FXML
    private Pane rd_container;

    @FXML
    private Text txt_car;

    @FXML
    private Text txt_dd;

    @FXML
    private Text txt_nom;

    @FXML
    private Text txt_prenom;

    @FXML
    private Text txt_serv;

    @FXML
    private Button btn_der;

    private Rendez_vous rdv;

    public void getrdv(Rendez_vous rdv) {
        this.rdv = rdv;

        if (rdv.getEtat().equals("en cours") || rdv.getEtat().equals("terminé")) {
            btn_der.setDisable(true);

        }

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

        txt_car.setText(rdv.getCar_model());
        txt_dd.setText(DATE_FORMAT.format(rdv.getDate_debut()));
        txt_nom.setText(rdv.getClient_rdv().getNom());
        txt_prenom.setText(rdv.getClient_rdv().getPrenom());
        txt_serv.setText(rdv.getService());
    }

    public Parent getRoot() {
        return txt_nom.getParent();
    }

    @FXML
    void demare(ActionEvent event) {

        Clientmodel newclient = rdv.getClient_rdv();
        Document clientrdv;

        Document newrdv = new Document("date_debut", rdv.getDate_debut());
        newrdv.append("date_fin", rdv.getDate_fin());
        newrdv.append("descrption_in", rdv.getDescrption_in());
        newrdv.append("descrption_out", rdv.getDescrption_out());
        newrdv.append("service", rdv.getService());
        newrdv.append("etat", "en cours");

        newrdv.append("car model", rdv.getCar_model());
        newrdv.append("prix", rdv.getPrix());

        if (rdv.getClient_rdv().getEmail() != null) {

            clientrdv = new Document("_id", new ObjectId(rdv.getId()));
            clientrdv.append("nom", rdv.getClient_rdv().getNom());
            clientrdv.append("prenom", rdv.getClient_rdv().getPrenom());
            clientrdv.append("tel", rdv.getClient_rdv().getNumero());
            clientrdv.append("email", rdv.getClient_rdv().getEmail());
            clientrdv.append("addresse", rdv.getClient_rdv().getAddresse());
        } else {
            clientrdv = new Document("nom", rdv.getClient_rdv().getNom());
            clientrdv.append("prenom", rdv.getClient_rdv().getPrenom());
            clientrdv.append("numero", rdv.getClient_rdv().getNumero());
        }

        clientrdv.append("client", clientrdv);

        int i = 0;

        List<Document> myDocuments = new ArrayList<Document>();
        for (i = 0; i < rdv.getParts().size(); i++) {
            Parts part = rdv.getParts().get(i);
            Document addpart = new Document("_id", new ObjectId(part.getId()));
            addpart.append("name", rdv.getParts().get(i).getName());
            addpart.append("price", rdv.getParts().get(i).getPrice());
            addpart.append("quantity", rdv.getParts().get(i).getQuntitie());
            // addpart.append("description", part.getDescription());
            myDocuments.add(addpart);

            System.out.println(myDocuments.get(i));
        }
        newrdv.append("parts", myDocuments);
        AdminController.UpdateRdv(newrdv, rdv);

    }
}
