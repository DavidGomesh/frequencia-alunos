package com.ifma.frequencia.domain.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> classe, Integer id) {
        super("Entidade { " + classe.getSimpleName() + "} encontrada! ID: " + id);
    }

    public EntityNotFoundException(Class<?> classe, String msg) {
        super("Entidade { " + classe.getSimpleName() + "} encontrada! " + msg);
    }
    
}
