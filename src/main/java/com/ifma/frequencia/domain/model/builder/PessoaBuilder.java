package com.ifma.frequencia.domain.model.builder;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.Pessoa;

@Component
public class PessoaBuilder {
    
    protected Pessoa pessoa = new Pessoa();

    public PessoaBuilder nome(){
        return nome("David Gomesh");
    }

    public PessoaBuilder nome(String nome){
        pessoa.setNome(nome);
        return this;
    }

    public PessoaBuilder init(){
        pessoa = new Pessoa();
        return this;
    }

    public Pessoa build(){
        return pessoa;
    }
}
