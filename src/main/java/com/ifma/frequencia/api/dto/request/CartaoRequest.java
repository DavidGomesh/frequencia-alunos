package com.ifma.frequencia.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartaoRequest {

    @NotBlank
    private String codigo;

    @NotNull
    private Integer pessoa;
}
