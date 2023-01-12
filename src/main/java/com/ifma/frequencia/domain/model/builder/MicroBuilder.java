package com.ifma.frequencia.domain.model.builder;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.enumerate.ModoOperacao;
import com.ifma.frequencia.domain.enumerate.TipoMicro;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.model.Sala;

@Component
public class MicroBuilder {

    protected Micro micro = new Micro();

    public MicroBuilder tipoMicro(){
        return tipoMicro(TipoMicro.ESP32);
    }
    
    public MicroBuilder tipoMicro(TipoMicro tipoMicro){
        micro.setTipoMicro(tipoMicro);
        return this;
    }

    public MicroBuilder modoOperacao(){
        return modoOperacao(ModoOperacao.APENAS_LEITURA);
    }
    
    public MicroBuilder modoOperacao(ModoOperacao modoOperacao){
        micro.setModoOperacao(modoOperacao);
        return this;
    }
    
    public MicroBuilder localizacao(Sala localizacao){
        micro.setLocalizacao(localizacao);
        return this;
    }

    public MicroBuilder init() {
        micro = new Micro();
        return this;
    }

    public Micro build() {
        return micro;
    }
}
