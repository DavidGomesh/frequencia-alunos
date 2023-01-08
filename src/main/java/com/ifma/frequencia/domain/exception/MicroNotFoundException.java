package com.ifma.frequencia.domain.exception;

import com.ifma.frequencia.domain.model.Micro;

public class MicroNotFoundException extends EntityNotFoundException {

    public MicroNotFoundException(Integer idMicro) {
        super(Micro.class, idMicro);
    }
}
