package application.models;

import java.util.Date;

public class Transaction {
    private String id;
    private Date date_de_transaction;
    private int somme_payee;
    private int somme_de_transaction;

    public Transaction() {

    }

    public Transaction(String id, Date date_de_transaction, int somme_payee, int somme_de_transaction) {
        this.id = id;
        this.date_de_transaction = date_de_transaction;
        this.somme_payee = somme_payee;
        this.somme_de_transaction = somme_de_transaction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate_de_transaction() {
        return date_de_transaction;
    }

    public void setDate_de_transaction(Date date_de_transaction) {
        this.date_de_transaction = date_de_transaction;
    }

    public int getSomme_payee() {
        return somme_payee;
    }

    public void setSomme_payee(int somme_payee) {
        this.somme_payee = somme_payee;
    }

    public int getSomme_de_transaction() {
        return somme_de_transaction;
    }

    public void setSomme_de_transaction(int somme_de_transaction) {
        this.somme_de_transaction = somme_de_transaction;
    }

}
