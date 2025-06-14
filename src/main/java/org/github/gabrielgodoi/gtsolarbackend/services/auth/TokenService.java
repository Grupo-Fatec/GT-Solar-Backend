package org.github.gabrielgodoi.gtsolarbackend.services.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.entities.admins.Admin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RequiredArgsConstructor
@Service
public class TokenService {
    @Value("{api.security.token.secret}")
    private String secret;

    public String generateToken(Admin admin) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.create()
                    .withIssuer("auth")
                    .withSubject(admin.getEmail())
                    .withSubject(admin.getName())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
