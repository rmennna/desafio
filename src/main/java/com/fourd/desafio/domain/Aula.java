package com.fourd.desafio.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Aula {

    @Id
    private String id;

    private String titulo;

    private String descricao;

    private LocalDateTime dataPrevista;
}
