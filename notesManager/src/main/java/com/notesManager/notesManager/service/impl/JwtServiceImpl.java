package com.notesManager.notesManager.service.impl;

import com.notesManager.notesManager.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Service
@Log4j2
public class JwtServiceImpl implements JwtService {
    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Key getsignKey(){
        log.info("getsignKey in JwtServiceImpl class of project AddProduct");
        byte[] key = Decoders.BASE64.decode("eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE2OTc5NTkyNDcsImV4cCI6MTY5Nzk2MDY4N30");
        log.info("generated signin key in AddProduct : "+ key);
        return Keys.hmacShaKeyFor(key);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers){
        final Claims claims = extratAllClaims(token);
        return claimsResolvers.apply(claims);
    }
    private Claims extratAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getsignKey()).build().parseClaimsJws(token).getBody();
    }
}
