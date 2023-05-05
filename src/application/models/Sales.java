package application.models;

import java.util.ArrayList;
import java.util.Date;

public class Sales {

    private String id;
    private Date date_de_vente;
    private ArrayList<Parts> partList;

    public Date getDate_de_vente() {
        return date_de_vente;
    }

    public void setDate_de_vente(Date date_de_vente) {
        this.date_de_vente = date_de_vente;
    }

    public ArrayList<Parts> getPartList() {
        return partList;
    }

    public void setPartList(ArrayList<Parts> partList) {
        this.partList = partList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int nuberofparts() {
        int count = 0;
        for (int i = 0; i < partList.size(); i++) {
            count = count + partList.get(i).getQuntitie();
        }

        return count;
    }

    public int total() {
        int count = 0;
        for (int i = 0; i < this.partList.size(); i++) {
            count = count + this.partList.get(i).getQuntitie() * this.partList.get(i).getPrice();
            System.out.println("this is partlist price " + this.partList.get(i).getPrice());
            System.out.println("this is partlist quantity " + this.partList.get(i).getQuntitie());
            System.out.println("total now is " + count);
        }
        return count;
    }

}