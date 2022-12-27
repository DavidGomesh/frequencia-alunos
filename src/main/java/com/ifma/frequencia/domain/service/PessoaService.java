package com.ifma.frequencia.domain.service;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.exception.PessoaNotFoundException;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.repository.PessoaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    
    public Pessoa salvar(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public Pessoa buscarPorId(Integer idPessoa){
        return pessoaRepository.findById(idPessoa).orElseThrow(() -> {
            throw new PessoaNotFoundException(idPessoa);
        });
    }
}
