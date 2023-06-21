package com.fourd.desafio.service;

import com.fourd.desafio.domain.Aula;
import com.fourd.desafio.domain.Professor;
import com.fourd.desafio.security.auth.domain.User;
import com.fourd.desafio.mapper.ProfessorMapper;
import com.fourd.desafio.repository.AulaRepository;
import com.fourd.desafio.repository.ProfessorRepository;
import com.fourd.desafio.requests.ProfessorPostRequestBody;
import com.fourd.desafio.requests.ProfessorPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final AulaRepository aulaRepository;
    public Professor create(ProfessorPostRequestBody request) {
        return this.professorRepository.save(ProfessorMapper.INSTANCE.toProfessor(request));
    }

    public Professor update(ProfessorPutRequestBody request) {
        return this.professorRepository.save(ProfessorMapper.INSTANCE.toProfessor(request));
    }

    public Page<Professor> listAll(Pageable pageable) {
        return this.professorRepository.findAll(pageable);
    }

    public Page<Aula> listAulasByProfessor(Authentication authentication, Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return this.aulaRepository.findAulasByIdProfessor(user.getIdProfessor(), pageable);
    }
}
