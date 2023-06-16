package com.fourd.desafio.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfessorPostRequestBody {
    @NotBlank
    private String nome;
}
