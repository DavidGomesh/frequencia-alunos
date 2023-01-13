package com.ifma.frequencia.domain.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.exception.HoraFinalNotDefinedException;
import com.ifma.frequencia.domain.model.generator.HorasEstagioGenerator;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HorasEstagioTest {

    @Autowired
    private HorasEstagioGenerator horasEstagioGenerator;

    @AfterEach
    void afterEach(){
        horasEstagioGenerator.init();
    }

    @Test
    void naoDeve_GerarHorasTotaisSemHoraFim(){
        HorasEstagio horasEstagio = horasEstagioGenerator.horaInicio().build();
        assertThrows(HoraFinalNotDefinedException.class, () -> {
            horasEstagio.horasTotais();
        });
    }
    
    @Test
    void deve_GerarHorasTotais(){
        HorasEstagio horasEstagio = horasEstagioGenerator.valid().build();
        assertDoesNotThrow(() -> {
            horasEstagio.horasTotais();
        });
    }
}
