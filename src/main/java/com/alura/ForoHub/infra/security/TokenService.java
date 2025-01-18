package com.alura.ForoHub.infra.security;

import com.alura.ForoHub.domain.model.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    @Value("$api.security.secret")
    private String apiSecret;

    public String generateToken(Users users) {
        try {
            // Create the algorithm for signing the token using the secret key (apiSecret)
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            // Build the JWT token with the specified claims
            return JWT.create()
                    // Set the issuer of the token (who is generating the token)
                    .withIssuer("Foro-Hub")

                    // Set the subject of the token (usually the user's login or username)
                    .withSubject(users.getLogin())

                    // Add a custom claim (the user ID in this case)
                    .withClaim("id", users.getId())

                    // Set the expiration date of the token using a custom method
                    .withExpiresAt(generatesExpirationDate())

                    // Sign the token using the specified algorithm
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            // Handle any exceptions thrown during token creation
            // In case of an error, throw a runtime exception
            throw new RuntimeException();
        }
    }


    // Method to generate the expiration date for the token
    private Instant generatesExpirationDate() {
        // Get the current date and time
        return LocalDateTime.now()
                // Add 2 hours to the current time for token expiration
                .plusHours(8)

                // Convert the LocalDateTime to an Instant with a specific timezone offset
                .toInstant(ZoneOffset.of("-05:00")); // The time zone offset is -05:00 (e.g., UTC-5)
    }


    public String getSubject(String token) {
        DecodedJWT verifier = null;
        try {
            // Trim the token to remove extra spaces or newlines
            token = token.trim();
            // Create the algorithm for validation using the secret key
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            // Build the JWT verifier with the specified algorithm
            verifier = JWT.require(algorithm)
                    .withIssuer("Foro-Hub") // Ensure the issuer is correct
                    .build() // Build the verifier
                    .verify(token); // Verify the token

            // Return the subject of the token if it is valid
            return verifier.getSubject();

        } catch (JWTVerificationException exception) {
            // Handle exception if the token is invalid or verification fails
            exception.printStackTrace();

            // Return null if there was an error during verification
            return null;
        }
        }



}
