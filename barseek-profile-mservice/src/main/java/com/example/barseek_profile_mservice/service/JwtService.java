package com.example.barseek_profile_mservice.service;


import com.example.barseek_profile_mservice.security.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";

    @Value("${security.jwt.token.secret-key}")
    private String jwtSigningKey;

    public Authentication createAuthentication(String token) {
        Claims claims = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(jwtSigningKey.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String userId = claims.getSubject();
        String role = claims.get("role", String.class);
        UserPrincipal principal = new UserPrincipal(userId, role);
        return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
    }

    public String extractToken(HttpServletRequest request) {
        String header = request.getHeader(HEADER_NAME);
        if (header != null && header.startsWith(BEARER_PREFIX)) {
            return header.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(jwtSigningKey.getBytes()))
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
