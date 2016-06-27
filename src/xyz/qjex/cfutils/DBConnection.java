package xyz.qjex.cfutils;

/**
 * Created by qjex on 1/21/16.
 */

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

public class DBConnection {
    private MongoClient mongoClient;
    private MongoDatabase db;

    public DBConnection(String host, String database) {
        mongoClient = new MongoClient(host);
        db = mongoClient.getDatabase(database);
    }

    public synchronized void insert(String collection, Document document) {
         db.getCollection(collection).insertOne(document);
    }

    private synchronized boolean deleteOne(String collection, Document document) {
        DeleteResult result = db.getCollection(collection).deleteOne(document);
        return (result.getDeletedCount() == 1);
    }

    private synchronized boolean deleteMany(String collection, Document document) {
        DeleteResult result = db.getCollection(collection).deleteMany(document);
        return (result.getDeletedCount() != 0);
    }

    public synchronized ArrayList<Document> find(String collection, Bson document) {
        FindIterable<Document> iterable = db.getCollection(collection).find(document);
        ArrayList<Document> documents = new ArrayList<>();
        iterable.forEach((Block<Document>) documents::add);
        return documents;
    }

    public synchronized void updateOne(String collection, Bson filter, Bson update) {
        db.getCollection(collection).updateOne(filter, update);
    }

}
