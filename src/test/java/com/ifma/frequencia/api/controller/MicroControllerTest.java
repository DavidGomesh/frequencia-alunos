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
import com.ifma.frequencia.api.dto.request.MicroRequest;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.service.SalaService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MicroControllerTest {
    
    @Autowired
    private RequestPerformer requestPerformer;
    
    @Autowired
    private SalaService salaService;

    @Test
    void deveSalvar_SemLocalizacao(){

        MicroRequest microRequest = new MicroRequest();

        HttpEntity<MicroRequest> httpEntity = new HttpEntity<>(microRequest);
        ResponseEntity<?> response = postSalvar(httpEntity);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void deveSalvar_ComLocalizacao(){

        Sala sala = new Sala();
        sala.setDescricao("P1S1");
        salaService.salvar(sala);

        MicroRequest microRequest = new MicroRequest();
        microRequest.setLocalizacao(sala.getIdSala());

        HttpEntity<MicroRequest> httpEntity = new HttpEntity<>(microRequest);
        ResponseEntity<?> response = postSalvar(httpEntity);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    private ResponseEntity<?> postSalvar(HttpEntity<MicroRequest> httpEntity){
        return requestPerformer.post("/micros", httpEntity, Micro.class);
    }
    
}
