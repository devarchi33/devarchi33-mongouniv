package com.devarchi.mongouniv.persistence;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;
import static com.devarchi.mongouniv.util.Helper.printJson;

/**
 * Created by donghoon on 2016. 3. 31..
 */
public class FindWithSortSkipAndLimitTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient("52.79.136.22:27017");
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithSortSkipAndLimitTest");

        collection.drop();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                collection.insertOne(new Document("i", i).append("j", j));
            }
        }

        Bson projection = fields(include("i", "j"), excludeId());
//        Bson sort = ascending("j");
        Bson sort = orderBy(ascending("i"), descending("j"));

        List<Document> all = collection.find().projection(projection).sort(sort).limit(50).skip(5).into(new ArrayList<>());

        for (Document doc : all) {
            printJson(doc);
        }

    }
}
