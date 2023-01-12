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
import com.ifma.frequencia.api.dto.request.CartaoRequest;
import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.model.generator.PessoaGenerator;
import com.ifma.frequencia.domain.repository.CartaoRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CartaoControllerTest {

    @Autowired
    private RequestPerformer requestPerformer;
    
    @Autowired
    private CartaoRepository cartaoRepository;
    
    @Autowired
    private PessoaGenerator pessoaGenerator;

    @AfterEach
    void afterEach(){
        cartaoRepository.deleteAll();
        pessoaGenerator.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){
        
        CartaoRequest cartaoRequest = new CartaoRequest();
        ResponseEntity<?> response = postSalvar(cartaoRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        
        cartaoRequest.setCodigo("Xx Xx Xx");
        response = postSalvar(cartaoRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        
        cartaoRequest.setPessoa(1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    
    @Test
    void deve_Salvar(){

        Pessoa pessoa = pessoaGenerator.valid().persist().build();

        CartaoRequest cartaoRequest = new CartaoRequest();
        cartaoRequest.setCodigo("Xx Xx Xx");
        cartaoRequest.setPessoa(pessoa.getIdPessoa());

        ResponseEntity<?> response = postSalvar(cartaoRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    private ResponseEntity<?> postSalvar(Object requestParam){
        return requestPerformer.post("/cartoes", requestParam, Cartao.class);
    }
}
