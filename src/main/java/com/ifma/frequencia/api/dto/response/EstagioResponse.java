package com.ifma.frequencia.api.dto.response;

import com.ifma.frequencia.domain.utils.Duracao;

import lombok.Data;

@Data
public class EstagioResponse {
    private AlunoResponse aluno;
    private Duracao horasTotais;
}
