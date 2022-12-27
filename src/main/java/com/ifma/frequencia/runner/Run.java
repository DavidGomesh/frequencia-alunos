package com.ifma.frequencia.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.service.MicroService;
import com.ifma.frequencia.domain.service.PessoaService;
import com.ifma.frequencia.domain.service.SalaService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Run implements CommandLineRunner {

    private final SalaService salaService;
    private final MicroService microcontroladorService;
    private final PessoaService pessoaService;

    @Override
    public void run(String... args) throws Exception {

        Sala sala1 = new Sala();
        sala1.setDescricao("S01P03");

        salaService.salvar(sala1);

        Micro micro1 = new Micro();
        micro1.setLocalizacao(sala1);

        microcontroladorService.salvar(micro1);

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("David Gomesh");

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Ana Beatriz");

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setNome("Marcos Oliveira");

        pessoaService.salvar(pessoa1);
        pessoaService.salvar(pessoa2);
        pessoaService.salvar(pessoa3);
    
    }
    
}
