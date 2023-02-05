package com.ifma.frequencia.domain.service;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.model.Aluno;
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

    public LogLeitura salvar(Micro micro, Aluno aluno){
        LogLeitura log = construirLogLeitura(micro, aluno);
        return logLeituraRepository.save(log);
    }

    private LogLeitura construirLogLeitura(Micro micro, String codigo){
        LogLeitura log = new LogLeitura();
        adicionarMicro(log, micro);
        adicionarCartao(log, codigo);
        return log;
    }
    
    private LogLeitura construirLogLeitura(Micro micro, Aluno aluno) {

        LogLeitura log = new LogLeitura();
        adicionarMicro(log, micro);
        adicionarCartao(log, aluno);
        return log;
    }

    private void adicionarMicro(LogLeitura log, Micro micro){
        log.setMicro(micro.getIdMicro().toString());
        log.setTipoMicro(micro.getTipoMicro().toString());
        log.setModoOperacao(micro.getModoOperacao().toString());
        log.setLocalizacao(micro.getLocalizacao().getDescricao());
    }

    private void adicionarCartao(LogLeitura log, Aluno aluno){
        log.setCodigo(aluno.getCartao());
        log.setPessoa(aluno.getNome());
    }

    private void adicionarCartao(LogLeitura log, String codigo){
        log.setCodigo(codigo);
    }
}
