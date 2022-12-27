package com.ifma.frequencia.domain.exception;

public class MicroDesconhecido extends RuntimeException {

    public MicroDesconhecido(Integer idMicro) {
        super("Microcontrolador {" + idMicro +"} desconhecido!");
    }
}
