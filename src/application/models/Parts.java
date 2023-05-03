package application.models;

import java.util.Date;

public class Parts {

    private String id;
    private String name;
    private int quntitie;
    private String description;
    private int price;
    private int buyingprice;
    private Fournisseur Fournisseur;

    private Date buyingdate;

    public Parts(String id, String name, int quntitie, String description, int price, int buyingprice,
            Fournisseur Fournisseur, Date buyingdate) {
        this.id = id;
        this.name = name;
        this.quntitie = quntitie;
        this.description = description;
        this.price = price;
        this.buyingprice = buyingprice;
        this.Fournisseur = Fournisseur;
        this.buyingdate = buyingdate;
    }

    public Parts() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuntitie() {
        return quntitie;
    }

    public void setQuntitie(int quntitie) {
        this.quntitie = quntitie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBuyingprice() {
        return buyingprice;
    }

    public void setBuyingprice(int buyingprice) {
        this.buyingprice = buyingprice;
    }

    public Fournisseur getFournisseur() {
        return Fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.Fournisseur = fournisseur;
    }

    public Date getBuyingdate() {
        return buyingdate;
    }

    public void setBuyingdate(Date buyingdate) {
        this.buyingdate = buyingdate;
    }

}
