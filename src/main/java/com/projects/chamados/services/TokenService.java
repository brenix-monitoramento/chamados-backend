package com.projects.chamados.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {
    @Value("${jwt.secret}") String secretKey;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateAccessToken(String technicianEmail){
        var expirationTime = 86400; // 24 horas

        Date now = Date.from(Instant.now());
        Date expirationDate = Date.from(Instant.now().plusSeconds(expirationTime));


        return Jwts.builder()
                .setSubject(technicianEmail)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(this.getSigningKey()).compact();
    }

    public String validateAccessToken(String token){
        try{
            return Jwts.parser().setSigningKey(this.getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch (Exception exception){
            // retorna null caso o token seja inválido
            return null;
        }
    }

    public String getFormattedToken (HttpServletRequest request){
        var bearerToken = request.getHeader("Authorization");

        boolean isInvalidToken  = bearerToken == null || !bearerToken.startsWith("Bearer ");

        if(isInvalidToken) return null;

        var formattedToken = bearerToken.replace("Bearer ", "");

        return formattedToken;
    }
}
