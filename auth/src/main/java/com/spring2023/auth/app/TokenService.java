package com.spring2023.auth.app;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

public class TokenService  {

    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        super();
        this.jwtEncoder = jwtEncoder;
    }

    public String generateAccessToken(CustomUserDetails usrDetails) {
        Instant now = Instant.now();
        String scope = usrDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(2, ChronoUnit.MINUTES))
                .subject(usrDetails.getUsername())
                .claim("scope", scope)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateRefreshToken(CustomUserDetails usrDetails) {
        Instant now = Instant.now();
        String scope = usrDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
                .subject(usrDetails.getUsername())
                .claim("scope", scope)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public JWTClaimsSet parseToken(String token) {
        try {
            if (token.startsWith("Bearer "))
                token = token.substring(7);
            SignedJWT decodedJWT = SignedJWT.parse(token);
            var claims = decodedJWT.getJWTClaimsSet();
            return claims;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}