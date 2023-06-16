package com.fourd.desafio.service;

import com.fourd.desafio.domain.Professor;
import com.fourd.desafio.mapper.ProfessorMapper;
import com.fourd.desafio.repository.ProfessorRepository;
import com.fourd.desafio.requests.ProfessorPostRequestBody;
import com.fourd.desafio.requests.ProfessorPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    public Professor create(ProfessorPostRequestBody request) {
        return this.professorRepository.save(ProfessorMapper.INSTANCE.toProfessor(request));
    }

    public Professor update(ProfessorPutRequestBody request) {
        return this.professorRepository.save(ProfessorMapper.INSTANCE.toProfessor(request));
    }

    public Page<Professor> listAll(Pageable pageable) {
        return this.professorRepository.findAll(pageable);
    }
}
