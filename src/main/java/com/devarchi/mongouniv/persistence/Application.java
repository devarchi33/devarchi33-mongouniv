package com.devarchi.mongouniv.persistence;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static java.util.Arrays.asList;

/**
 * Created by donghoon on 2016. 3. 29..
 */
public class Application {
    public static void main(String[] args) {
//         연결 방법 1
//        MongoClient client = new MongoClient(asList(new ServerAddress("localhost", 27017))); //defalut localhost, 27017, replset 대비 asList

//        연결 방법 2
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build(); //default 100
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://52.79.136.22:27017"));
//        MongoClient client = new MongoClient(new ServerAddress(), options);

        MongoDatabase db = client.getDatabase("students").withReadPreference(ReadPreference.secondary());
        MongoCollection<Document> collection = db.getCollection("grades");
        Long gradesCnt = collection.count();

        System.out.println(gradesCnt);
    }
}
