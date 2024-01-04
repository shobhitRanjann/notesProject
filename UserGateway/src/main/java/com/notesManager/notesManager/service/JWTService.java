package com.notesManager.notesManager.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JWTService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    public void validateToken(String token);

    String generateRefreshToken(HashMap<String, Object> extraClaims, UserDetails userDetails);
}
