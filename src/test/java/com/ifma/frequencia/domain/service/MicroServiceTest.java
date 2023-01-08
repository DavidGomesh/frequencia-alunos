package com.ifma.frequencia.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.repository.MicroRepository;
import com.ifma.frequencia.domain.repository.SalaRepository;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MicroServiceTest {

    @Autowired
    private MicroService microService;

    @Autowired
    private MicroRepository microRepository;

    @Autowired
    private SalaRepository salaRepository;

    @AfterEach
    void afterEach(){
        microRepository.deleteAll();
        salaRepository.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){
        Micro micro = new Micro();
        assertThrows(ConstraintViolationException.class, () -> {
            microService.salvar(micro);
        });
        assertNull(micro.getIdMicro());
    }

    @Test
    void deve_Salvar(){
        Sala sala = new Sala();
        sala.setDescricao("P1S01");
        salaRepository.save(sala);

        Micro micro = new Micro();
        micro.setLocalizacao(sala);
        assertDoesNotThrow(() -> {
            microService.salvar(micro);
        });
        assertNotNull(micro.getIdMicro());
    }

    @Test
    void testeDeLeitura(){
        fail("Not implemented yet!");
    }
}
