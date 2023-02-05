package com.ifma.frequencia.domain.exception;

public class HoraFinalNotDefinedException extends RuntimeException {

    public HoraFinalNotDefinedException() {
        super("Hora de Finalização não definida!");
    }
    
}
