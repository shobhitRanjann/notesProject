package com.notesManager.notesManager.controller;

import com.notesManager.notesManager.dto.JWTAuthenticationResponse;
import com.notesManager.notesManager.dto.SignInRequest;
import com.notesManager.notesManager.dto.SignUpRequest;
import com.notesManager.notesManager.entity.User;
import com.notesManager.notesManager.service.AuthenticationService;
import com.notesManager.notesManager.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
@Log4j2
public class HomeController {

    @Autowired
   private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest){
        JWTAuthenticationResponse authenticationRes = authenticationService.signIn(signInRequest);
        log.info("in signIn function of UserDataControl AuthenticationController >>> "+ authenticationRes);
        if(authenticationRes == null){
            log.info("in AuthenticationController signIn method , sending user is null");
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(authenticationRes);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest){
        log.info("in signup request");
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/getuserid")
    public long getUserIdByUsername(@RequestParam("username") String username){
        log.info("calling controller getUserIdByUsername with username : "+username);
        return userService.getUserIdByUsername(username);
    }

}
