package com.fourd.desafio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Aula {

    @Id
    private String id;

    private String titulo;

    private String descricao;

    private Date dataPrevista;

    private String idProfessor;
}
