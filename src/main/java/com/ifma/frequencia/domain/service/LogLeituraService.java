package com.ifma.frequencia.domain.service;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.model.LogLeitura;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.repository.LogLeituraRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LogLeituraService {

    private final LogLeituraRepository logLeituraRepository;
    
    public LogLeitura salvar(Micro micro, String codigo){
        LogLeitura log = construirLogLeitura(micro, codigo);
        return logLeituraRepository.save(log);
    }
    
    private LogLeitura construirLogLeitura(Micro micro, String codigo){

        LogLeitura log = new LogLeitura();
        log.setMicro(micro.getIdMicro().toString());
        log.setTipoMicro(micro.getTipoMicro().toString());
        log.setModoOperacao(micro.getModoOperacao().toString());
        log.setCodigo(codigo);

        return log;
    }
}
