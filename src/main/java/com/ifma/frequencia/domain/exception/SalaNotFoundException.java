package com.ifma.frequencia.domain.exception;

import com.ifma.frequencia.domain.model.Sala;

public class SalaNotFoundException extends EntityNotFoundException {

    public SalaNotFoundException(Integer idSala) {
        super(Sala.class, idSala);
    }
    
}
