package application.controller;


import org.bson.Document;
import org.bson.types.ObjectId;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import application.Connectdatabase;
import application.Usermodel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.add_employer_controller;

public class AdminController {

    public static void AddEmp(Document Doc){

        MongoCollection collection = Connectdatabase.connectdb("users");
        collection.insertOne(Doc);
        
    }

    public static void UpdateEmp(Document Doc){

        
        MongoCollection collection = Connectdatabase.connectdb("users");
        ObjectId objid = new ObjectId(add_employer_controller.user.getId());
        Document found = (Document) collection.find(new Document ("_id",objid)).first();
        // Document found = (Document) collection.find;
        System.out.println(found.get("password"));
        Doc.append("_id", objid);
        Document updateop = new Document("$set",Doc);
        collection.updateOne(found, updateop);
        // collection.findOneAndUpdate(found, updateop);
        
        
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
