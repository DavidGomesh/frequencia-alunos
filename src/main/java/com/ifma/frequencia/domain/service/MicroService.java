package com.ifma.frequencia.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.repository.MicroRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MicroService {
    
    private final MicroRepository microrRepository;

    public Micro salvar(Micro microcontrolador){
        return microrRepository.save(microcontrolador);
    }

    public Optional<Micro> buscarPorId(Integer idMicro){
        return microrRepository.findById(idMicro);
    }
}
