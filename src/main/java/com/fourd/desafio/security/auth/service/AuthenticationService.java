package com.fourd.desafio.security.auth.service;

import com.fourd.desafio.exception.BadRequestException;
import com.fourd.desafio.repository.UserRepository;
import com.fourd.desafio.security.JWTService;
import com.fourd.desafio.security.auth.requests.ActiveUserRequest;
import com.fourd.desafio.security.auth.requests.AuthenticationRequest;
import com.fourd.desafio.security.auth.requests.RegisterRequest;
import com.fourd.desafio.security.auth.response.AuthenticationResponse;
import com.fourd.desafio.security.auth.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public void register(RegisterRequest register) {
        Optional<User> existLogin = userRepository.findUserByLogin(register.getLogin());
        if(existLogin.isPresent()) throw new BadRequestException("Login Already Exists");

        var user = User.builder()
                .login(register.getLogin())
                .password(passwordEncoder.encode(register.getPassword()))
                .idProfessor(null)
                .status("AGUARDANDO_APROVAÇÃO")
                .build();
        this.userRepository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authentication) {
        User user = userRepository.findUserByLogin(authentication.getLogin()).orElseThrow(() -> new BadRequestException("User not found!"));
        if(user.getStatus().equals("AGUARDANDO_APROVAÇÃO")) throw new BadRequestException("user is not activated");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authentication.getLogin(),
                        authentication.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void active(ActiveUserRequest activeUserRequest) {
        Optional<User> existLogin = userRepository.findUserByLogin(activeUserRequest.getLogin());
        if(!existLogin.isPresent()) throw new BadRequestException("Login not found!");
        User user = existLogin.get();
        user.setIdProfessor(activeUserRequest.getIdProfessor());
        user.setStatus("APROVADO");
        this.userRepository.save(user);
    }
}
