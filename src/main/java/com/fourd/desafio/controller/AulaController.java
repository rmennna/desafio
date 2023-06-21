package com.fourd.desafio.controller;

import com.fourd.desafio.domain.Aula;
import com.fourd.desafio.requests.AulaPostRequestBody;
import com.fourd.desafio.requests.AulaPutRequestBody;
import com.fourd.desafio.service.AulaService;
import com.fourd.desafio.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/aula")
@Log4j2
@RequiredArgsConstructor
public class AulaController {

    private final DateUtil dateUtil;
    private final AulaService aulaService;
    @GetMapping("/list")
    public ResponseEntity<Page<Aula>> save(Pageable pageable){
        log.info(("List Aula Method " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return ResponseEntity.ok(this.aulaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> findById(@PathVariable String id){
        log.info(("Find by id Aula Method" + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return ResponseEntity.ok(this.aulaService.findByIdOrThrowBaRequestException(id));
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<Aula> create(@RequestBody @Valid AulaPostRequestBody aulaPostRequestBody) {
        log.info(("Create Aula Method " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return new ResponseEntity<>(this.aulaService.save(aulaPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<Void> replace(@RequestBody AulaPutRequestBody aulaPutRequestBody){
        log.info(("Update Aula Method" + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        this.aulaService.replace(aulaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
