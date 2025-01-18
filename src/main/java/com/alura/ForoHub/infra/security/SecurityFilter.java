package com.alura.ForoHub.infra.security;

import com.alura.ForoHub.dto.users.UserAuthenticationData;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // authentication logic: validating a JWT token
        var token = request.getHeader("Authorization");

        if(token != null ){
            token= token.replace("Bearer", "");
        }
        filterChain.doFilter(request, response);

    }


}
