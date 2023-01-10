package com.ifma.frequencia.api.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CartaoRequest {

    @NotBlank
    private String codigo;

    @NotNull
    private Integer pessoa;
}
