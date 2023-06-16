package com.fourd.desafio.requests;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class AulaPostRequestBody {

    @NotBlank(message = "titulo can not be null or empty")
    private String titulo;

    @NotBlank(message = "decricao can not be null or empty")
    private String descricao;

    @FutureOrPresent
    private Date dataPrevista;

    @NotBlank(message = "id professor can not be null or empty")
    private String idProfessor;
}
