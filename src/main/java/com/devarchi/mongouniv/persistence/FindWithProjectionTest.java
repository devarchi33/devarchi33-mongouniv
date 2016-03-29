package com.devarchi.mongouniv.persistence;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.devarchi.mongouniv.util.Helper.printJson;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

/**
 * Created by donghoon on 2016. 3. 30..
 */
public class FindWithProjectionTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("52.79.136.22");
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");

        collection.drop();

        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document()
                    .append("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100))
                    .append("i", i));
        }

        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));

//        projection 필드 사라지게 하기. 0: 사라짐 1: 보이기
//        Bson projection = new Document("x", 0)
//                .append("_id", 0);

        Bson projection = fields(include("y","i"), exclude("_id"));

        List<Document> all = collection.find(filter)
                .projection(projection)
                .into(new ArrayList<Document>());

        for (Document doc : all) {
            printJson(doc);
        }


    }
}
