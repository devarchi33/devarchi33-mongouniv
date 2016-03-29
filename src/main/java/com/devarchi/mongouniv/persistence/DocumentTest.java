package com.devarchi.mongouniv.persistence;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Date;

import static com.devarchi.mongouniv.util.Helper.printJson;

/**
 * Created by donghoon on 2016. 3. 29..
 */
public class DocumentTest {
    public static void main(String[] args) {
        Document document = new Document()
                .append("str", "Hello Mongo!")
                .append("int", 42)
                .append("l", 2l)
                .append("b", true)
                .append("date", new Date())
                .append("objectId", new ObjectId())
                .append("null", null)
                .append("embeddedDoc", new Document("x", 0))
                .append("list", Arrays.asList(1, 2, 3));

        printJson(document);

        String str = (String) document.get("str");
        System.out.println(str);

        BsonDocument bsonDocument = new BsonDocument("str", new BsonString("MongoDB Hello!"));
        System.out.println(bsonDocument);
    }
}
