package com.ifma.frequencia.domain.exception;

import com.ifma.frequencia.domain.model.Pessoa;

public class PessoaNotFoundException extends EntityNotFoundException {

    public PessoaNotFoundException(Integer idPessoa) {
        super(Pessoa.class, idPessoa);
    }
}
