package com.projects.chamados.services;

import com.projects.chamados.dtos.inputs.LoginInputDTO;
import com.projects.chamados.dtos.outputs.AccessTokenOutputDTO;
import com.projects.chamados.exceptions.UnauthorizedException;
import com.projects.chamados.utils.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TechnicianService technicianService;
    @Value("${jwt.secret}") String secretKey;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                secretKey.getBytes(StandardCharsets.UTF_8)
        );
    }

    private String generateAccessToken(String technicianEmail){
        var expirationTime = 86400; // 24 horas

        Date now = Date.from(Instant.now());
        Date expirationDate = Date.from(Instant.now().plusSeconds(expirationTime));


        return Jwts.builder()
                .setSubject(technicianEmail)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(this.getSigningKey()).compact();
    }

    public AccessTokenOutputDTO login(LoginInputDTO loginData){
        try{

            var technician = new UsernamePasswordAuthenticationToken(
                    loginData.email(),
                    loginData.password()
            );

            Authentication authentication = authenticationManager.authenticate(technician);
            var technicianEmail = authentication.getName();

            var accessToken  = new AccessTokenOutputDTO(this.generateAccessToken(technicianEmail));

            return accessToken;

        }catch(AuthenticationException exception){
            throw new UnauthorizedException(Constants.INVALID_CREDENTIALS);
        }
    }
}
