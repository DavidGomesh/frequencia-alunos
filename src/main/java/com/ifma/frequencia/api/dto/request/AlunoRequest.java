package com.ifma.frequencia.api.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AlunoRequest {

    @NotBlank
    private String nome;

    @NotNull
    private Integer curso;
    
    @NotNull
    private String cartao;
}
