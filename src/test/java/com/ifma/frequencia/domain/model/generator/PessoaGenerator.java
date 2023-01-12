package com.ifma.frequencia.domain.model.generator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.model.builder.PessoaBuilder;
import com.ifma.frequencia.domain.repository.PessoaRepository;

@Component
public class PessoaGenerator extends PessoaBuilder {

    @Autowired
    private PessoaRepository pessoaRepository;
    private Set<Pessoa> pessoas = new HashSet<>();

    public PessoaGenerator valid() {
        pessoa = new Pessoa();
        nome();
        return this;
    }
    
    public PessoaGenerator invalid(){
        pessoa = new Pessoa();
        return this;
    }

    public PessoaGenerator persist(){
        pessoaRepository.save(pessoa);
        pessoas.add(pessoa);
        return this;
    }

    public void deleteAll() {
        pessoas.forEach(pessoaRepository::delete);
        pessoas.clear();
    }

}
