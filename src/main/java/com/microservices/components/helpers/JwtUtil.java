package com.microservices.components.helpers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "1b0447efe86c8b23ca3ca84bd74ee43f92de0abe16fae58692ad1709551b921f";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public  static String generateToken(String email) {
        return Jwts.builder().subject(email).expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(getSigningKey())
            .compact();
    }
    private static Key getSigningKey() {
        byte[] keyBytes = SECRET.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
