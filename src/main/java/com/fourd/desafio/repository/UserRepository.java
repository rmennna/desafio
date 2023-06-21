package com.fourd.desafio.repository;

import com.fourd.desafio.security.auth.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByLogin(String login);
}
