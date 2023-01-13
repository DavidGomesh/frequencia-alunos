package com.ifma.frequencia.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.HorasEstagio;
import com.ifma.frequencia.domain.model.generator.EstagioGenerator;
import com.ifma.frequencia.domain.repository.HorasEstagioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HorasEstagioServiceTest {

    @Autowired
    private HorasEstagioService horasEstagioService;

    @Autowired
    private HorasEstagioRepository horasEstagioRepository;

    @Autowired
    private EstagioGenerator estagioGenerator;
    
    @AfterEach
    void afterEach(){
        estagioGenerator.deleteAll();
    }
    
    @Test
    void deve_CadastrarNovaHorasEstagio(){
        Estagio estagio = estagioGenerator.valid().persist().build();

        HorasEstagio horasEstagio = horasEstagioService.cadastrarHora(estagio);
        assertNotNull(horasEstagio.getIdHorasEstagio());
        assertNull(horasEstagio.getHoraFim());

        horasEstagioRepository.deleteAll();
    }

    @Test
    void deve_AtualizarHorasEstagioAtual(){
        Estagio estagio = estagioGenerator.valid().persist().build();
        HorasEstagio horasEstagio = horasEstagioService.cadastrarHora(estagio);

        HorasEstagio horasAtualizadas = horasEstagioService.cadastrarHora(estagio);
        assertEquals(horasEstagio.getIdHorasEstagio(), horasAtualizadas.getIdHorasEstagio());
        assertNotNull(horasAtualizadas.getHoraFim());

        horasEstagioRepository.deleteAll();
    }
}
