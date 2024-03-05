package com.daotask.gymservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Component
public class JWTIssuer {

    private Properties properties;

    public JWTIssuer() throws IOException {
        properties = loadProperties();
    }

    private Properties loadProperties() throws IOException {
        Properties props = new Properties();
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("application-dev.properties")){
            if(input != null){
                props.load(input);
            }
            else {
                System.err.println("Unable to load class properties");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return props;
    }

    public String issue(long userId, String username, List<String> roles){
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Date.from(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS))))
                .withClaim("u",username)
                .withClaim("a",roles)
                .sign(Algorithm.HMAC256(
                        properties.getProperty("security.jwt.secret-key")
                ));
    }
}
