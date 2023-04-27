package application.models;

public class Car {
    private String id;
    private String marque;
    private String modele;
    private String couleur;
    private int matricule;
    private String vin;



    
    public Car() {
    }
    public Car(String id, String marque, String modele, String couleur, int matricule, String vin) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
        this.matricule = matricule;
        this.vin = vin;
    }

    
    public String getMarque() {
        return marque;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }
    public String getModele() {
        return modele;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }
    public String getCouleur() {
        return couleur;
    }
    public void setCouleur(String color) {
        this.couleur = color;
    }
    public int getMatricule() {
        return matricule;
    }
    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }
    public String getVin() {
        return vin;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    


}
