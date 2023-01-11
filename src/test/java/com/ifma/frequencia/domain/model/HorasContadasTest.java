package com.ifma.frequencia.domain.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HorasContadasTest {
    
    @Test
    void naoDeve_gerarHorasTotaisSemDataHoraSaida(){
        HorasContadas horasContadas = new HorasContadas();
        assertThrows(NullPointerException.class, () -> {
            horasContadas.horasTotais();
        });
    }

    @Test
    void deve_GerarHorasTotais(){
        HorasContadas horasContadas = new HorasContadas();
        horasContadas.setDataHoraSaida(
            horasContadas.getDataHoraEntrada().plusHours(4)
        );

        assertDoesNotThrow(() -> {
            horasContadas.horasTotais();
        });

        Duration horasTotais = horasContadas.horasTotais();
        assertEquals(4, horasTotais.toHours());
        assertEquals(60 * 4, horasTotais.toMinutes());

        horasContadas.setDataHoraSaida(
            horasContadas.getDataHoraEntrada().plusHours(3).plusMinutes(15)
        );

        horasTotais = horasContadas.horasTotais();
        assertEquals(3, horasTotais.toHours());
        assertEquals(60 * 3 + 15, horasTotais.toMinutes());
    }
}
