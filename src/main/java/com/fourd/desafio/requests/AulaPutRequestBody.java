package com.fourd.desafio.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AulaPutRequestBody {

    private String id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataPrevista;
}
