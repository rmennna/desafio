package com.fourd.desafio.service;

import com.fourd.desafio.domain.Usuario;
import com.fourd.desafio.exception.BadRequestException;
import com.fourd.desafio.mapper.UsuarioMapper;
import com.fourd.desafio.repository.UsuarioRepository;
import com.fourd.desafio.requests.UsuarioPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    public Usuario save(UsuarioPostRequestBody usuarioPostRequestBody){
        Optional<Usuario> existLogin = usuarioRepository.findByLogin(usuarioPostRequestBody.getLogin());
        if(existLogin.isPresent()) throw new BadRequestException("Login Already Exists");
        usuarioPostRequestBody.setPassword(passwordEncoder.encode(usuarioPostRequestBody.getPassword()));
        return this.usuarioRepository.save(UsuarioMapper.INSTANCE.toUsuario(usuarioPostRequestBody));
    }

    public Page<Usuario> findAll(Pageable pageable) {
        return this.usuarioRepository.findAll(pageable);
    }

    public void replace() {}
}
