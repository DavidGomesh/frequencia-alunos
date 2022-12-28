package com.ifma.frequencia.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ifma.frequencia.api.controller.utils.RequestPerformer;
import com.ifma.frequencia.api.dto.request.SalaRequest;
import com.ifma.frequencia.domain.model.Sala;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SalaControllerTest {
    
    @Autowired
    private RequestPerformer requestPerformer;

    @Test
    void naoDeve_SalvarComErroDeValidacao(){

        SalaRequest salaRequest = new SalaRequest();

        HttpEntity<SalaRequest> httpEntity = new HttpEntity<>(salaRequest);
        ResponseEntity<?> response = postSalvar(httpEntity);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deveSalvar(){

        SalaRequest salaRequest = new SalaRequest();
        salaRequest.setDescricao("P1S1");

        HttpEntity<SalaRequest> httpEntity = new HttpEntity<>(salaRequest);
        ResponseEntity<?> response = postSalvar(httpEntity);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    private ResponseEntity<?> postSalvar(HttpEntity<SalaRequest> httpEntity){
        return requestPerformer.post("/salas", httpEntity, Sala.class);
    }
}
