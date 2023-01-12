package com.ifma.frequencia.domain.model.builder;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.Sala;

@Component
public class SalaBuilder {

    protected Sala sala = new Sala();

    public SalaBuilder descricao(){
        return descricao("P1S09");
    }

    public SalaBuilder descricao(String descricao){
        sala.setDescricao(descricao);
        return this;
    }

    public SalaBuilder init(){
        sala = new Sala();
        return this;
    }

    public Sala build() {
        return sala;
    }
}
