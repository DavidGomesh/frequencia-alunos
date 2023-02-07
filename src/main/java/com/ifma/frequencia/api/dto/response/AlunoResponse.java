package com.ifma.frequencia.api.dto.response;

import lombok.Data;

@Data
public class AlunoResponse {
    private Integer idAluno;
    private String nome;
    private String curso;
    private String cartao;
}
