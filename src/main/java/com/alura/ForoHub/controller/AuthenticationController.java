package com.alura.ForoHub.controller;

import com.alura.ForoHub.dto.users.UserAuthenticationData;
import com.alura.ForoHub.domain.model.Users;
import com.alura.ForoHub.infra.security.JWTTokenData;
import com.alura.ForoHub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@SecurityRequirement(name = "bearer-key")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid UserAuthenticationData userAuthenticationData){

        Authentication authToken = new UsernamePasswordAuthenticationToken(userAuthenticationData.login(), userAuthenticationData.clave());
        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((Users) authenticatedUser.getPrincipal());
        return  ResponseEntity.ok( new JWTTokenData(JWTtoken));
    }

}
