package com.notesManager.notesManager.service.impl;

import com.notesManager.notesManager.dto.JWTAuthenticationResponse;
import com.notesManager.notesManager.dto.SignInRequest;
import com.notesManager.notesManager.dto.SignUpRequest;
import com.notesManager.notesManager.entity.Role;
import com.notesManager.notesManager.entity.User;
import com.notesManager.notesManager.repository.UsersRepository;
import com.notesManager.notesManager.service.AuthenticationService;
import com.notesManager.notesManager.service.JWTService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
@Log4j2
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Override
    public JWTAuthenticationResponse signIn(SignInRequest signInRequest) {

        var user1 = userRepository.findByEmail(signInRequest.getEmail());
        if(user1.isEmpty()){
            return null;
        }
        if(!passwordCheck(signInRequest.getPassword(), user1.get().getPassword())){
            log.info("user is null in signIn AuthenticationServiceImpl");
            return null;
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                signInRequest.getPassword()));
        System.out.println("after authenticationManager  " +authenticationManager);
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid Username or Password!"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public User signUp(SignUpRequest signUpRequest){
        log.info("in signup service");
        if(userRepository.findByEmail(signUpRequest.getUserName()).isPresent()){
            return new User();
        }
        User allUsersDetails = new User();
        allUsersDetails.setFirstName(signUpRequest.getFirstName());
        allUsersDetails.setLastName(signUpRequest.getLastName());
        allUsersDetails.setEmail(signUpRequest.getUserName());
        allUsersDetails.setRole(Role.USER);
        allUsersDetails.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        allUsersDetails.setDate(new Date(System.currentTimeMillis()));
        log.info("in signup service end");
        return userRepository.save(allUsersDetails);
    }

    public boolean passwordCheck(String password, String encodedPassword){
        log.info(password,"   kk    ", encodedPassword);
        log.info("password check "+passwordEncoder.matches(password, encodedPassword));
        return passwordEncoder.matches(password, encodedPassword);
    }
}
