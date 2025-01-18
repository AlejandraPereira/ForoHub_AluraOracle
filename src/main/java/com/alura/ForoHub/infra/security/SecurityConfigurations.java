package com.alura.ForoHub.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;  // Usa un filtro ya existente

    // Constructor para inyectar el filtro
    public SecurityConfigurations(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            return httpSecurity.csrf(csrf -> csrf.disable())
                    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(req -> {

                        req.requestMatchers("/swagger-ui.html","/swagger-ui/**","/v3/api-docs/**").permitAll();

                        //login
                       req.requestMatchers(HttpMethod.POST, "/login").permitAll();
                       //actualizar topicos
                       req.requestMatchers(HttpMethod.PUT, "/topicos/{id}").permitAll();

                        //Obtener topico por id
                       req.requestMatchers(HttpMethod.GET, "/topicos/{id}").permitAll();

                       //listar topicos
                       req.requestMatchers(HttpMethod.GET, "/topicos").permitAll();

                       //registart topicos
                       req.requestMatchers(HttpMethod.POST, "/topicos").permitAll();

                       // borrar topico
                        req.requestMatchers(HttpMethod.DELETE, "/topicos/{id}").permitAll();

                       req.anyRequest().authenticated();
                        })
                    .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        }

    @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
        }
    @Bean
        public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        }
}

