package com.ifma.frequencia.api.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SalaRequest {

    @NotBlank
    private String descricao;
}
