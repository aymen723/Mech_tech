package application.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Rendez_vous {

    private String id;

    private String car_model;

    private String etat;

    private String service;

    private ArrayList<Parts> parts;

    private LocalDate date_debut;

    private LocalDate date_fin;

    private String decription;

    private Clientmodel client_rdv;

    public Rendez_vous() {
    }

    public Rendez_vous(String id, String car_model, String etat, String service, ArrayList<Parts> parts,
            LocalDate date_debut, LocalDate date_fin, String decription, Clientmodel client_rdv) {
        this.id = id;
        this.car_model = car_model;
        this.etat = etat;
        this.service = service;
        this.parts = parts;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.decription = decription;
        this.client_rdv = client_rdv;
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

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public Clientmodel getClient_rdv() {
        return client_rdv;
    }

    public void setClient_rdv(Clientmodel client_rdv) {
        this.client_rdv = client_rdv;
    }

}
