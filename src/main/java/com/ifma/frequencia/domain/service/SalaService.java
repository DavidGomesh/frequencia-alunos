package com.ifma.frequencia.domain.service;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.repository.SalaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SalaService {
    
    private final SalaRepository salaRepository;

    public Sala salvar(Sala sala){
        return salaRepository.save(sala);
    }
}
