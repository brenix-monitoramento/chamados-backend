package com.projects.chamados.services;

import com.projects.chamados.dtos.inputs.LoginInputDTO;
import com.projects.chamados.dtos.outputs.AccessTokenOutputDTO;
import com.projects.chamados.exceptions.UnauthorizedException;
import com.projects.chamados.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private TechnicianService technicianService;
    @Value("${jwt.secret}") String secretKey;

    public AccessTokenOutputDTO login(LoginInputDTO loginData){
        try{

            var technician = new UsernamePasswordAuthenticationToken(
                    loginData.email(),
                    loginData.password()
            );

            Authentication authentication = authenticationManager.authenticate(technician);
            var technicianEmail = authentication.getName();

            var accessToken  = new AccessTokenOutputDTO(this.tokenService.generateAccessToken(technicianEmail));

            return accessToken;

        }catch(AuthenticationException exception){
            throw new UnauthorizedException(Constants.INVALID_CREDENTIALS);
        }
    }
}
