package application.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import application.Connectdatabase;
import application.ViewController.Client_dashbord;
import application.models.Car;
// import application.ViewController.add_employer_controller;<
import application.models.Clientmodel;
import application.models.Fournisseur;
import application.models.Parts;
import application.models.Rendez_vous;
import application.models.Transaction;
import application.models.Usermodel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminController {

	public static void AddEmp(Document Doc) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("users");
		collection.insertOne(Doc);
		Connectdatabase.closeconndb();

	}

	public static void UpdateEmp(Document Doc, Usermodel user) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("users");
		ObjectId objid = new ObjectId(user.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		Doc.append("_id", objid);
		Document updateop = new Document("$set", Doc);
		collection.updateOne(found, updateop);
		Connectdatabase.closeconndb();

	}

	public static ObservableList<Usermodel> EmpLiist() {
		ObservableList<Usermodel> List = FXCollections.observableArrayList();
		MongoCollection<Document> collection = Connectdatabase.connectdb("users");

		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				Usermodel user = new Usermodel();

				user.setId(doc.getObjectId("_id").toString());
				user.setNom(doc.getString("nom"));
				user.setPrenom(doc.getString("prenom"));
				user.setUsername(doc.getString("nomutil"));
				user.setPassword(doc.getString("motdepass"));
				user.setEmail(doc.getString("email"));
				user.setNumero(doc.getString("tel"));
				user.setRole(doc.getString("role"));

				List.add(user);

			}
		} finally {
			cursor.close();
			Connectdatabase.closeconndb();
		}

		return List;

	}

	// public static void addpart(Document Doc) {

	// MongoCollection<Document> collection = Connectdatabase.connectdb("parts");
	// collection.insertOne(Doc);
	// Connectdatabase.closeconndb();

	// }

	// public static void updatepart(Document Doc, Parts part) {
	// MongoCollection<Document> collection = Connectdatabase.connectdb("parts");
	// ObjectId objid = new ObjectId(part.getId());
	// Document found = (Document) collection.find(new Document("_id",
	// objid)).first();
	// System.out.println(found.get("name"));
	// Doc.append("_id", objid);
	// Document updateop = new Document("$set", Doc);
	// collection.updateOne(found, updateop);
	// Connectdatabase.closeconndb();

	// }

	public static void deletpart(Parts part) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("parts");
		ObjectId objid = new ObjectId(part.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		collection.deleteOne(found);
		Connectdatabase.closeconndb();

	}

	public static ObservableList<Parts> PartList() {
		ObservableList<Parts> List = FXCollections.observableArrayList();
		MongoCollection<Document> collection = Connectdatabase.connectdb("parts");
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				Parts part = new Parts();

				part.setId(doc.getObjectId("_id").toString());

				part.setName(doc.getString("name"));
				part.setDescription(doc.getString("description"));
				part.setQuntitie(doc.getInteger("quantity"));
				part.setPrice(doc.getInteger("price"));

				List.add(part);

			}
		} catch (Exception e) {
			System.out.println("Error retrieving documents: " + e.getMessage());
		} finally {
			cursor.close();
			Connectdatabase.closeconndb();

		}

		return List;

	}

	public static void addpart(Document Doc) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("parts");
		collection.insertOne(Doc);
		Connectdatabase.closeconndb();

	}

	public static void updatepart(Document Doc, Parts part) {
		MongoCollection<Document> collection = Connectdatabase.connectdb("parts");
		ObjectId objid = new ObjectId(part.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		System.out.println(found.get("name"));
		Doc.append("_id", objid);
		Document updateop = new Document("$set", Doc);
		collection.updateOne(found, updateop);
		Connectdatabase.closeconndb();

	}

	public static void deletemp(Usermodel user) {
		MongoCollection<Document> collection = Connectdatabase.connectdb("users");
		ObjectId objid = new ObjectId(user.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		collection.deleteOne(found);
		Connectdatabase.closeconndb();

	}

	public static void AddClient(Document Doc) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("clients");
		collection.insertOne(Doc);
		Connectdatabase.closeconndb();

	}

	public static void UpdateClient(Document Doc) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("clients");
		ObjectId objid = new ObjectId(Client_dashbord.client.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		Doc.append("_id", objid);
		Document updated = new Document("$set", Doc);
		collection.updateOne(found, updated);
		Connectdatabase.closeconndb();

	}

	public static void deletClient(Clientmodel client) {
		MongoCollection<Document> collection = Connectdatabase.connectdb("clients");
		// ObjectId objid = new ObjectId(add_employer_controller.user.getId());
		ObjectId objid = new ObjectId(client.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		collection.deleteOne(found);
		Connectdatabase.closeconndb();

	}

	public static ObservableList<Clientmodel> ListClient() {
		ObservableList<Clientmodel> List = FXCollections.observableArrayList();
		MongoCollection<Document> collection = Connectdatabase.connectdb("clients");

		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				Clientmodel client = new Clientmodel();

				client.setId(doc.getObjectId("_id").toString());
				client.setNom(doc.getString("nom"));
				client.setPrenom(doc.getString("prenom"));
				client.setEmail(doc.getString("email"));
				client.setNumero(doc.getString("tel"));
				client.setAddresse(doc.getString("adresse"));

				List.add(client);

			}
		} finally {
			cursor.close();
			Connectdatabase.closeconndb();

		}

		return List;

	}

	public static Document findclientbyid(String id) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("clients");

		Document doc = new Document();
		// Document document = collection.find(eq("_id", new ObjectId(id))).first();
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));

		doc = collection.find(query).first();
		Connectdatabase.closeconndb();

		return doc;
	}

	public static void addrdv(Document Doc) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("Rendez_vous");
		collection.insertOne(Doc);
		Connectdatabase.closeconndb();

	}

	// public static void UpdateRdv(Document Doc , Rendez_vous rdv) {

	// MongoCollection<Document> collection =
	// Connectdatabase.connectdb("Rendez_vous");
	// ObjectId objid = new ObjectId(rdv.getId());
	// Document found = (Document) collection.find(new Document("_id",
	// objid)).first();
	// Doc.append("_id", objid);
	// Document updated = new Document("$set", Doc);
	// collection.updateOne(found, updated);
	// Connectdatabase.closeconndb();

	// Document doc = new Document();
	// // Document document = collection.find(eq("_id", new ObjectId(id))).first();
	// Connectdatabase.closeconndb();

	// }

	public static ArrayList<Rendez_vous> ListRdv() {
		ArrayList<Rendez_vous> List = new ArrayList<>();
		MongoCollection<Document> collection = Connectdatabase.connectdb("Rendez_vous");

		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				Rendez_vous rdv = new Rendez_vous();

				rdv.setId(doc.getObjectId("_id").toString());
				rdv.setCar_model(doc.getString("car model"));
				// SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

				rdv.setDate_debut(doc.getDate("date_debut"));
				rdv.setDate_fin(doc.getDate("date_fin"));
				rdv.setPrix(doc.getInteger("prix"));
				rdv.setDescrption_in(doc.getString("descrption_in"));
				rdv.setDescrption_out(doc.getString("descrption_out"));
				rdv.setEtat(doc.getString("etat"));
				rdv.setService(doc.getString("service"));

				Document clientdoc = doc.get("client", Document.class);
				Clientmodel client = new Clientmodel();

				client.setId(clientdoc.getObjectId("_id").toString());
				client.setNom(clientdoc.getString("nom"));
				client.setPrenom(clientdoc.getString("prenom"));
				client.setEmail(clientdoc.getString("email"));
				client.setNumero(clientdoc.getString("tel"));
				client.setAddresse(clientdoc.getString("adresse"));

				Document techniciendoc = doc.get("technicien", Document.class);
				Usermodel technicien = new Usermodel();

				technicien.setId(techniciendoc.getObjectId("_id").toString());
				technicien.setNom(techniciendoc.getString("nom"));
				technicien.setPrenom(techniciendoc.getString("prenom"));
				technicien.setEmail(techniciendoc.getString("email"));
				technicien.setNumero(techniciendoc.getString("tel"));
				technicien.setRole(techniciendoc.getString("role"));
				technicien.setUsername(techniciendoc.getString("nomutil"));

				Document cardoc = doc.get("Car", Document.class);
				Car car = new Car();

				car.setId(cardoc.getObjectId("_id").toString());
				car.setMarque(cardoc.getString("marque"));
				car.setModele(cardoc.getString("modele"));
				car.setCouleur(cardoc.getString("couleur"));
				car.setMatricule(cardoc.getString("matricule"));
				car.setVin(cardoc.getString("vin"));

				ArrayList<Document> parlistdoc = (ArrayList<Document>) doc.get("parts");
				ArrayList<Parts> partlist = new ArrayList<Parts>();
				for (Document pardoc : parlistdoc) {
					Parts part = new Parts();

					part.setId(doc.getObjectId("_id").toString());

					part.setName(pardoc.getString("name"));
					part.setDescription(pardoc.getString("description"));
					part.setQuntitie(pardoc.getInteger("quantity"));
					part.setPrice(pardoc.getInteger("price"));

					partlist.add(part);

				}
				rdv.setParts(partlist);
				rdv.setClient_rdv(client);
				rdv.setTechnicien_rdv(technicien);
				rdv.setCar_rdv(car);
				List.add(rdv);

			}
		} finally {
			cursor.close();
			Connectdatabase.closeconndb();

		}

		return List;

	}

	public static void UpdateRdv(Document Doc, Rendez_vous rdv) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("Rendez_vous");
		ObjectId objid = new ObjectId(rdv.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		Doc.append("_id", objid);
		Document updated = new Document("$set", Doc);
		collection.updateOne(found, updated);
		Connectdatabase.closeconndb();

	}

	public static void deletrdv(Rendez_vous rdv) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("Rendez_vous");
		ObjectId objid = new ObjectId(rdv.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		collection.deleteOne(found);

		Connectdatabase.closeconndb();

	}

	public static void AddCar(Document Doc) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("cars");
		collection.insertOne(Doc);
		Connectdatabase.closeconndb();

	}

	public static void UpdateCar(Document Doc, Car car) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("cars");
		ObjectId objid = new ObjectId(car.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		Doc.append("_id", objid);
		Document updateop = new Document("$set", Doc);
		collection.updateOne(found, updateop);
		Connectdatabase.closeconndb();

	}

	public static ObservableList<Car> CarLiist() {
		ObservableList<Car> List = FXCollections.observableArrayList();
		MongoCollection<Document> collection = Connectdatabase.connectdb("cars");

		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				Car car = new Car();

				car.setId(doc.getObjectId("_id").toString());
				car.setMarque(doc.getString("marque"));
				car.setModele(doc.getString("modele"));
				car.setCouleur(doc.getString("couleur"));
				car.setMatricule(doc.getString("matricule"));
				car.setVin(doc.getString("vin"));

				List.add(car);

			}
		} finally {
			cursor.close();
			Connectdatabase.closeconndb();
		}

		return List;

	}

	public static void deletpCar(Car car) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("cars");
		ObjectId objid = new ObjectId(car.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		collection.deleteOne(found);
		Connectdatabase.closeconndb();

	}

	public static ArrayList<Rendez_vous> RdvListCar(String vin) {
		MongoCollection<Document> collection = Connectdatabase.connectdb("Rendez_vous");
		ArrayList<Rendez_vous> List = new ArrayList<>();
		MongoCursor<Document> cursor = collection.find(Filters.in("Car.vin", vin)).iterator();
		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				Rendez_vous rdv = new Rendez_vous();

				rdv.setId(doc.getObjectId("_id").toString());
				rdv.setCar_model(doc.getString("car model"));
				// SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

				rdv.setDate_debut(doc.getDate("date_debut"));
				rdv.setDate_fin(doc.getDate("date_fin"));
				rdv.setPrix(doc.getInteger("prix"));
				rdv.setDescrption_in(doc.getString("descrption_in"));
				rdv.setDescrption_out(doc.getString("descrption_out"));
				rdv.setEtat(doc.getString("etat"));
				rdv.setService(doc.getString("service"));

				Document clientdoc = doc.get("client", Document.class);
				Clientmodel client = new Clientmodel();

				client.setId(clientdoc.getObjectId("_id").toString());
				client.setNom(clientdoc.getString("nom"));
				client.setPrenom(clientdoc.getString("prenom"));
				client.setEmail(clientdoc.getString("email"));
				client.setNumero(clientdoc.getString("tel"));
				client.setAddresse(clientdoc.getString("adresse"));

				Document techniciendoc = doc.get("technicien", Document.class);
				Usermodel technicien = new Usermodel();

				technicien.setId(techniciendoc.getObjectId("_id").toString());
				technicien.setNom(techniciendoc.getString("nom"));
				technicien.setPrenom(techniciendoc.getString("prenom"));
				technicien.setEmail(techniciendoc.getString("email"));
				technicien.setNumero(techniciendoc.getString("tel"));
				technicien.setRole(techniciendoc.getString("role"));
				technicien.setUsername(techniciendoc.getString("nomutil"));

				Document cardoc = doc.get("Car", Document.class);
				Car car = new Car();

				car.setId(cardoc.getObjectId("_id").toString());
				car.setMarque(cardoc.getString("marque"));
				car.setModele(cardoc.getString("modele"));
				car.setCouleur(cardoc.getString("couleur"));
				car.setMatricule(cardoc.getString("matricule"));
				car.setVin(cardoc.getString("vin"));

				ArrayList<Document> parlistdoc = (ArrayList<Document>) doc.get("parts");
				ArrayList<Parts> partlist = new ArrayList<Parts>();
				for (Document pardoc : parlistdoc) {
					Parts part = new Parts();

					part.setId(doc.getObjectId("_id").toString());

					part.setName(pardoc.getString("name"));
					part.setDescription(pardoc.getString("description"));
					part.setQuntitie(pardoc.getInteger("quantity"));
					part.setPrice(pardoc.getInteger("price"));

					partlist.add(part);

				}
				rdv.setParts(partlist);
				rdv.setClient_rdv(client);
				rdv.setTechnicien_rdv(technicien);
				rdv.setCar_rdv(car);
				List.add(rdv);

			}
		} finally {
			cursor.close();
			Connectdatabase.closeconndb();

		}
		System.out.println("this is the size of list rdv of cars " + List.size());

		return List;

	}

	public static void AddFournisseur(Document Doc) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("fournisseurs");
		collection.insertOne(Doc);
		Connectdatabase.closeconndb();

	}

	public static void UpdateFournisseur(Document Doc, Fournisseur fournisseur) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("fournisseurs");
		ObjectId objid = new ObjectId(fournisseur.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		Doc.append("_id", objid);
		Document updateop = new Document("$set", Doc);
		collection.updateOne(found, updateop);
		Connectdatabase.closeconndb();

	}

	public static ArrayList<Fournisseur> ListFournisseur() {
		ArrayList<Fournisseur> List = new ArrayList<Fournisseur>();
		MongoCollection<Document> collection = Connectdatabase.connectdb("fournisseurs");

		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				Fournisseur fournisseur = new Fournisseur();

				fournisseur.setId(doc.getObjectId("_id").toString());
				fournisseur.setName(doc.getString("nom"));
				// SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

				fournisseur.setAddress(doc.getString("adresse"));
				fournisseur.setPhone(doc.getString("numero"));
				fournisseur.setEmail(doc.getString("email"));
				fournisseur.setBalance(doc.getInteger("balance"));

				ArrayList<Document> transactions_doc_list = (ArrayList<Document>) doc.get("Transactions");
				ArrayList<Transaction> tansactions = new ArrayList<Transaction>();
				for (Document tansaction_doc : transactions_doc_list) {
					Transaction transaction = new Transaction();

					transaction.setId(tansaction_doc.getObjectId("_id").toString());
					transaction.setDate_de_transaction(tansaction_doc.getDate("date_de_transaction"));
					transaction.setSomme_payee(tansaction_doc.getInteger("somme_payee"));
					transaction.setSomme_de_transaction(tansaction_doc.getInteger("somme_de_transaction"));

					tansactions.add(transaction);

				}
				fournisseur.setTransactions(tansactions);
				List.add(fournisseur);

			}
		} finally {
			cursor.close();
			Connectdatabase.closeconndb();

		}

		return List;

	}

	public static void deleteFournisseur(Fournisseur fournisseur) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("cars");
		ObjectId objid = new ObjectId(fournisseur.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		collection.deleteOne(found);
		Connectdatabase.closeconndb();

	}

	public static void addTransaction(Document transaction, Fournisseur fournisseur) {
		// MongoCollection<Document> collection =
		// Connectdatabase.connectdb("fournisseurs");
		// System.out.println(fournisseur.getId());
		// Document doc = new Document("_id", new ObjectId(fournisseur.getId()));
		// doc.append("balance", fournisseur.getBalance() +
		// transaction.getInteger("somme_payee") - transaction
		// .getInteger("somme_de_transaction"));
		// Bson update = Updates.push("Transactions", transaction);
		// UpdateResult UR = collection.updateOne(doc, update);
		// System.out.println(UR);
		// Connectdatabase.closeconndb();

		MongoCollection<Document> collection = Connectdatabase.connectdb("fournisseurs");
		Bson filter = Filters.eq("_id", new ObjectId(fournisseur.getId()));
		// Document doc = new Document();
		// doc.append("balance", fournisseur.getBalance() +
		// transaction.getInteger("somme_payee") - transaction
		// .getInteger("somme_de_transaction"));
		Bson update = Updates.addToSet("Transactions", transaction);
		Bson updatebalance = Updates.set("balance",
				fournisseur.getBalance() + transaction.getInteger("somme_payee") - transaction
						.getInteger("somme_de_transaction"));
		UpdateResult URbalance = collection.updateOne(filter, updatebalance);
		System.out.println(URbalance);
		UpdateResult UR = collection.updateOne(filter, update);
		System.out.println(UR);
		Connectdatabase.closeconndb();
	}

	public static Fournisseur findFournisseurbyid(String id) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("fournisseurs");

		Document doc = new Document();
		// Document document = collection.find(eq("_id", new ObjectId(id))).first();
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));

		doc = collection.find(query).first();

		Fournisseur fournisseur = new Fournisseur();

		fournisseur.setId(doc.getObjectId("_id").toString());
		fournisseur.setName(doc.getString("nom"));
		// SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

		fournisseur.setAddress(doc.getString("adresse"));
		fournisseur.setPhone(doc.getString("numero"));
		fournisseur.setEmail(doc.getString("email"));
		fournisseur.setBalance(doc.getInteger("balance"));

		List<Document> transactions_doc_list = doc.getList("Transactions", Document.class);
		ArrayList<Transaction> tansactions = new ArrayList<Transaction>();
		for (Document tansaction_doc : transactions_doc_list) {
			Transaction transaction = new Transaction();
			transaction.setId(tansaction_doc.getObjectId("_id").toString());
			transaction.setDate_de_transaction(tansaction_doc.getDate("date_de_transaction"));
			transaction.setSomme_payee(tansaction_doc.getInteger("somme_payee"));
			transaction.setSomme_de_transaction(tansaction_doc.getInteger("somme_de_transaction"));

			tansactions.add(transaction);

		}
		fournisseur.setTransactions(tansactions);

		Connectdatabase.closeconndb();

		return fournisseur;
	}
}