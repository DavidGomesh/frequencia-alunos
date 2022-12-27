package com.ifma.frequencia.domain.service;

import java.util.Optional;

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

    public Optional<Sala> buscarPorId(Integer idSala){
        return salaRepository.findById(idSala);
    }
}
