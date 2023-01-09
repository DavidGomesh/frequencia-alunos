package com.ifma.frequencia.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlunoRequest {

    @NotNull
    private Integer pessoa;

    @NotBlank
    private String matricula;
}
