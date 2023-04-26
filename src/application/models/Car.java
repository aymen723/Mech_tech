package application.models;

public class Car {
    
    private String marque;
    private String modele;
    private String color;
    private int matricule;
    private String vin;



    
    public Car() {
    }
    public Car(String marque, String modele, String color, int matricule, String vin) {
        this.marque = marque;
        this.modele = modele;
        this.color = color;
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
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
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


    


}
