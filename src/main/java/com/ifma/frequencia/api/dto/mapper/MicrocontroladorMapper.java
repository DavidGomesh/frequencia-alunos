package com.ifma.frequencia.api.dto.mapper;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.api.dto.request.MicrocontroladorRequest;
import com.ifma.frequencia.domain.model.Microcontrolador;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.service.SalaService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MicrocontroladorMapper {

    private final SalaService salaService;
    
    public Microcontrolador toEntity(MicrocontroladorRequest microRequest){
        
        Microcontrolador micro = new Microcontrolador();
        micro.setTipoMicro(microRequest.getTipoMicro());

        if(microRequest.getLocalizacao() != null){
            Sala sala = salaService.buscarPorId(microRequest.getLocalizacao()).orElseThrow();
            micro.setLocalizacao(sala);
        }

        return micro;
    }
}
