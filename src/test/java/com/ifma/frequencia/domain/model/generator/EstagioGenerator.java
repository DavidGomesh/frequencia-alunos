package com.ifma.frequencia.domain.model.generator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.builder.EstagioBuilder;
import com.ifma.frequencia.domain.repository.EstagioRespository;

@Component
public class EstagioGenerator extends EstagioBuilder {
    
    @Autowired
    private AlunoGenerator alunoGenerator;
    
    @Autowired
    private EstagioRespository estagioRespository;
    private Set<Estagio> estagios = new HashSet<>();

    public EstagioGenerator valid(){
        estagio = new Estagio();
        aluno().ativo();
        return this;
    }

    public EstagioGenerator invalid(){
        estagio = new Estagio();
        return this;
    }

    public EstagioGenerator persist(){
        estagioRespository.save(estagio);
        estagios.add(estagio);
        return this;
    }

    public void deleteAll(){
        estagios.forEach(estagioRespository::delete);
        estagios.clear();
        alunoGenerator.deleteAll();
    }

    public EstagioGenerator aluno(){
        aluno(alunoGenerator.valid().persist().build());
        return this;
    }
}
