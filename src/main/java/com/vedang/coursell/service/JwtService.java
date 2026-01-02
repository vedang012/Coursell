package com.vedang.coursell.service;

import com.vedang.coursell.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET = "0d11c952faf5fc10c709030c50392a1d0d11c952faf5fc10c709030c50392a1d";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }


    public boolean isTokenValid(String token, User user) {
        try {
            Claims claims = extractClaims(token);

            String email = getUsername(token);
            Date expiration = extractExpiration(token);

            return email.equals(user.getEmail()) && expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
