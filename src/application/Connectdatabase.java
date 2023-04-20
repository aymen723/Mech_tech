package application;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import org.bson.Document;

import com.mongodb.MongoClientException;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoException;
import com.mongodb.MongoSocketOpenException;
//import com.mongodb.MongoClient;
// import com.mongodb.MongoClientURI;
// import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.ClusterDescription;

public class Connectdatabase {
	private static com.mongodb.client.MongoClient mongoClient;
	private static MongoDatabase database;
	private static MongoCollection<Document> collection;

	static public String url = "mongodb://localhost:27017";

	// 192.168.10.150:27017
	public static MongoCollection<Document> connectdb(String collectionName) {
		// MongoCollection<Document> collection = null;
		// try {
		// mongoClient = MongoClients.create(url);
		// database = mongoClient.getDatabase("Meca_tech");
		// collection = database.getCollection(collectionName);
		// } catch (Exception e) {
		// System.out.println(e);t
		// }

		// if (mongoClient == null) {
		try {
			mongoClient = MongoClients.create(url);
			database = mongoClient.getDatabase("Meca_tech");
			collection = database.getCollection(collectionName);
		} catch (MongoCommandException e) {
			System.out.println("Failed to connect to the specified database: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
		}

		// }

		return collection;

	}

	public static void closeconndb() {
        mongoClient.close();
    }

	public static void testdb() throws SocketException {
		try {
			mongoClient = MongoClients.create(url);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void Getstringdb(String Stringdb) {
		url = "mongodb://" + Stringdb;
	}

}
