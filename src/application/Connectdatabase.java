package application;

import org.bson.Document;

//import com.mongodb.MongoClient;
// import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connectdatabase {
	private static com.mongodb.client.MongoClient mongoClient;
	private static MongoDatabase database;

	static public String url = "mongodb://localhost:27017";

	public static MongoCollection<Document> connectdb(String collectionName) {
		mongoClient = MongoClients.create("mongodb://localhost:27017");
		database = mongoClient.getDatabase("Meca_tech");
		MongoCollection<Document> collection = database.getCollection(collectionName);
		return collection;
	}

	public static void closeconndb() {
		mongoClient.close();
	}

}
