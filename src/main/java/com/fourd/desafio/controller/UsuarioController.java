package com.fourd.desafio.controller;

import com.fourd.desafio.domain.Usuario;
import com.fourd.desafio.repository.UsuarioRepository;
import com.fourd.desafio.requests.UsuarioPostRequestBody;
import com.fourd.desafio.service.UsuarioService;
import com.fourd.desafio.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@Log4j2
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final DateUtil dateUtil;

    @GetMapping("/listAll")
    public ResponseEntity<Page<Usuario>> listarTodos(Pageable pageable) {
        log.info(("FindAll Usuario Method " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return ResponseEntity.ok(this.usuarioService.findAll(pageable));
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> save(@RequestBody @Valid UsuarioPostRequestBody usuarioPostRequestBody) {
        log.info(("Save Usuario Method " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return new ResponseEntity<>(this.usuarioService.save(usuarioPostRequestBody), HttpStatus.CREATED);
    }
}
