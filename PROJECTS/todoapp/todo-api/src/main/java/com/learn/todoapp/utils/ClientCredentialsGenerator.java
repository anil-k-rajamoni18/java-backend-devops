package com.learn.todoapp.utils;

import lombok.experimental.UtilityClass;
import java.security.SecureRandom;
import java.util.UUID;

@UtilityClass
public class ClientCredentialsGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * Generates a unique client ID
     */
    public static String generateClientId() {
        return "client_" + UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Generates a secure client secret
     */
    public static String generateClientSecret() {
        StringBuilder secret = new StringBuilder(64);
        for (int i = 0; i < 64; i++) {
            secret.append(CHARACTERS.charAt(secureRandom.nextInt(CHARACTERS.length())));
        }
        return secret.toString();
    }
}
