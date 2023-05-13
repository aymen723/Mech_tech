package application.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOneModel;
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
import application.models.Sales;
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
				part.setBuyingprice(doc.getInteger("buyingprice"));
				part.setBuyingdate(doc.getDate("buyingdate"));

				Document fourniDocument = doc.get("fournisseur", Document.class);
				Fournisseur fournisseur = new Fournisseur();

				fournisseur.setId(fourniDocument.getObjectId("_id").toString());
				fournisseur.setName(fourniDocument.getString("nom"));
				// SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

				fournisseur.setAddress(fourniDocument.getString("adresse"));
				fournisseur.setPhone(fourniDocument.getString("numero"));
				fournisseur.setEmail(fourniDocument.getString("email"));

				part.setFournisseur(fournisseur);

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

				if (clientdoc.getObjectId("_id") != null) {
					client.setId(clientdoc.getObjectId("_id").toString());
				}

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

					// part.setId(doc.getObjectId("_id").toString());

					// part.setName(pardoc.getString("name"));
					// part.setDescription(pardoc.getString("description"));
					// part.setQuntitie(pardoc.getInteger("quantity"));
					// part.setPrice(pardoc.getInteger("price"));

					// partlist.add(part);
					part.setId(pardoc.getObjectId("_id").toString());

					part.setName(pardoc.getString("name"));
					part.setDescription(pardoc.getString("description"));
					part.setQuntitie(pardoc.getInteger("quantity"));
					part.setPrice(pardoc.getInteger("price"));
					part.setBuyingprice(pardoc.getInteger("prix_achat"));
					part.setBuyingdate(doc.getDate("date_de_vente"));

					Document fourniDocument = pardoc.get("fournisseur", Document.class);
					Fournisseur fournisseur = new Fournisseur();

					fournisseur.setId(fourniDocument.getObjectId("_id").toString());
					fournisseur.setName(fourniDocument.getString("name"));
					// SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

					fournisseur.setAddress(fourniDocument.getString("adresse"));
					fournisseur.setPhone(fourniDocument.getString("numero"));
					fournisseur.setEmail(fourniDocument.getString("email"));

					part.setFournisseur(fournisseur);
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

	//
	public static void update_parts_qtnt(ArrayList<Parts> listp) {

		// System.out.println("id part " + listp.get(0).getName());
		MongoCollection<Document> collection = Connectdatabase.connectdb("parts");
		ObservableList<Parts> list_before = AdminController.PartList();
		// System.out.println("list before " + list_before.size());
		ArrayList<Parts> newlist = new ArrayList<Parts>();
		ArrayList<Document> myDocuments = new ArrayList<Document>();
		List<UpdateOneModel<Document>> updates = new ArrayList<>();

		for (Parts part : listp) {
			// System.out.println("haana part id" + part.getId());
			for (int i = 0; i < list_before.size(); i++) {
				// System.out.println("haana listbefore part id" + list_before.get(i).getId());
				if (list_before.get(i).getId().equals(part.getId())) {

					list_before.get(i).setQuntitie(list_before.get(i).getQuntitie() - part.getQuntitie());
					// System.out.println(list_before.get(i).getName() + "quntt changed to " +
					// (list_before.get(i).getQuntitie() - list.get(i).getQuntitie()));
					newlist.add(list_before.get(i));
					Document addpart = new Document("_id", new ObjectId(list_before.get(i).getId()));
					// addpart.append("name", list_before.get(i).getName());
					// addpart.append("price", list_before.get(i).getPrice());
					addpart.append("quantity", list_before.get(i).getQuntitie());
					// addpart.append("description", list_before.get(i).getDescription());

					myDocuments.add(addpart);

					break;
				}
			}
		}
		System.out.println(" my documents size " + myDocuments.size());

		for (Document doc : myDocuments) {
			ObjectId id = doc.getObjectId("_id");
			// Create the update operation to set the new field values
			UpdateOneModel<Document> update = new UpdateOneModel<>(
					Filters.eq("_id", id),
					new Document("$set", doc));
			updates.add(update);

		}

		BulkWriteResult result = collection.bulkWrite(updates);
		System.out.println("this is update part rslt" + result);

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

				List<Document> parlistdoc = doc.getList("parts", Document.class);
				ArrayList<Parts> partlist = new ArrayList<Parts>();
				for (Document pardoc : parlistdoc) {
					Parts part = new Parts();

					// part.setId(doc.getObjectId("_id").toString());

					// part.setName(pardoc.getString("name"));
					// part.setDescription(pardoc.getString("description"));
					// part.setQuntitie(pardoc.getInteger("quantity"));
					// part.setPrice(pardoc.getInteger("price"));

					// partlist.add(part);

					part.setId(pardoc.getObjectId("_id").toString());

					part.setName(pardoc.getString("name"));
					part.setDescription(pardoc.getString("description"));
					part.setQuntitie(pardoc.getInteger("quantity"));
					part.setPrice(pardoc.getInteger("price"));
					part.setBuyingprice(pardoc.getInteger("prix_achat"));
					part.setBuyingdate(doc.getDate("date_de_vente"));

					Document fourniDocument = pardoc.get("fournisseur", Document.class);
					Fournisseur fournisseur = new Fournisseur();

					fournisseur.setId(fourniDocument.getObjectId("_id").toString());
					fournisseur.setName(fourniDocument.getString("name"));
					// SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

					fournisseur.setAddress(fourniDocument.getString("adresse"));
					fournisseur.setPhone(fourniDocument.getString("numero"));
					fournisseur.setEmail(fourniDocument.getString("email"));

					part.setFournisseur(fournisseur);
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
				List.add(fournisseur);

			}
		} finally {
			cursor.close();
			Connectdatabase.closeconndb();

		}

		return List;

	}

	public static void deleteFournisseur(Fournisseur fournisseur) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("fournisseurs");
		ObjectId objid = new ObjectId(fournisseur.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		collection.deleteOne(found);
		Connectdatabase.closeconndb();

	}

	public static void addTransaction(Document transaction, Fournisseur fournisseur) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("fournisseurs");
		Bson filter = Filters.eq("_id", new ObjectId(fournisseur.getId()));

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

	public static void deleteTransaction(Transaction transaction, Fournisseur fournisseur) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("fournisseurs");
		Bson filter = Filters.eq("_id", new ObjectId(fournisseur.getId()));

		Bson update = Updates.pull("Transactions", new Document("_id", new ObjectId(transaction.getId())));
		Bson updatebalance = Updates.set("balance",
				fournisseur.getBalance() - transaction.getSomme_payee() + transaction
						.getSomme_de_transaction());
		UpdateResult UR = collection.updateOne(filter, update);
		System.out.println(UR);
		UpdateResult URbalance = collection.updateOne(filter, updatebalance);
		System.out.println(URbalance);

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

	public static void AddSale(Document Doc) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("ventes");
		collection.insertOne(Doc);
		Connectdatabase.closeconndb();

	}

	public static void UpdateSale(Document Doc, Sales sale) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("ventes");
		ObjectId objid = new ObjectId(sale.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		Doc.append("_id", objid);
		Document updateop = new Document("$set", Doc);
		collection.updateOne(found, updateop);
		Connectdatabase.closeconndb();

	}

	public static ArrayList<Sales> ListSales() {
		ArrayList<Sales> List = new ArrayList<Sales>();
		MongoCollection<Document> collection = Connectdatabase.connectdb("ventes");

		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				Sales sale = new Sales();

				sale.setId(doc.getObjectId("_id").toString());
				sale.setDate_de_vente(doc.getDate("date_de_vente"));
				// SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

				List<Document> parlistdoc = doc.getList("parts", Document.class);
				ArrayList<Parts> part_list = new ArrayList<Parts>();
				for (Document pardoc : parlistdoc) {
					Parts part = new Parts();

					part.setId(pardoc.getObjectId("_id").toString());

					part.setName(pardoc.getString("name"));
					part.setDescription(pardoc.getString("description"));
					part.setQuntitie(pardoc.getInteger("quantity"));
					part.setPrice(pardoc.getInteger("price"));
					part.setBuyingprice(pardoc.getInteger("prix_achat"));
					part.setBuyingdate(doc.getDate("date_de_vente"));

					Document fourniDocument = pardoc.get("fournisseur", Document.class);
					Fournisseur fournisseur = new Fournisseur();

					fournisseur.setId(fourniDocument.getObjectId("_id").toString());
					fournisseur.setName(fourniDocument.getString("name"));
					// SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

					fournisseur.setAddress(fourniDocument.getString("adresse"));
					fournisseur.setPhone(fourniDocument.getString("numero"));
					fournisseur.setEmail(fourniDocument.getString("email"));

					part.setFournisseur(fournisseur);

					part_list.add(part);

				}
				sale.setPartList(part_list);
				List.add(sale);
			}

		} finally {
			cursor.close();
			Connectdatabase.closeconndb();

		}

		return List;

	}

	public static void deleteSale(Sales sale) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("ventes");
		ObjectId objid = new ObjectId(sale.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		collection.deleteOne(found);
		Connectdatabase.closeconndb();

	}

	public static void update_parts_qtnt_delete(ArrayList<Parts> listp) {

		// System.out.println("id part " + listp.get(0).getName());
		MongoCollection<Document> collection = Connectdatabase.connectdb("parts");
		ObservableList<Parts> list_before = AdminController.PartList();
		// System.out.println("list before " + list_before.size());
		ArrayList<Parts> newlist = new ArrayList<Parts>();
		ArrayList<Document> myDocuments = new ArrayList<Document>();
		List<UpdateOneModel<Document>> updates = new ArrayList<>();

		for (Parts part : listp) {
			// System.out.println("haana part id" + part.getId());
			for (int i = 0; i < list_before.size(); i++) {
				// System.out.println("haana listbefore part id" + list_before.get(i).getId());
				if (list_before.get(i).getId().equals(part.getId())) {

					list_before.get(i).setQuntitie(list_before.get(i).getQuntitie() + part.getQuntitie());
					// System.out.println(list_before.get(i).getName() + "quntt changed to " +
					// (list_before.get(i).getQuntitie() - list.get(i).getQuntitie()));
					newlist.add(list_before.get(i));
					Document addpart = new Document("_id", new ObjectId(list_before.get(i).getId()));
					// addpart.append("name", list_before.get(i).getName());
					// addpart.append("price", list_before.get(i).getPrice());
					addpart.append("quantity", list_before.get(i).getQuntitie());
					// addpart.append("description", list_before.get(i).getDescription());

					myDocuments.add(addpart);

					break;
				}
			}
		}

		for (Document doc : myDocuments) {
			ObjectId id = doc.getObjectId("_id");
			// Create the update operation to set the new field values
			UpdateOneModel<Document> update = new UpdateOneModel<>(
					Filters.eq("_id", id),
					new Document("$set", doc));
			updates.add(update);

		}

		BulkWriteResult result = collection.bulkWrite(updates);
		System.out.println("this is update part delete rslt" + result);

	}

	public static ArrayList<Rendez_vous> statRdvList(LocalDate date) {
		MongoCollection<Document> collection = Connectdatabase.connectdb("Rendez_vous");
		ArrayList<Rendez_vous> List = new ArrayList<>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		date = date.minusDays(date.getDayOfMonth() - 1);
		String datedabutString = date.atStartOfDay().format(formatter);
		Date startDate = Date.from(Instant.parse(datedabutString));

		LocalDate datefin = date.plusMonths(1).minusDays(1);
		String datefinString = datefin.atStartOfDay().format(formatter);
		Date endDate = Date.from(Instant.parse(datefinString));

		Document query = new Document("date_debut", new Document("$gte", startDate)
				.append("$lt", endDate));

		MongoCursor<Document> cursor = collection.find(query).iterator();

		try {
			while (cursor.hasNext()) {
				System.out.println("this inside the wile");
				Document doc = cursor.next();
				Rendez_vous rdv = new Rendez_vous();

				rdv.setId(doc.getObjectId("_id").toString());
				rdv.setCar_model(doc.getString("car model"));

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

				List<Document> parlistdoc = doc.getList("parts", Document.class);
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
			List.sort((doc1, doc2) -> (doc1.getDate_debut()).compareTo(doc2.getDate_debut()));
			cursor.close();
			Connectdatabase.closeconndb();

		}
		System.out.println("this is the size of list rdv stats " + List.size());
		System.out.println("date debut " + datedabutString + "date fin" + datefinString);

		return List;

	}

	public static ArrayList<Sales> statListSales(LocalDate date) {
		ArrayList<Sales> List = new ArrayList<Sales>();
		MongoCollection<Document> collection = Connectdatabase.connectdb("ventes");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		date = date.minusDays(date.getDayOfMonth() - 1);
		String datedabutString = date.atStartOfDay().format(formatter);
		Date startDate = Date.from(Instant.parse(datedabutString));

		LocalDate datefin = date.plusMonths(1).minusDays(1);
		String datefinString = datefin.atStartOfDay().format(formatter);
		Date endDate = Date.from(Instant.parse(datefinString));

		Document query = new Document("date_de_vente", new Document("$gte",
				startDate)
				.append("$lt", endDate));

		MongoCursor<Document> cursor = collection.find(query).iterator();

		try {
			while (cursor.hasNext()) {

				Document doc = cursor.next();
				Sales sale = new Sales();

				sale.setId(doc.getObjectId("_id").toString());
				sale.setDate_de_vente(doc.getDate("date_de_vente"));

				List<Document> parlistdoc = doc.getList("parts", Document.class);
				ArrayList<Parts> part_list = new ArrayList<Parts>();
				for (Document pardoc : parlistdoc) {
					Parts part = new Parts();

					part.setId(pardoc.getObjectId("_id").toString());

					part.setName(pardoc.getString("name"));
					part.setDescription(pardoc.getString("description"));
					part.setQuntitie(pardoc.getInteger("quantity"));
					part.setPrice(pardoc.getInteger("price"));
					part.setBuyingprice(pardoc.getInteger("prix_achat"));
					part.setBuyingdate(doc.getDate("date_de_vente"));

					part_list.add(part);

				}
				sale.setPartList(part_list);
				List.add(sale);
			}

		} finally {
			cursor.close();
			Connectdatabase.closeconndb();

		}
		System.out.println("stat sales" + List.size());
		return List;

	}

}