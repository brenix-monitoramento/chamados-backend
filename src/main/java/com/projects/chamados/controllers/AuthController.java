package com.projects.chamados.controllers;

import com.projects.chamados.dtos.inputs.LoginInputDTO;
import com.projects.chamados.dtos.outputs.AccessTokenOutputDTO;
import com.projects.chamados.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value="/login")
    public ResponseEntity<AccessTokenOutputDTO> login(@RequestBody @Valid LoginInputDTO loginData){
            var accessToken = this.authService.login(loginData);

            return ResponseEntity.ok().body(accessToken);
    }
}
