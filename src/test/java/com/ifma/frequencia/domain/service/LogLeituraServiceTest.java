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
import com.ifma.frequencia.domain.model.generator.CartaoGenerator;
import com.ifma.frequencia.domain.model.generator.MicroGenerator;
import com.ifma.frequencia.domain.repository.LogLeituraRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LogLeituraServiceTest {
    
    @Autowired
    private LogLeituraService logService;
    
    @Autowired
    private LogLeituraRepository logRepository;
    
    @Autowired
    private MicroGenerator microGenerator;
    
    @Autowired
    private CartaoGenerator cartaoGenerator;

    @AfterEach
    void afterEach(){
        microGenerator.deleteAll();
        cartaoGenerator.deleteAll();
    }

    @Test
    void deve_SalvarLogCartaoDesconhecido(){

        final String codigo = "Xx Xx Xx";
        Micro micro = microGenerator.valid().persist().build();

        assertDoesNotThrow(() -> {

            LogLeitura log = logService.salvar(micro, codigo);
            assertEquals(micro.getIdMicro().toString(), log.getMicro());
            assertEquals(micro.getTipoMicro().toString(), log.getTipoMicro());
            assertEquals(micro.getModoOperacao().toString(), log.getModoOperacao());
            assertEquals(micro.getLocalizacao().getDescricao(), log.getLocalizacao());
            assertEquals(codigo, log.getCodigo());
            assertNull(log.getPessoa());

            logRepository.delete(log);
        });
    }
    
    @Test
    void deve_SalvarLogCartaoConhecido(){

        Micro micro = microGenerator.valid().persist().build();
        Cartao cartao = cartaoGenerator.valid().persist().build();
        
        assertDoesNotThrow(() -> {
            LogLeitura log = logService.salvar(micro, cartao);

            assertEquals(micro.getIdMicro().toString(), log.getMicro());
            assertEquals(micro.getTipoMicro().toString(), log.getTipoMicro());
            assertEquals(micro.getModoOperacao().toString(), log.getModoOperacao());
            assertEquals(micro.getLocalizacao().getDescricao(), log.getLocalizacao());
            assertEquals(cartao.getCodigo(), log.getCodigo());
            assertEquals(cartao.getPessoa().getNome(), log.getPessoa());

            logRepository.delete(log);
        });
    }
}
