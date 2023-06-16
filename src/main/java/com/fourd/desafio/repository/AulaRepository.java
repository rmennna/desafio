package com.fourd.desafio.repository;

import com.fourd.desafio.domain.Aula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AulaRepository extends MongoRepository<Aula, String> {
    Page<Aula> findByIdProfessor(String idProfessor, Pageable pageable);
}
