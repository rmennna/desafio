package com.fourd.desafio.security.auth.controller;

import com.fourd.desafio.security.auth.requests.ActiveUserRequest;
import com.fourd.desafio.security.auth.requests.AuthenticationRequest;
import com.fourd.desafio.security.auth.requests.RegisterRequest;
import com.fourd.desafio.security.auth.response.AuthenticationResponse;
import com.fourd.desafio.security.auth.service.AuthenticationService;
import com.fourd.desafio.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Log4j2
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final DateUtil dateUtil;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<Void> register(@RequestBody RegisterRequest register) {
        log.info(("Register auth Method " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        this.authenticationService.register(register);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authentication) {
        log.info(("Authenticate auth Method " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return ResponseEntity.ok(this.authenticationService.authenticate(authentication));
    }

    @PostMapping("/active")
    public ResponseEntity<Void> active(@RequestBody ActiveUserRequest activeUserRequest) {
        log.info(("Active auth Method " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        this.authenticationService.active(activeUserRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
