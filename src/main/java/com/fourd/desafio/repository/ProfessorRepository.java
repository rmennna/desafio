package com.fourd.desafio.repository;

import com.fourd.desafio.domain.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfessorRepository extends MongoRepository<Professor, String> {}
