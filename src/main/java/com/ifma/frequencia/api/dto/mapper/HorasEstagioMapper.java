package com.ifma.frequencia.api.dto.mapper;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.api.dto.request.HorasEstagioRequest;
import com.ifma.frequencia.domain.model.HorasEstagio;

@Component
public class HorasEstagioMapper {
    
    public HorasEstagio toEntity(HorasEstagioRequest horasEstagioRequest){

        HorasEstagio horasEstagio = new HorasEstagio();
        horasEstagio.setHoraInicio(horasEstagioRequest.getHoraInicio());
        horasEstagio.setHoraFim(horasEstagioRequest.getHoraFim());

        return horasEstagio;
    }
}
