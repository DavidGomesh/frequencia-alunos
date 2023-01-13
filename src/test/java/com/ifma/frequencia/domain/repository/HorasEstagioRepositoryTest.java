package com.ifma.frequencia.domain.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.HorasEstagio;
import com.ifma.frequencia.domain.model.generator.EstagioGenerator;
import com.ifma.frequencia.domain.model.generator.HorasEstagioGenerator;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HorasEstagioRepositoryTest {

    @Autowired
    private HorasEstagioRepository horasEstagioRepository;
    
    @Autowired
    private EstagioGenerator estagioGenerator;
    
    @Autowired
    private HorasEstagioGenerator horasEstagioGenerator;

    @AfterEach
    void afterEach(){
        horasEstagioGenerator.deleteAll();
        estagioGenerator.deleteAll();
    }
    
    @Test
    void deve_BuscarHorasEstagioAtuais(){

        Estagio estagio = estagioGenerator.valid().persist().build();
        horasEstagioGenerator.valid().estagio(estagio).dataRegistro(LocalDate.now());
        HorasEstagio horasEstagio = horasEstagioGenerator.persist().build();

        horasEstagioGenerator.valid().estagio(estagio).dataRegistro(LocalDate.now().plusDays(1));
        horasEstagioGenerator.persist().build();

        Optional<HorasEstagio> optHorasAtuais = horasEstagioRepository.buscarHorasAtuais(estagio);

        assertTrue(optHorasAtuais.isPresent());

        HorasEstagio horasAtuais = optHorasAtuais.get();
        assertEquals(horasEstagio.getIdHorasEstagio(), horasAtuais.getIdHorasEstagio());
        assertEquals(LocalDate.now(), horasAtuais.getDataRegistro());
    }
}
