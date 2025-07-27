package com.learn.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
    private static final String URI = "mongodb+srv://atlasroot:root123@testcluster0.jxkbbko.mongodb.net/?retryWrites=true&w=majority&appName=TestCluster0";
    private static MongoClient mongoClient;

    public static MongoDatabase getDatabase(String dbName) {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI); // creates the connection
        }
        return mongoClient.getDatabase(dbName);
    }
}