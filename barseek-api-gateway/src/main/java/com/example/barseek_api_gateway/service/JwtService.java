package com.example.barseek_api_gateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import javax.crypto.SecretKey;
import java.util.List;

@Service
public class JwtService {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";

    @Value("${jwt.token.secret-key}")
    private String jwtSigningKey;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSigningKey.getBytes());
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractToken(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HEADER_NAME);
        if(authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            return authHeader.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    public Claims getClaims(String token) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(jwtSigningKey.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUserId(String token) {
        return getClaims(token).getSubject();
    }

    public List<String> extractRoles(String token) {
        return getClaims(token).get("roles", List.class);
    }


}
