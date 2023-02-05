package com.ifma.frequencia.domain.exception;

import com.ifma.frequencia.domain.model.Aluno;

public class AlunoNotFoundException extends EntityNotFoundException {

    public AlunoNotFoundException(Integer idAluno) {
        super(Aluno.class, idAluno);
    }
    
}
