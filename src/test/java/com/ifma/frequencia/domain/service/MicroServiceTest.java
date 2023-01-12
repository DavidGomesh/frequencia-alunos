package com.ifma.frequencia.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.model.generator.CartaoGenerator;
import com.ifma.frequencia.domain.model.generator.MicroGenerator;
import com.ifma.frequencia.domain.repository.LogLeituraRepository;
import com.ifma.frequencia.domain.repository.MicroRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MicroServiceTest {

    @Autowired
    private MicroService microService;
    
    @Autowired
    private MicroRepository microRepository;

    @Autowired
    private MicroGenerator microGenerator;
    
    @Autowired
    private LogLeituraRepository logRepository;
    
    @Autowired
    private CartaoGenerator cartaoGenerator;
    

    @AfterEach
    void afterEach(){
        microGenerator.deleteAll();
        cartaoGenerator.deleteAll();
        logRepository.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){
        Micro micro = microGenerator.invalid().build();
        assertThrows(ConstraintViolationException.class, () -> {
            microService.salvar(micro);
        });
    }

    @Test
    void deve_Salvar(){
        Micro micro = microGenerator.valid().build();
        assertDoesNotThrow(() -> {
            microService.salvar(micro);
        });
        microRepository.delete(micro);
    }

    @Test
    void deve_RealizarLeituraCartaoDesconhecido(){
        Micro micro = microGenerator.valid().persist().build();
        assertDoesNotThrow(() -> {
            microService.leitura(micro, "Xx Xx Xx");
        });
    }
    
    @Test
    void deve_RealizarLeituraCartaoConhecido(){

        Micro micro = microGenerator.valid().persist().build();
        
        final String codigo = "Xx Xx Xx";
        cartaoGenerator.valid().codigo(codigo);
        cartaoGenerator.persist().build();

        assertDoesNotThrow(() -> {
            microService.leitura(micro, codigo);
        });

    }
}
