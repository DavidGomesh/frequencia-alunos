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
import com.ifma.frequencia.api.dto.request.PessoaRequest;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.repository.PessoaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PessoaControllerTest {
    
    @Autowired
    private RequestPerformer requestPerformer;
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @AfterEach
    void afterEach(){
        pessoaRepository.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){
        PessoaRequest pessoaRequest = new PessoaRequest();
        ResponseEntity<?> response = postSalvar(pessoaRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    
    @Test
    void deve_Salvar(){
        PessoaRequest pessoaRequest = new PessoaRequest();
        pessoaRequest.setNome("David");

        ResponseEntity<?> response = postSalvar(pessoaRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    private ResponseEntity<?> postSalvar(Object requestParam){
        return requestPerformer.post("/pessoas", requestParam, Pessoa.class);
    }
}
