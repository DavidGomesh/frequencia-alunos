package com.ifma.frequencia.domain.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.exception.SalaNotFoundException;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.repository.SalaRepository;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class SalaService {
    
    private final SalaRepository salaRepository;

    public Sala salvar(@Valid Sala sala){
        return salaRepository.save(sala);
    }

    public Sala buscarPorId(@NonNull Integer idSala){
        return salaRepository.findById(idSala).orElseThrow(() -> {
            throw new SalaNotFoundException(idSala);
        });
    }
}
