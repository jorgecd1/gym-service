package com.daotask.gymservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

@Component
@RequiredArgsConstructor
public class JWTDecoder {

    Properties properties = loadProperties();

    private Properties loadProperties() {
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

    public DecodedJWT decode(String token){
        return JWT.require(Algorithm.HMAC256(properties.getProperty("security.jwt.secret-key")))
                .build()
                .verify(token);
    }
}
