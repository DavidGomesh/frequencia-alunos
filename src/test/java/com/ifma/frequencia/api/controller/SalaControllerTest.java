package com.ifma.frequencia.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ifma.frequencia.api.dto.request.SalaRequest;
import com.ifma.frequencia.domain.model.Sala;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SalaControllerTest {
    
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void naoDeve_SalvarComErroDeValidacao(){

        SalaRequest salaRequest = new SalaRequest();

        HttpEntity<SalaRequest> httpEntity = new HttpEntity<>(salaRequest);
        ResponseEntity<?> response = testRestTemplate.exchange(
            "/salas", HttpMethod.POST, httpEntity, Sala.class
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deveSalvar(){

        SalaRequest salaRequest = new SalaRequest();
        salaRequest.setDescricao("P1S1");

        HttpEntity<SalaRequest> httpEntity = new HttpEntity<>(salaRequest);
        ResponseEntity<?> response = testRestTemplate.exchange(
            "/salas", HttpMethod.POST, httpEntity, Sala.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
