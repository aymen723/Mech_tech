package application.models;


import java.util.ArrayList;
import java.util.Date;

public class Sales {

  private String id;  
  private  Date date_de_vente ;
  private  ArrayList<Parts> partList;

  

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


    
}
