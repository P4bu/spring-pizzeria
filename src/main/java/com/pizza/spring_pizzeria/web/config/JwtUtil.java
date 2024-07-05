package com.pizza.spring_pizzeria.web.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    //Genera un JWT
    private static String SECRET_KEY = "4dm1n_p1zz4";
    private static Algorithm ALGORiTHM = Algorithm.HMAC256(SECRET_KEY);

    public String create(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("spring-pizza")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORiTHM);
    }

    public boolean isValid(String jwt) {
        try {
            JWT.require(ALGORiTHM)
                    .build()
                    .verify(jwt);
            return true;
        }
        catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUsername(String jwt) {
        return JWT.require(ALGORiTHM)
                .build()
                .verify(jwt)
                .getSubject();
    }

}
