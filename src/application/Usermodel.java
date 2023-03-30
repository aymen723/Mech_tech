package application;

import javafx.beans.property.SimpleStringProperty;

public class Usermodel {

//	public SimpleStringProperty id = new SimpleStringProperty("");
//	public SimpleStringProperty username = new SimpleStringProperty("");
//	public SimpleStringProperty nom = new SimpleStringProperty("");
//
//	public Usermodel(String id, String username, String nom) {
//		this.id.set(id);
//		this.username.set(username);
//		this.nom.set(nom);
//
//	}
//
//	public final String getusername() {
//		return username.get();
//	}
//
//	public final void setusername(String value) {
//		username.set(value);
//	}
//
//	public final void setnom(String value) {
//		nom.set(value);
//	}
//
//	public String getnom() {
//		return nom.get();
//	}
//
//	public String getid() {
//		return id.get();
//	}
//
//	public final void setuid(String value) {
//		id.set(value);
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Usermodel(String id, String username, String password, String nom, String prenom, String numero, String address,
			String email, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.numero = numero;
		this.address = address;
		this.email = email;
		this.role = role;
	}

	public Usermodel() {
		super();
}

	String id;

	String username;
	String password;
	String nom;
	String prenom;
	String numero;
	String address;
	String email;
	String role;

}
