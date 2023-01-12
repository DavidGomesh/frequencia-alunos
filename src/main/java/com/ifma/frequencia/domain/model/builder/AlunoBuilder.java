package com.ifma.frequencia.domain.model.builder;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Pessoa;

@Component
public class AlunoBuilder {

    protected Aluno aluno = new Aluno();

    public AlunoBuilder pessoa(Pessoa pessoa){
        aluno.setPessoa(pessoa);
        return this;
    }

    public AlunoBuilder matricula(){
        return matricula("20232SI0001");
    }

    public AlunoBuilder matricula(String matricula){
        aluno.setMatricula(matricula);
        return this;
    }

    public AlunoBuilder init(){
        aluno = new Aluno();
        return this;
    }

    public Aluno build() {
        return aluno;
    }
}
