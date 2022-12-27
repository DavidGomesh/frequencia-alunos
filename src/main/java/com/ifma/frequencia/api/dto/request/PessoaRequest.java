package com.ifma.frequencia.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PessoaRequest {
    
    @NotBlank
    private String nome;
}
