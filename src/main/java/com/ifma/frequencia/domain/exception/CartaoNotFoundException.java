package com.ifma.frequencia.domain.exception;

import com.ifma.frequencia.domain.model.Cartao;

public class CartaoNotFoundException extends EntityNotFoundException {

    public CartaoNotFoundException(String msg) {
        super(Cartao.class, msg);
    }
}
