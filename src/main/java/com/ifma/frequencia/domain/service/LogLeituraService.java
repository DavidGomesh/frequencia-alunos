package com.ifma.frequencia.domain.service;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.model.Cartao;
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
        return salvar(log);
    }

    public LogLeitura salvar(Micro micro, Cartao cartao){
        LogLeitura log = construirLogLeitura(micro, cartao);
        return salvar(log);
    }
    
    private LogLeitura salvar(LogLeitura log){
        return logLeituraRepository.save(log);
    }

    private LogLeitura construirLogLeitura(Micro micro, String codigo){

        LogLeitura log = new LogLeitura();
        adicionarMicro(log, micro);
        adicionarCartao(log, codigo);
        return log;
    }
    
    private LogLeitura construirLogLeitura(Micro micro, Cartao cartao) {

        LogLeitura log = new LogLeitura();
        adicionarMicro(log, micro);
        adicionarCartao(log, cartao);
        return log;
    }

    private void adicionarMicro(LogLeitura log, Micro micro){
        log.setMicro(micro.getIdMicro().toString());
        log.setTipoMicro(micro.getTipoMicro().toString());
        log.setModoOperacao(micro.getModoOperacao().toString());
        log.setLocalizacao(micro.getLocalizacao().getDescricao());
    }

    private void adicionarCartao(LogLeitura log, Cartao cartao){
        log.setCodigo(cartao.getCodigo());
        log.setPessoa(cartao.getPessoa().getNome());
    }

    private void adicionarCartao(LogLeitura log, String codigo){
        log.setCodigo(codigo);
    }
}
