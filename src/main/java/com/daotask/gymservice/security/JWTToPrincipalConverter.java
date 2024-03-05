package com.daotask.gymservice.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JWTToPrincipalConverter {
    public UserPrincipal convert(DecodedJWT decodedJWT){
        return UserPrincipal.builder()
                .userId(Long.valueOf(decodedJWT.getSubject()))
                .username(decodedJWT.getClaim("e").asString())
                .authorities(extractAuthoritiesFromClaim(decodedJWT))
                .build();
    }

    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT decodedJWT){
        var claim = decodedJWT.getClaim("a");
        if(claim.isNull()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }
}
