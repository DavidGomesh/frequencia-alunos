package com.ifma.frequencia.domain.model.builder;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.enumerate.ModoOperacao;
import com.ifma.frequencia.domain.enumerate.TipoMicro;
import com.ifma.frequencia.domain.model.LogLeitura;

@Component
public class LogLeituraBuilder {
    
    protected LogLeitura log = new LogLeitura();

    public LogLeituraBuilder dataHora(){
        return dataHora(LocalDateTime.now());
    }

    public LogLeituraBuilder dataHora(LocalDateTime dataHora){
        log.setDataHora(dataHora);
        return this;
    }

    public LogLeituraBuilder micro(){
        return micro("1");
    }

    public LogLeituraBuilder micro(String micro){
        log.setMicro(micro);
        return this;
    }

    public LogLeituraBuilder tipoMicro(){
        return tipoMicro(TipoMicro.ESP32.toString());
    }

    public LogLeituraBuilder tipoMicro(String tipoMicro){
        log.setTipoMicro(tipoMicro);
        return this;
    }

    public LogLeituraBuilder modoOperacao(){
        return modoOperacao(ModoOperacao.APENAS_LEITURA.toString());
    }

    public LogLeituraBuilder modoOperacao(String modoOperacao){
        log.setModoOperacao(modoOperacao);
        return this;
    }

    public LogLeituraBuilder localizacao(){
        return localizacao("P1S09");
    }

    public LogLeituraBuilder localizacao(String localizacao){
        log.setLocalizacao(localizacao);
        return this;
    }

    public LogLeituraBuilder codigo(){
        return codigo("Xx Xx Xx");
    }

    public LogLeituraBuilder codigo(String codigo){
        log.setCodigo(codigo);
        return this;
    }

    public LogLeituraBuilder pessoa(){
        return pessoa("David Gomesh");
    }

    public LogLeituraBuilder pessoa(String pessoa){
        log.setPessoa(pessoa);
        return this;
    }

    public LogLeituraBuilder init() {
        log = new LogLeitura();
        return this;
    }

    public LogLeitura build() {
        return log;
    }
}
