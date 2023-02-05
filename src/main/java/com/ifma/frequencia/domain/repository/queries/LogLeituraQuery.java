package com.ifma.frequencia.domain.repository.queries;

import java.util.List;

import com.ifma.frequencia.domain.model.LogLeitura;

public interface LogLeituraQuery {
    List<LogLeitura> buscarUltimos();
}
