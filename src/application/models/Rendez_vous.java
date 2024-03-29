package application.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Rendez_vous {

    private String id;

    private String car_model;

    private String etat;

    private String service;

    private ArrayList<Parts> parts;

    private Date date_debut;

    private Date date_fin;

    private Clientmodel client_rdv;

    private String descrption_in;

    private String descrption_out;

    private int prix;

    private Usermodel technicien_rdv;

    public Rendez_vous(String id, String car_model, String etat, String service, ArrayList<Parts> parts,
            Date date_debut, Date date_fin, Clientmodel client_rdv, String descrption_in, String descrption_out,
            int prix, Usermodel technicien_rdv) {
        this.id = id;
        this.car_model = car_model;
        this.etat = etat;
        this.service = service;
        this.parts = parts;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.client_rdv = client_rdv;
        this.descrption_in = descrption_in;
        this.descrption_out = descrption_out;
        this.prix = prix;
        this.technicien_rdv = technicien_rdv;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public ArrayList<Parts> getParts() {
        return parts;
    }

    public void setParts(ArrayList<Parts> parts) {
        this.parts = parts;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Clientmodel getClient_rdv() {
        return client_rdv;
    }

    public void setClient_rdv(Clientmodel client_rdv) {
        this.client_rdv = client_rdv;
    }

    public String getDescrption_in() {
        return descrption_in;
    }

    public void setDescrption_in(String descrption_in) {
        this.descrption_in = descrption_in;
    }

    public String getDescrption_out() {
        return descrption_out;
    }

    public void setDescrption_out(String descrption_out) {
        this.descrption_out = descrption_out;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Usermodel gettechnicien_rdv() {

        return technicien_rdv;
    }

    public void settechnicien_rdv(Usermodel user) {
        this.technicien_rdv = user;

    }

    public Rendez_vous() {
    }


}
