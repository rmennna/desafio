package com.fourd.desafio.service;

import com.fourd.desafio.domain.Aula;
import com.fourd.desafio.exception.BadRequestException;
import com.fourd.desafio.mapper.AulaMapper;
import com.fourd.desafio.repository.AulaRepository;
import com.fourd.desafio.requests.AulaPostRequestBody;
import com.fourd.desafio.requests.AulaPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository aularepository;

    public Page<Aula> findAll(Pageable pageable) {
        return this.aularepository.findAll(pageable);
    }

    public Aula save(AulaPostRequestBody aulaPostRequestBody) {
        return this.aularepository.save(AulaMapper.INSTANCE.toAula(aulaPostRequestBody));
    }

    public Aula findByIdOrThrowBaRequestException(String id){
        return this.aularepository.findById(id).orElseThrow(() -> new BadRequestException("Aula not Found"));
    }

    public void replace(AulaPutRequestBody aulaPutRequestBody) {
        Aula savedAula = findByIdOrThrowBaRequestException(aulaPutRequestBody.getId());
        Aula aula = AulaMapper.INSTANCE.toAula(aulaPutRequestBody);
        aula.setId(savedAula.getId());
        this.aularepository.save(aula);
    }
}
