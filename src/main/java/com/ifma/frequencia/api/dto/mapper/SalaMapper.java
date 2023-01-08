package com.ifma.frequencia.api.dto.mapper;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.api.dto.request.SalaRequest;
import com.ifma.frequencia.domain.model.Sala;

@Component
public class SalaMapper {
    
    public Sala toEntity(SalaRequest salaRequest) {
        Sala sala = new Sala();
        sala.setDescricao(salaRequest.getDescricao());
        return sala;
    }
}
