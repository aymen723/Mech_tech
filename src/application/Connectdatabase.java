package application;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connectdatabase {

	static public String url = "mongodb://localhost:27017";

	static public  MongoCollection<Document> connectdb(String coll) {
		MongoClientURI clineturi = new MongoClientURI(url);
		try (MongoClient clinet = new MongoClient(clineturi)) {
			MongoDatabase dbmongo = clinet.getDatabase("Meca_tech");
			MongoCollection<Document> collection = dbmongo.getCollection(coll);

			
//         if (dbmongo.hashCode() == 0) {
//	    	 System.out.println("null");
//	    	 System.out.println(dbmongo.hashCode());
//	     }else {
//	    	 System.out.println("not null");
//	    	 System.out.println(dbmongo.hashCode());
//	     }

			return collection;
		}

	}
}
