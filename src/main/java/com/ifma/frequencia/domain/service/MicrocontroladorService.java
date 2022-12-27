package com.ifma.frequencia.domain.service;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.model.Microcontrolador;
import com.ifma.frequencia.domain.repository.MicrocontroladorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MicrocontroladorService {
    
    private final MicrocontroladorRepository microrRepository;

    public Microcontrolador salvar(Microcontrolador microcontrolador){
        return microrRepository.save(microcontrolador);
    }
}
