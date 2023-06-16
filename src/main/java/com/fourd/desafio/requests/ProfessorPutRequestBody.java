package com.fourd.desafio.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfessorPutRequestBody {
    @NotBlank
    private String nome;
}
