package application.models;

public class Clientmodel {

    String id;
    String nom;
    String prenom;
    String numero;
    String addresse;
    String email;

    public Clientmodel(String id, String nom, String prenom, String numero, String addresse, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.addresse = addresse;
        this.email = email;

    }

    public Clientmodel(String nom, String prenom, String numero) {
        this.id = null;
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.addresse = null;
        this.email = null;

    }

    public Clientmodel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}