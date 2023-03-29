package application.controller;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import application.Connectdatabase;


public class AdminController {

    public static void AddEmp(Document Doc){

        MongoCollection collection = Connectdatabase.connectdb("usrs");
        collection.insertOne(collection);
        
    }
    
}
