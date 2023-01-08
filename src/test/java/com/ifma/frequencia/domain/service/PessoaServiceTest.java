package com.ifma.frequencia.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.repository.PessoaRepository;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PessoaServiceTest {

    @Autowired
    private PessoaService pessoaService;
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @AfterEach
    void afterEach(){
        pessoaRepository.deleteAll();
    }
    
    @Test
    void naoDeve_SalvarComErroDeValidacao(){
        Pessoa pessoa = new Pessoa();
        assertThrows(ConstraintViolationException.class, () -> {
            pessoaService.salvar(pessoa);
        });
        assertNull(pessoa.getIdPessoa());
    }

    @Test
    void deve_Salvar(){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("David");
        assertDoesNotThrow(() -> {
            pessoaService.salvar(pessoa);
        });
        assertNotNull(pessoa.getIdPessoa());
    }

}
