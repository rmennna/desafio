package com.fourd.desafio.security.auth.controller;

import com.fourd.desafio.security.auth.requests.AuthenticationRequest;
import com.fourd.desafio.security.auth.requests.RegisterRequest;
import com.fourd.desafio.security.auth.response.AuthenticationResponse;
import com.fourd.desafio.security.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest register) {
        return ResponseEntity.ok(this.authenticationService.register(register));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authentication) {
        return ResponseEntity.ok(this.authenticationService.authenticate(authentication));
    }
}
