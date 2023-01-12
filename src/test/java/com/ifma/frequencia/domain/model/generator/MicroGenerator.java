package com.ifma.frequencia.domain.model.generator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.model.builder.MicroBuilder;
import com.ifma.frequencia.domain.repository.MicroRepository;

@Component
public class MicroGenerator extends MicroBuilder {

    @Autowired
    protected SalaGenerator salaGenerator;

    @Autowired
    private MicroRepository microRepository;
    private Set<Micro> micros = new HashSet<>();

    public MicroGenerator valid() {
        micro = new Micro();
        tipoMicro().modoOperacao();
        localizacao();
        return this;
    }
    
    public MicroGenerator invalid() {
        micro = new Micro();
        return this;
    }

    public MicroGenerator persist() {
        microRepository.save(micro);
        micros.add(micro);
        return this;
    }

    public void deleteAll() {
        micros.forEach(microRepository::delete);
        micros.clear();
        salaGenerator.deleteAll();
    }

    public MicroGenerator localizacao(){
        localizacao(salaGenerator.valid().persist().build());
        return this;
    }
}
