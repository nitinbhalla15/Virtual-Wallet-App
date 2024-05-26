package com.project.paytm.authentication.auth_controller;

import com.project.paytm.authentication.auth_service.AuthenticationService;
import com.project.paytm.authentication.login_entity.LoginRequest;
import com.project.paytm.db_entities.UserEntity;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;


    @PostMapping(value = "/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody UserEntity userDetails){
        return ResponseEntity.ok().body(authService.registerUser(userDetails));
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<Object> sigin(@Valid @RequestBody LoginRequest loginDetails){
        return ResponseEntity.ok().body(authService.signIn(loginDetails));
    }



}
