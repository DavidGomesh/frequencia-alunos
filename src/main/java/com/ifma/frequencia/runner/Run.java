package com.ifma.frequencia.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ifma.frequencia.api.controller.MicroController;
import com.ifma.frequencia.domain.enumerate.TipoMicro;
import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.service.CartaoService;
import com.ifma.frequencia.domain.service.MicroService;
import com.ifma.frequencia.domain.service.PessoaService;
import com.ifma.frequencia.domain.service.SalaService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Run implements CommandLineRunner {

    private final SalaService salaService;
    private final MicroService microService;
    private final PessoaService pessoaService;
    private final CartaoService cartaoService;

    @Override
    public void run(String... args) throws Exception {

        // ===================================== 
        // SALAS
        Sala sala1 = new Sala();
        sala1.setDescricao("S01P03");
        
        salaService.salvar(sala1);
        
        // ===================================== 
        // MICROCONTROLADORES
        Micro micro1 = new Micro();
        micro1.setLocalizacao(sala1);

        Micro micro2 = new Micro();
        micro2.setLocalizacao(sala1);
        micro2.setTipoMicro(TipoMicro.ARDUINO);
        
        microService.salvar(micro1);
        microService.salvar(micro2);
        
        // ===================================== 
        // PESSOAS
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("David Gomesh");

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Ana Beatriz");

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setNome("Marcos Oliveira");

        pessoaService.salvar(pessoa1);
        pessoaService.salvar(pessoa2);
        pessoaService.salvar(pessoa3);

        // ===================================== 
        // CARTOES
        Cartao cartao1 = new Cartao();
        cartao1.setCodigo("123");
        cartao1.setPessoa(pessoa1);

        Cartao cartao2 = new Cartao();
        cartao2.setCodigo("ASD");
        cartao2.setPessoa(pessoa1);

        Cartao cartao3 = new Cartao();
        cartao3.setCodigo("321");
        cartao3.setPessoa(pessoa2);

        Cartao cartao4 = new Cartao();
        cartao4.setCodigo("987");
        cartao4.setPessoa(pessoa3);

        cartaoService.salvar(cartao1);
        cartaoService.salvar(cartao2);
        cartaoService.salvar(cartao3);
        cartaoService.salvar(cartao4);

        // LOGS
        microService.leitura(1, "321");
        microService.leitura(2, "987");
        microService.leitura(1, "123");
        microService.leitura(1, "123312");
        microService.leitura(1, "123");
        microService.leitura(2, "123");
        microService.leitura(2, "987");
        microService.leitura(1, "asdas");
        microService.leitura(2, "asdadd");
        microService.leitura(2, "987");
    }
    
}
