package com.ifma.frequencia.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.repository.SalaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SalaServiceTest {
    
    @Autowired
    private SalaService salaService;
    
    @Autowired
    private SalaRepository salaRepository;

    @AfterEach
    void afterEach(){
        salaRepository.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){
        Sala sala = new Sala();
        assertThrows(ConstraintViolationException.class, () -> {
            salaService.salvar(sala);
        });
        assertNull(sala.getIdSala());
    }

    @Test
    void deve_Salvar(){
        Sala sala = new Sala();
        sala.setDescricao("P1S01");
        assertDoesNotThrow(() -> {
            salaService.salvar(sala);
        });
        assertNotNull(sala.getIdSala());
    }
}
