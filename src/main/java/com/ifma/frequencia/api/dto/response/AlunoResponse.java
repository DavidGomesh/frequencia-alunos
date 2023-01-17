package com.ifma.frequencia.api.dto.response;

import lombok.Data;

@Data
public class AlunoResponse {
    private PessoaResponse pessoa;
    private String matricula;
}
