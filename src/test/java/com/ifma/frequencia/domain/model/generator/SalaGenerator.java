package com.ifma.frequencia.domain.model.generator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.model.builder.SalaBuilder;
import com.ifma.frequencia.domain.repository.SalaRepository;

@Component
public class SalaGenerator extends SalaBuilder {
    
    @Autowired
    private SalaRepository salaRepository;
    private Set<Sala> salas = new HashSet<>();

    public SalaGenerator valid() {
        sala = new Sala();
        descricao();
        return this;
    }
    
    public SalaGenerator invalid() {
        sala = new Sala();
        return this;
    }

    public SalaGenerator persist() {
        salaRepository.save(sala);
        salas.add(sala);
        return this;
    }

    public void deleteAll() {
        salas.forEach(salaRepository::delete);
        salas.clear();
    }
}
