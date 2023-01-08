package com.ifma.frequencia.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ifma.frequencia.api.controller.utils.RequestPerformer;
import com.ifma.frequencia.api.dto.request.SalaRequest;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.repository.SalaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SalaControllerTest {
    
    @Autowired
    private RequestPerformer requestPerformer;
    
    @Autowired
    private SalaRepository salaRepository;

    @AfterEach
    void afterEach(){
        salaRepository.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){
        SalaRequest salaRequest = new SalaRequest();
        ResponseEntity<?> response = postSalvar(salaRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deveSalvar(){

        SalaRequest salaRequest = new SalaRequest();
        salaRequest.setDescricao("P1S1");

        ResponseEntity<?> response = postSalvar(salaRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    private ResponseEntity<?> postSalvar(Object requestParam){
        return requestPerformer.post("/salas", requestParam, Sala.class);
    }
}
