package com.devarchi.mongouniv.persistence;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.devarchi.mongouniv.util.Helper.printJson;

/**
 * Created by donghoon on 2016. 3. 30..
 */
public class FindTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("52.79.136.22");
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findTest");

        collection.drop();

        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document("x", i));
        }

        System.out.println("Find One..");
        Document first = collection.find().first();
        printJson(first);

        System.out.println("Find all with into..");
        List<Document> all = collection.find().into(new ArrayList<Document>());
        for (Document doc : all) {
            printJson(doc);
        }

        System.out.println("Find all with iteration..");
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while ((cursor.hasNext())) {
                Document cur = cursor.next();
                printJson(cur);
            }
        } finally {
            cursor.close();
        }

        System.out.println("Count..");
        Long count = collection.count();
        System.out.println(count);
    }
}
