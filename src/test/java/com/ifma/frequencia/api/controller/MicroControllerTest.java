package com.ifma.frequencia.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
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
import com.ifma.frequencia.domain.repository.MicroRepository;
import com.ifma.frequencia.domain.repository.SalaRepository;
import com.ifma.frequencia.domain.service.MicroService;
import com.ifma.frequencia.domain.service.SalaService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MicroControllerTest {

    @Autowired
    private RequestPerformer requestPerformer;

    @Autowired
    private MicroService microService;

    @Autowired
    private SalaService salaService;

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
    void naoDeve_SalvarSemLocalizacao(){

        MicroRequest microRequest = new MicroRequest();

        HttpEntity<MicroRequest> httpEntity = new HttpEntity<>(microRequest);
        ResponseEntity<?> response = postSalvar(httpEntity);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deve_Salvar(){

        Sala sala = new Sala();
        sala.setDescricao("P1S1");
        salaService.salvar(sala);

        MicroRequest microRequest = new MicroRequest();
        microRequest.setLocalizacao(sala.getIdSala());

        HttpEntity<MicroRequest> httpEntity = new HttpEntity<>(microRequest);
        ResponseEntity<?> response = postSalvar(httpEntity);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void deve_RealizarLeituraCartoes(){

        Sala sala = new Sala();
        sala.setDescricao("P1S1");
        salaService.salvar(sala);

        Micro micro = new Micro();
        micro.setLocalizacao(sala);
        microService.salvar(micro);

        Map<String, String> args = new HashMap<>();
        args.put("id-micro", micro.getIdMicro().toString());
        args.put("codigo", "123");

        ResponseEntity<?> response = postLeitura(args);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    private ResponseEntity<?> postSalvar(HttpEntity<MicroRequest> httpEntity){
        return requestPerformer.post("/micros", httpEntity, Micro.class);
    }

    private ResponseEntity<?> postLeitura(Map<String, String> args){
        return requestPerformer.post("/micros/{id-micro}/leitura/{codigo}", Micro.class, args);
    }

}
