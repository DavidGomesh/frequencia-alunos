package com.ifma.frequencia.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.api.dto.response.LogLeituraResponse;
import com.ifma.frequencia.domain.model.LogLeitura;

@Component
public class LogLeituraMapper {

    public LogLeituraResponse toResponse(LogLeitura log){
        LogLeituraResponse logResponse = new LogLeituraResponse();
        logResponse.setDataHora(log.getDataHora());
        logResponse.setMicro(log.getMicro());
        logResponse.setTipoMicro(log.getTipoMicro());
        logResponse.setModoOperacao(log.getModoOperacao());
        logResponse.setLocalizacao(log.getLocalizacao());
        logResponse.setCodigo(log.getCodigo());
        logResponse.setPessoa(log.getPessoa());
        return logResponse;
    }

    public List<LogLeituraResponse> toResponseList(List<LogLeitura> logs){
        return (logs.stream()
            .map(this::toResponse)
            .collect(Collectors.toList())
        );
    }
}
