package application;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClientException;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoException;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClientOptions.Builder;
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

	static public String url = "mongodb://192.168.138.95:27017";

	static public String url2;

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
		// System.out.println(mongoClient);

		// try {
		// mongoClient = MongoClients.create(url);

		// } catch (Exception e) {
		// System.out.println(e.getMessage());
		// }

		// Builder o = MongoClientOptions.builder().connectTimeout(3000);

		// try {
		// MongoClient mongo = new MongoClient(new ServerAddress("192.168.0.1", 3001),
		// o.build());

		// // mongo.getAddress();
		// } catch (Exception e) {
		// System.out.println("Mongo is down");
		// // mongo.close();
		// return;
		// }
		// MongoClientOptions clientOptions = new MongoClientOptions.Builder().build();
		mongoClient = MongoClients.create(url);
		try {
			List<String> databaseNames = mongoClient.listDatabaseNames().into(new ArrayList<>());

			mongoClient = MongoClients.create();
		} catch (Exception ex) {

		}

		// if (!databaseNames.isEmpty()) {
		// System.out.println("Connected to MongoDB successfully.");
		// } else {
		// System.out.println("Unable to connect to MongoDB.");
		// }

	}

	public static void setstringdb(String Stringdb) {
		url = "mongodb://" + Stringdb;
		url2 = Stringdb;
	}

	public static String getstringdb() {
		return url;
	}

}
