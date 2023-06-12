package com.fourd.desafio.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AulaPostRequestBody {

    @NotBlank(message = "o campo título não pode ser vazio")
    private String titulo;

    @NotBlank(message = "o campo descricao não pode ser vazio")
    private String descricao;

    private LocalDateTime dataPrevista;
}
