package com.ifma.frequencia.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.model.generator.SalaGenerator;
import com.ifma.frequencia.domain.repository.SalaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SalaServiceTest {
    
    @Autowired
    private SalaService salaService;
    
    @Autowired
    private SalaRepository salaRepository;
    
    @Autowired
    private SalaGenerator salaGenerator;

    @AfterEach
    void afterEach(){
        salaGenerator.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){
        Sala sala = salaGenerator.invalid().build();
        assertThrows(ConstraintViolationException.class, () -> {
            salaService.salvar(sala);
        });
    }

    @Test
    void deve_Salvar(){
        Sala sala = salaGenerator.valid().build();
        assertDoesNotThrow(() -> {
            salaService.salvar(sala);
        });
        salaRepository.delete(sala);
    }
}
