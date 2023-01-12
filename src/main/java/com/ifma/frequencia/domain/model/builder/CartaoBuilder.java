package com.ifma.frequencia.domain.model.builder;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.Pessoa;

@Component
public class CartaoBuilder {

    protected Cartao cartao = new Cartao();

    public CartaoBuilder codigo(){
        return codigo("Xx Xx Xx");
    }

    public CartaoBuilder codigo(String codigo){
        cartao.setCodigo(codigo);
        return this;
    }
    
    public CartaoBuilder pessoa(Pessoa pessoa){
        cartao.setPessoa(pessoa);
        return this;
    }

    public CartaoBuilder init(){
        cartao = new Cartao();
        return this;
    }

    public Cartao build() {
        return cartao;
    }
}
