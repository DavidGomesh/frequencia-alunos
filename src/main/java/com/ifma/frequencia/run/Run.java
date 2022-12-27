package com.ifma.frequencia.run;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.service.MicroService;
import com.ifma.frequencia.domain.service.SalaService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Run implements CommandLineRunner {

    private final SalaService salaService;
    private final MicroService microcontroladorService;

    @Override
    public void run(String... args) throws Exception {

        Sala sala = new Sala();
        sala.setDescricao("S01P03");

        salaService.salvar(sala);

        Micro microcontrolador = new Micro();
        microcontrolador.setLocalizacao(sala);

        microcontroladorService.salvar(microcontrolador);
    }
    
}
