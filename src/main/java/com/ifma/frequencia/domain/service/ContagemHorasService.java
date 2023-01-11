package com.ifma.frequencia.domain.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.model.ContagemHoras;
import com.ifma.frequencia.domain.repository.ContagemHorasRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContagemHorasService {

    private final ContagemHorasRepository contagemHorasRepository;
    
    public ContagemHoras salvar(@Valid ContagemHoras contagemHoras){
        return contagemHorasRepository.save(contagemHoras);
    }
}
