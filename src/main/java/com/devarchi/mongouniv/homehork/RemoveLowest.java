package com.devarchi.mongouniv.homehork;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by donghoon on 2016. 4. 6..
 */
public class RemoveLowest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("school");
        MongoCollection students = db.getCollection("students");
        MongoCursor<Document> cursor = students.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document student = cursor.next();
                List<Document> scores = (List<Document>) student.get("scores");

                double minScore = Double.MAX_VALUE;
                Document minDocumentObj = null;

                for (Document scoreDocument : scores) {
                    String type = scoreDocument.getString("type");
                    double score = scoreDocument.getDouble("score");

                    if (type.equals("homework") && score < minScore) {
                        minScore = score;
                        minDocumentObj = scoreDocument;
                    }
                }

                if (minDocumentObj != null) {
                    scores.remove(minDocumentObj);
                }

                students.updateOne(eq("_id", student.get("_id")), new Document("$set", new Document("scores", scores)));
            }
        } finally {
            cursor.close();
        }
        client.close();
    }
}
