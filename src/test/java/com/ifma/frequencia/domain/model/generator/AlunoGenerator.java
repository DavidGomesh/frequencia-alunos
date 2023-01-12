package com.ifma.frequencia.domain.model.generator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.builder.AlunoBuilder;
import com.ifma.frequencia.domain.repository.AlunoRepository;

@Component
public class AlunoGenerator extends AlunoBuilder {

    @Autowired
    protected PessoaGenerator pessoaGenerator;

    @Autowired
    private AlunoRepository alunoRepository;
    private Set<Aluno> alunos = new HashSet<>();

    public AlunoGenerator valid() {
        aluno = new Aluno();
        pessoa().matricula();
        return this;
    }

    public AlunoGenerator invalid() {
        aluno = new Aluno();
        return this;
    }

    public AlunoGenerator persist() {
        alunoRepository.save(aluno);
        alunos.add(aluno);
        return this;
    }
    
    public void deleteAll() {
        alunos.forEach(alunoRepository::delete);
        alunos.clear();
        pessoaGenerator.deleteAll();
    }

    public AlunoGenerator pessoa(){
        pessoa(pessoaGenerator.valid().persist().build());
        return this;
    }
}
