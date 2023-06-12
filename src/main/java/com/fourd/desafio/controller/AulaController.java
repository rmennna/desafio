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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("aulas")
@Log4j2
@RequiredArgsConstructor
public class AulaController {

    private final DateUtil dateUtil;
    private final AulaService aulaService;
    @GetMapping
    public ResponseEntity<Page<Aula>> save(Pageable pageable){
        log.info(("FindAll Aula Method " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return ResponseEntity.ok(this.aulaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> findById(@PathVariable String id){
        log.info(("FindById Aula Method" + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return ResponseEntity.ok(this.aulaService.findByIdOrThrowBaRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Aula> save(@RequestBody @Valid AulaPostRequestBody aulaPostRequestBody) {
        log.info(("Save Aula Method " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return new ResponseEntity<>(this.aulaService.save(aulaPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AulaPutRequestBody aulaPutRequestBody){
        log.info(("Put Aula Method" + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        this.aulaService.replace(aulaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
