package application.models;

import java.util.ArrayList;

public class Fournisseur {
private String id;
private String name;
private String address;
private String phone;
private String email;
private int balance;
private ArrayList<Transaction> transactions;




public Fournisseur(String id, String name, String address, String phone, String email, int balance,
        ArrayList<Transaction> transactions) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.balance = balance;
    this.transactions = transactions;
}

public Fournisseur() {
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
public String getAddress() {
    return address;
}
public void setAddress(String address) {
    this.address = address;
}
public String getPhone() {
    return phone;
}
public void setPhone(String phone) {
    this.phone = phone;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public int getBalance() {
    return balance;
}
public void setBalance(int balance) {
    this.balance = balance;
}
public ArrayList<Transaction> getTransactions() {
    return transactions;
}
public void setTransactions(ArrayList<Transaction> transactions) {
    this.transactions = transactions;
}





   
}
