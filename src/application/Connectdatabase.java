package application;

import org.bson.Document;

//import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connectdatabase {

	static public String url = "mongodb://localhost:27017";

	public static MongoCollection<Document> connectdb(String collectionName) {
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase("Meca_tech");
		MongoCollection<Document> collection = database.getCollection(collectionName);
		return collection;
	}

}
