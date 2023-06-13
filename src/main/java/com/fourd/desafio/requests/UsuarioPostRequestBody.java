package com.fourd.desafio.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioPostRequestBody {

    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
