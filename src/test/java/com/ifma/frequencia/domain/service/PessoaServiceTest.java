package com.ifma.frequencia.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.model.generator.PessoaGenerator;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PessoaServiceTest {

    @Autowired
    private PessoaService pessoaService;
    
    @Autowired
    private PessoaGenerator pessoaGenerator;

    @AfterEach
    void afterEach(){
        pessoaGenerator.deleteAll();
    }
    
    @Test
    void naoDeve_SalvarComErroDeValidacao(){
        Pessoa pessoa = pessoaGenerator.invalid().build();
        assertThrows(ConstraintViolationException.class, () -> {
            pessoaService.salvar(pessoa);
        });
    }

    @Test
    void deve_Salvar(){
        Pessoa pessoa = pessoaGenerator.valid().build();
        assertDoesNotThrow(() -> {
            pessoaService.salvar(pessoa);
        });
    }

}
