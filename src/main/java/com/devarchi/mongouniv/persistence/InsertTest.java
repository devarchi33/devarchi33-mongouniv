package com.devarchi.mongouniv.persistence;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

import static com.devarchi.mongouniv.util.Helper.printJson;

/**
 * Created by donghoon on 2016. 3. 30..
 */
public class InsertTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("52.79.136.22");
        MongoDatabase db = client.getDatabase("students");
        MongoCollection<Document> collection = db.getCollection("insertTest");

        collection.drop();

        Document smith = new Document("name", "smith")
                .append("age", 30)
                .append("profession", "programmer");

        Document jones = new Document("name", "jones")
                .append("age", 35)
                .append("profession", "baseball-player");

//        collection.insertOne(smith);

        collection.insertMany(Arrays.asList(smith, jones));
        printJson(smith);
        printJson(jones);
    }
}
