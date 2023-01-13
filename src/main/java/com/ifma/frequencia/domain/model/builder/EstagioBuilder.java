package com.ifma.frequencia.domain.model.builder;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Estagio;

@Component
public class EstagioBuilder {
    
    protected Estagio estagio = new Estagio();

    public EstagioBuilder aluno(Aluno aluno){
        estagio.setAluno(aluno);
        return this;
    }

    public EstagioBuilder ativo(){
        return ativo(true);
    }

    public EstagioBuilder ativo(Boolean ativo){
        estagio.setAtivo(ativo);
        return this;
    }

    public EstagioBuilder init(){
        estagio = new Estagio();
        return this;
    }

    public Estagio build(){
        return estagio;
    }
}
