package com.ifma.frequencia.domain.model.generator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.LogLeitura;
import com.ifma.frequencia.domain.model.builder.LogLeituraBuilder;
import com.ifma.frequencia.domain.repository.LogLeituraRepository;

@Component
public class LogLeituraGenerator extends LogLeituraBuilder {
    
    @Autowired
    private LogLeituraRepository logRepository;
    private Set<LogLeitura> logs = new HashSet<>();

    public LogLeituraGenerator valid() {
        log = new LogLeitura();
        dataHora().micro().tipoMicro().modoOperacao().localizacao().codigo().pessoa();
        return this;
    }

    public LogLeituraGenerator invalid() {
        log = new LogLeitura();
        return this;
    }

    public LogLeituraGenerator persist() {
        logRepository.save(log);
        logs.add(log);
        return this;
    }

    public void deleteAll() {
        logs.forEach(logRepository::delete);
        logs.clear();
    }
}
