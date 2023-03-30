package application.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import application.Connectdatabase;
import application.Usermodel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AdminController {

    public static void AddEmp(Document Doc){

        MongoCollection collection = Connectdatabase.connectdb("users");
        collection.insertOne(Doc);
        
    }

    public static void UpdateEmp(Document Doc){

        System.out.println(Doc.get("username"));
        MongoCollection collection = Connectdatabase.connectdb("users");

        Document found = (Document) collection.find(new Document ("username",Doc.get("username"))).first();
        System.out.println(found.get("password"));
        Document updateop = new Document("$set",Doc);
        collection.updateOne(found, updateop);
        
        
    }
    
    public static ObservableList<Usermodel> EmpLiist(){
        ObservableList<Usermodel> List = FXCollections.observableArrayList();
        MongoCollection collection = Connectdatabase.connectdb("users");

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

    
}