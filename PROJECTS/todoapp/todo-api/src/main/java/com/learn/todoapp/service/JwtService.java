package com.learn.todoapp.service;

import com.learn.todoapp.dto.TokenApiResponse;
import com.learn.todoapp.enity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {

    @Value("${auth.jwt.jwtSecret}")
    private String jwtSecret;

    @Value("${auth.jwt.jwtExpiration}")
    private int jwtExpiration;

    private final static SecretKey strongDynamicKey;

    static { // for strong secret
        strongDynamicKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public TokenApiResponse generateJwtToken(Authentication authentication) {
        Map<String, String> claims = new HashMap<>();
        final User user = (User) authentication.getPrincipal();
        claims.put("email", user.getEmail());
        claims.put("roles", user.getRoles());

        final String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setHeader(Map.of(Header.TYPE, Header.JWT_TYPE))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 1000L * jwtExpiration))
                .signWith(getSignKeyWhereSecretIsEncoded(), SignatureAlgorithm.HS384)
                .compact();
        return TokenApiResponse.builder()
                .tokenType("Bearer")
                .token(token)
                .expiresIn((extractExpiration(token).getTime() - System.currentTimeMillis())/1000L)
                .build();
    }


    private SecretKey getSignKeyWhereSecretIsEncoded() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private SecretKey getSignKeyWhereSecretIsNotEncoded() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private SecretKey getSigningKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKeyWhereSecretIsEncoded())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
