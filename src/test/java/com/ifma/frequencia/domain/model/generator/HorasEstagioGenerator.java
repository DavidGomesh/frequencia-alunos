package com.ifma.frequencia.domain.model.generator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.HorasEstagio;
import com.ifma.frequencia.domain.model.builder.HorasEstagioBuilder;
import com.ifma.frequencia.domain.repository.HorasEstagioRepository;

@Component
public class HorasEstagioGenerator extends HorasEstagioBuilder {

    @Autowired
    private EstagioGenerator estagioGenerator;

    @Autowired
    private HorasEstagioRepository repository;
    private Set<HorasEstagio> horasEstagioSet = new HashSet<>();

    public HorasEstagioGenerator valid(){
        horasEstagio = new HorasEstagio();
        estagio().dataRegistro().horaInicio().horaFim();
        return this;
    }

    public HorasEstagioGenerator invalid(){
        horasEstagio = new HorasEstagio();
        return this;
    }

    public HorasEstagioGenerator persist(){
        repository.save(horasEstagio);
        horasEstagioSet.add(horasEstagio);
        return this;
    }

    public void deleteAll() {
        horasEstagioSet.forEach(repository::delete);
        horasEstagioSet.clear();
        estagioGenerator.deleteAll();
    }

    public HorasEstagioGenerator estagio(){
        estagio(estagioGenerator.valid().persist().build());
        return this;
    }
}
