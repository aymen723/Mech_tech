package application.models;

import java.time.LocalDate;

public class Rendez_vous {

    private String id;

    private LocalDate date_debut;

    private LocalDate date_fin;

    private String decription;

    private Clientmodel client_rdv;

    public Rendez_vous(String id, LocalDate date_debut, LocalDate date_fin, String decription, Clientmodel client_rdv) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.decription = decription;
        this.client_rdv = client_rdv;
    }

    public Rendez_vous() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public void setClient_rdv(Clientmodel client_rdv) {
        this.client_rdv = client_rdv;
    }

    public String getId() {
        return id;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public String getDecription() {
        return decription;
    }

    public Clientmodel getClient_rdv() {
        return client_rdv;
    }

}
