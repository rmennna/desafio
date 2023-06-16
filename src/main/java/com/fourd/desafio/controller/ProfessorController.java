package com.fourd.desafio.controller;

import com.fourd.desafio.domain.Aula;
import com.fourd.desafio.domain.Professor;
import com.fourd.desafio.domain.User;
import com.fourd.desafio.requests.ProfessorPostRequestBody;
import com.fourd.desafio.requests.ProfessorPutRequestBody;
import com.fourd.desafio.service.ProfessorService;
import com.fourd.desafio.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;
    private final DateUtil dateUtil;

    @GetMapping("/aulas")
    public ResponseEntity<Page<Aula>> listAulasByProfessor(@CurrentSecurityContext(expression = "authentication")Authentication authentication, Pageable pageable) {
        log.info(("Aulas Professor Method " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return ResponseEntity.ok(this.professorService.listAulasByProfessor(authentication, pageable));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Professor>> ListAll(Pageable pageable) {
        log.info(("List Professor Method " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return ResponseEntity.ok(this.professorService.listAll(pageable));
    }

    @PostMapping("/create")
    public ResponseEntity<Professor> create(@RequestBody @Valid ProfessorPostRequestBody request){
        log.info(("Create Professor Method " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return new ResponseEntity<>(this.professorService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Professor> update(@RequestBody @Valid ProfessorPutRequestBody request) {
        log.info(("Update Professor Method" + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return new ResponseEntity<>(this.professorService.update(request), HttpStatus.OK);
    }
}
