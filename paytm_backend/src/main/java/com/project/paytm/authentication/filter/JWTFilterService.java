package com.project.paytm.authentication.filter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTFilterService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String parseUserNameFromToken(String jwtToken){
        return parseSingleAttriubteFromToken(jwtToken,Claims::getSubject);
    }

    private <T> T parseSingleAttriubteFromToken(String token, Function<Claims,T> claimResolver){
        Claims allClaims = parseAllClaimsFromToken(token);
        return claimResolver.apply(allClaims);
    }

    private Claims parseAllClaimsFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(fetchSignInKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key fetchSignInKey(String SECRET_KEY){
        return new SecretKeySpec(SECRET_KEY.getBytes(),"HMACSHA256");
    }

    public boolean isTokenExpired(String jwtToken){
        Date expirationTime = parseSingleAttriubteFromToken(jwtToken,Claims::getExpiration);
        return expirationTime.before(new Date(System.currentTimeMillis()));
    }

    public String generateToken(UserDetails usrDetails){
        return Jwts.builder()
                .signWith(fetchSignInKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis()*1000*60*24))
                .setSubject(usrDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .compact();
    }

}
