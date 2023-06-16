package com.fourd.desafio.security.auth.service;

import com.fourd.desafio.exception.BadRequestException;
import com.fourd.desafio.repository.UserRepository;
import com.fourd.desafio.security.JWTService;
import com.fourd.desafio.security.auth.requests.AuthenticationRequest;
import com.fourd.desafio.security.auth.requests.RegisterRequest;
import com.fourd.desafio.security.auth.response.AuthenticationResponse;
import com.fourd.desafio.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest register) {
        Optional<User> existLogin = userRepository.findByLogin(register.getLogin());
        if(existLogin.isPresent()) throw new BadRequestException("Login Already Exists");

        var user = User.builder()
                .login(register.getLogin())
                .password(passwordEncoder.encode(register.getPassword()))
                .build();
        this.userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authentication) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authentication.getLogin(),
                        authentication.getPassword()
                )
        );
        var user = userRepository.findByLogin(authentication.getLogin()).orElseThrow(() -> new BadRequestException("User not found!"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
