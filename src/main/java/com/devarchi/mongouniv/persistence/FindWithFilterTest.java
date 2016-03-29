package com.devarchi.mongouniv.persistence;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.devarchi.mongouniv.util.Helper.printJson;
import static com.mongodb.client.model.Filters.*;

/**
 * Created by donghoon on 2016. 3. 30..
 */
public class FindWithFilterTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("52.79.136.22");
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");

        collection.drop();

        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document()
                    .append("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100)));
        }

//        Bson filter = new Document("x", 0)
//                .append("y", new Document("$gt", 50)
//                .append("y", new Document("$lt", 90)));

        Bson filter = and(eq("x", 0), gt("y", 30));

        List<Document> all = collection.find(filter).into(new ArrayList<>());

        for (Document doc : all) {
            printJson(doc);
        }

        Long count = collection.count();
        System.out.println();
        System.out.println(count);
    }
}
