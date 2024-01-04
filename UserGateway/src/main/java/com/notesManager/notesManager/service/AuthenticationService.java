package com.notesManager.notesManager.service;

import com.notesManager.notesManager.dto.JWTAuthenticationResponse;
import com.notesManager.notesManager.dto.SignInRequest;
import com.notesManager.notesManager.dto.SignUpRequest;
import com.notesManager.notesManager.entity.User;

public interface AuthenticationService {
    public JWTAuthenticationResponse signIn(SignInRequest signInRequest);
    User signUp(SignUpRequest signUpRequest);
}
