package com.ifma.frequencia.domain.model.generator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.builder.CartaoBuilder;
import com.ifma.frequencia.domain.repository.CartaoRepository;

@Component
public class CartaoGenerator extends CartaoBuilder {

    @Autowired
    private PessoaGenerator pessoaGenerator;

    @Autowired
    private CartaoRepository cartaoRepository;
    private Set<Cartao> cartoes = new HashSet<>();

    public CartaoGenerator valid() {
        cartao = new Cartao();
        pessoa().codigo();
        return this;
    }

    public CartaoGenerator invalid() {
        cartao = new Cartao();
        return this;
    }

    public CartaoGenerator persist() {
        cartaoRepository.save(cartao);
        cartoes.add(cartao);
        return this;
    }

    public void deleteAll() {
        cartoes.forEach(cartaoRepository::delete);
        cartoes.clear();
        pessoaGenerator.deleteAll();
    }

    public CartaoGenerator pessoa(){
        pessoa(pessoaGenerator.valid().persist().build());
        return this;
    }

}
