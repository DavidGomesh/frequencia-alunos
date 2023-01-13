package com.ifma.frequencia.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ifma.frequencia.api.controller.utils.RequestPerformer;
import com.ifma.frequencia.api.dto.request.MicroRequest;
import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.model.generator.AlunoGenerator;
import com.ifma.frequencia.domain.model.generator.CartaoGenerator;
import com.ifma.frequencia.domain.model.generator.EstagioGenerator;
import com.ifma.frequencia.domain.model.generator.MicroGenerator;
import com.ifma.frequencia.domain.model.generator.SalaGenerator;
import com.ifma.frequencia.domain.repository.LogLeituraRepository;
import com.ifma.frequencia.domain.repository.MicroRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MicroControllerTest {

    @Autowired
    private RequestPerformer requestPerformer;

    @Autowired
    private MicroRepository microRepository;
    
    @Autowired
    private LogLeituraRepository logRepository;

    @Autowired
    private SalaGenerator salaGenerator;
    
    @Autowired
    private MicroGenerator microGenerator;

    @Autowired
    private AlunoGenerator alunoGenerator;
    
    @Autowired
    private CartaoGenerator cartaoGenerator;
    
    @Autowired
    private EstagioGenerator estagioGenerator;

    @AfterEach
    void afterEach(){
        microGenerator.deleteAll();
        logRepository.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){
        MicroRequest microRequest = new MicroRequest();
        ResponseEntity<?> response = postSalvar(microRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deve_Salvar(){

        Sala sala = salaGenerator.valid().persist().build();
        MicroRequest microRequest = new MicroRequest();
        microRequest.setLocalizacao(sala.getIdSala());

        ResponseEntity<?> response = postSalvar(microRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        microRepository.deleteAll();
    }

    @Test
    void deve_RealizarLeituraCartoes(){

        Micro micro = microGenerator.valid().persist().build();
        Map<String, String> args = new HashMap<>();
        args.put("id-micro", micro.getIdMicro().toString());
        args.put("codigo", "123");

        ResponseEntity<?> response = postLeitura(args);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void deve_RealizarCadastroHorasEstagio(){

        Micro micro = microGenerator.valid().persist().build();
        Aluno aluno = alunoGenerator.valid().persist().build();

        cartaoGenerator.codigo().pessoa(aluno.getPessoa());
        Cartao cartao = cartaoGenerator.persist().build();

        estagioGenerator.aluno(aluno);
        estagioGenerator.persist();

        Map<String, String> args = new HashMap<>();
        args.put("id-micro", micro.getIdMicro().toString());
        args.put("codigo", cartao.getCodigo());

        ResponseEntity<?> response = postHorasEstagio(args);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    private ResponseEntity<?> postSalvar(Object requestBody){
        return requestPerformer.post("/micros", requestBody, Micro.class);
    }

    private ResponseEntity<?> postLeitura(Map<String, String> args){
        return requestPerformer.post("/micros/{id-micro}/leitura/{codigo}", Micro.class, args);
    }

    private ResponseEntity<?> postHorasEstagio(Map<String, String> args){
        return requestPerformer.post("/micros/{id-micro}/estagio/{codigo}", Micro.class, args);
    }

}
