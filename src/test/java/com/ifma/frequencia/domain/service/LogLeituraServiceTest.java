package com.ifma.frequencia.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.LogLeitura;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.repository.CartaoRepository;
import com.ifma.frequencia.domain.repository.LogLeituraRepository;
import com.ifma.frequencia.domain.repository.MicroRepository;
import com.ifma.frequencia.domain.repository.PessoaRepository;
import com.ifma.frequencia.domain.repository.SalaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LogLeituraServiceTest {
    
    @Autowired
    private LogLeituraService logService;
    
    @Autowired
    private LogLeituraRepository logRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private MicroRepository microRepository;
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private CartaoRepository cartaoRepository;

    @AfterEach
    void afterEach(){
        logRepository.deleteAll();
        microRepository.deleteAll();
        salaRepository.deleteAll();
        cartaoRepository.deleteAll();
        pessoaRepository.deleteAll();
    }

    @Test
    void deve_SalvarLogCartaoDesconhecido(){
        Sala sala = new Sala();
        sala.setDescricao("P1S01");
        salaRepository.save(sala);

        Micro micro = new Micro();
        micro.setLocalizacao(sala);
        microRepository.save(micro);

        final String codigo = "Xx Xx Xx";
        assertDoesNotThrow(() -> {
            LogLeitura log = logService.salvar(micro, codigo);

            assertEquals(micro.getIdMicro().toString(), log.getMicro());
            assertEquals(micro.getTipoMicro().toString(), log.getTipoMicro());
            assertEquals(micro.getModoOperacao().toString(), log.getModoOperacao());
            assertEquals(sala.getDescricao(), log.getLocalizacao());
            assertEquals(codigo, log.getCodigo());
            assertNull(log.getPessoa());
        });
    }
    
    @Test
    void deve_SalvarLogCartaoConhecido(){
        Sala sala = new Sala();
        sala.setDescricao("P1S01");
        salaRepository.save(sala);

        Micro micro = new Micro();
        micro.setLocalizacao(sala);
        microRepository.save(micro);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("David");
        pessoaRepository.save(pessoa);
        
        final String codigo = "Xx Xx Xx";

        Cartao cartao = new Cartao();
        cartao.setCodigo(codigo);
        cartao.setPessoa(pessoa);
        cartaoRepository.save(cartao);

        assertDoesNotThrow(() -> {
            LogLeitura log = logService.salvar(micro, cartao);

            assertEquals(micro.getIdMicro().toString(), log.getMicro());
            assertEquals(micro.getTipoMicro().toString(), log.getTipoMicro());
            assertEquals(micro.getModoOperacao().toString(), log.getModoOperacao());
            assertEquals(sala.getDescricao(), log.getLocalizacao());
            assertEquals(codigo, log.getCodigo());
            assertEquals(pessoa.getNome(), log.getPessoa());
        });
    }
}
