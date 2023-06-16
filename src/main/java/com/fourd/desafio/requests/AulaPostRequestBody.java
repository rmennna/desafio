package com.fourd.desafio.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AulaPostRequestBody {

    @NotBlank(message = "titulo can not be null")
    private String titulo;

    @NotBlank(message = "decricao can not be null")
    private String descricao;

    private LocalDateTime dataPrevista;

    @NotBlank(message = "id professor can not be null")
    private String idProfessor;
}
