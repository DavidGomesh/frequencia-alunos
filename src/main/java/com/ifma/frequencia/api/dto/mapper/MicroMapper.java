package com.ifma.frequencia.api.dto.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.api.dto.request.MicroRequest;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.repository.SalaRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MicroMapper {

    private final SalaRepository salaRepository;
    
    public Micro toEntity(MicroRequest microRequest){
        
        Optional<Sala> optSala = salaRepository.findById(microRequest.getLocalizacao());
        Sala sala = optSala.orElseThrow();
        
        Micro micro = new Micro();
        micro.setTipoMicro(microRequest.getTipoMicro());
        micro.setLocalizacao(sala);

        return micro;
    }
}
