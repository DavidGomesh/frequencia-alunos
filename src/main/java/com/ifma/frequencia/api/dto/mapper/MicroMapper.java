package com.ifma.frequencia.api.dto.mapper;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.api.dto.request.MicroRequest;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.service.SalaService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MicroMapper {

    private final SalaService salaService;
    
    public Micro toEntity(MicroRequest microRequest){
        
        Micro micro = new Micro();
        micro.setTipoMicro(microRequest.getTipoMicro());

        if(microRequest.getLocalizacao() != null){
            Sala sala = salaService.buscarPorId(microRequest.getLocalizacao()).orElseThrow();
            micro.setLocalizacao(sala);
        }

        return micro;
    }
}
