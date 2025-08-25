package com.learn.todoapp.utils;

import org.springframework.stereotype.Component;
import java.security.SecureRandom;
import java.util.UUID;

@Component
public class ClientCredentialsGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * Generates a unique client ID
     */
    public String generateClientId() {
        return "client_" + UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Generates a secure client secret
     */
    public String generateClientSecret() {
        StringBuilder secret = new StringBuilder(64);
        for (int i = 0; i < 64; i++) {
            secret.append(CHARACTERS.charAt(secureRandom.nextInt(CHARACTERS.length())));
        }
        return secret.toString();
    }
}
