package com.ifma.frequencia.domain.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.repository.EstagioRespository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstagioService {

    private final EstagioRespository estagioRespository;
    
    @Transactional
    public Estagio salvar(@Valid Estagio estagio){
        return estagioRespository.save(estagio);
    }
}
