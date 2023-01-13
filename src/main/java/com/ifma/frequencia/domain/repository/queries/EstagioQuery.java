package com.ifma.frequencia.domain.repository.queries;

import java.util.Optional;

import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Estagio;

public interface EstagioQuery {
    
    public Optional<Estagio> buscarAtivosPorAluno(Aluno aluno);
}
