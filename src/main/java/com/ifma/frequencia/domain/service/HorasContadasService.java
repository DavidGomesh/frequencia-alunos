package com.ifma.frequencia.domain.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.model.HorasContadas;
import com.ifma.frequencia.domain.repository.HorasContadasRespository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HorasContadasService {
    
    private final HorasContadasRespository horasContadasRespository;

    public HorasContadas salvar(@Valid HorasContadas horasContadas){
        return horasContadasRespository.save(horasContadas);
    }
}
