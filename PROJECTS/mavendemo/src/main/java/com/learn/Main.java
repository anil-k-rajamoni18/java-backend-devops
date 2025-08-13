package com.learn;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream in = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(props.getProperty("message"));
        System.out.println(props.getProperty("environment"));
        System.out.println(props.getProperty("database.url"));
    }
}