package com.ifma.frequencia.domain.repository.queries;

import java.util.Optional;

import com.ifma.frequencia.domain.model.Aluno;

public interface AlunoQuery {
    
    public Optional<Aluno> buscarPorCodigoCartao(String codigo);
}
