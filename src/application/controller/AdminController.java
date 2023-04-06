package application.controller;

import org.bson.Document;

import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import application.Connectdatabase;
import application.ViewController.add_employer_controller;
import application.models.Clientmodel;
import application.models.Parts;
import application.models.Usermodel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminController {

	public static void AddEmp(Document Doc) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("users");
		collection.insertOne(Doc);

	}

	public static void UpdateEmp(Document Doc) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("users");
		ObjectId objid = new ObjectId(add_employer_controller.user.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		Doc.append("_id", objid);
		Document updateop = new Document("$set", Doc);
		collection.updateOne(found, updateop);

	}

	public static void deletemp() {
		MongoCollection<Document> collection = Connectdatabase.connectdb("users");
		ObjectId objid = new ObjectId(add_employer_controller.user.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		collection.deleteOne(found);
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
		}

		return List;

	}

	public static void addpart(Document Doc) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("parts");
		collection.insertOne(Doc);

	}

	public static void updatepart(Document Doc, Parts part) {
		MongoCollection<Document> collection = Connectdatabase.connectdb("parts");
		ObjectId objid = new ObjectId(part.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		System.out.println(found.get("name"));
		Doc.append("_id", objid);
		Document updateop = new Document("$set", Doc);
		collection.updateOne(found, updateop);

	}

	public static void deletpart(Parts part) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("parts");
		ObjectId objid = new ObjectId(part.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		collection.deleteOne(found);

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
		}

		return List;

	}

	
	public static void AddClient(Document Doc) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("clients");
		collection.insertOne(Doc);

	}

	public static void UpdateClient(Document Doc) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("clients");
		ObjectId objid = new ObjectId(add_employer_controller.user.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		Doc.append("_id", objid);
		Document updated = new Document("$set", Doc);
		collection.updateOne(found, updated);

	}

	public static void deletClient() {
		MongoCollection<Document> collection = Connectdatabase.connectdb("clients");
		ObjectId objid = new ObjectId(add_employer_controller.user.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		collection.deleteOne(found);
	}

	public static ObservableList<Clientmodel> EmpClients() {
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
		}

		return List;

	}
	

}