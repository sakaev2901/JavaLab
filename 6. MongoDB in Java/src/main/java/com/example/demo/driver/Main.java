package com.example.demo.driver;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

public class Main {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mongo_java");
        MongoCollection<Document> collection = mongoDatabase.getCollection("cars");
        Document car = new Document();
        car.put("model", "audi");
        collection.insertOne(car); // insert
        collection.deleteOne(car); // delete
        Bson filter = set("color", "red");
        collection.updateOne(eq("model", "audi"), filter); // update
    }
}
