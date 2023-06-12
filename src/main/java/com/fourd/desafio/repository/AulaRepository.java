package com.fourd.desafio.repository;

import com.fourd.desafio.domain.Aula;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AulaRepository extends MongoRepository<Aula, String> {
}
