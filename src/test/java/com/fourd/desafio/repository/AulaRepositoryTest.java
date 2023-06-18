package com.fourd.desafio.repository;

import com.fourd.desafio.domain.Aula;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@DisplayName("Tests for Anime Repository")
class AulaRepositoryTest {

    @Autowired
    private AulaRepository aulaRepository;

    @Test
    @DisplayName("Save persists aula when successful")
    void save_PersistAula_WhenSuccessful() {
        Aula aulaToBeSaved = createAula();
        Aula aulaSaved = this.aulaRepository.save(aulaToBeSaved);

        Assertions.assertThat(aulaSaved).isNotNull();
        Assertions.assertThat(aulaSaved.getTitulo()).isNotNull();
        Assertions.assertThat(aulaSaved.getTitulo()).isEqualTo(aulaToBeSaved.getTitulo());
        Assertions.assertThat(aulaSaved.getDescricao()).isNotNull();
        Assertions.assertThat(aulaSaved.getDescricao()).isEqualTo(aulaToBeSaved.getDescricao());
        Assertions.assertThat(aulaSaved.getIdProfessor()).isNotNull();
    }

    @Test
    @DisplayName("Save updates aula when successful")
    void save_UpdateAula_WhenSuccessful() {
        Aula aulaToBeSaved = createAula();
        Aula aulaSaved = this.aulaRepository.save(aulaToBeSaved);

        aulaSaved.setTitulo("Aula 01 update");
        aulaSaved.setDescricao("Descrição Aula 01 update");
        Aula aulaUpdate = this.aulaRepository.save(aulaSaved);

        Assertions.assertThat(aulaUpdate).isNotNull();
        Assertions.assertThat(aulaUpdate.getTitulo()).isNotNull();
        Assertions.assertThat(aulaUpdate.getTitulo()).isEqualTo(aulaSaved.getTitulo());
        Assertions.assertThat(aulaUpdate.getDescricao()).isNotNull();
        Assertions.assertThat(aulaUpdate.getDescricao()).isEqualTo(aulaSaved.getDescricao());
        Assertions.assertThat(aulaUpdate.getIdProfessor()).isNotNull();
    }

    private Aula createAula() {
        return Aula.builder()
                .titulo("Aula 01")
                .descricao("Descrição Aula 01")
                .dataPrevista(new Date())
                .idProfessor("123456789")
                .build();
    }

}