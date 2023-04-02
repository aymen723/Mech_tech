package application.controller;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import application.Connectdatabase;
import application.ViewController.add_employer_controller;
import application.ViewController.parts_dashbord_controller;
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
		System.out.println(found.get("password"));
		Doc.append("_id", objid);
		Document updateop = new Document("$set", Doc);
		collection.updateOne(found, updateop);

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

				user.setUsername(doc.getString("username"));
				user.setEmail(doc.getString("email"));

				List.add(user);

			}
		} finally {
			cursor.close();
		}

		return List;

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
		} finally {
			cursor.close();
		}

		return List;

	}

	public static void addpart(Document Doc) {

		MongoCollection<Document> collection = Connectdatabase.connectdb("parts");
		collection.insertOne(Doc);

	}

	public static void updatepart(Document Doc) {
		MongoCollection<Document> collection = Connectdatabase.connectdb("parts");
		ObjectId objid = new ObjectId(parts_dashbord_controller.part.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		System.out.println(found.get("name"));
		Doc.append("_id", objid);
		Document updateop = new Document("$set", Doc);
		collection.updateOne(found, updateop);

	}

	public static void deletpart() {

		MongoCollection<Document> collection = Connectdatabase.connectdb("parts");
		ObjectId objid = new ObjectId(parts_dashbord_controller.part.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
		collection.deleteOne(found);

	}

	public static void deletemp() {
		MongoCollection<Document> collection = Connectdatabase.connectdb("users");
		ObjectId objid = new ObjectId(add_employer_controller.user.getId());
		Document found = (Document) collection.find(new Document("_id", objid)).first();
        collection.deleteOne(found);
	}

}
