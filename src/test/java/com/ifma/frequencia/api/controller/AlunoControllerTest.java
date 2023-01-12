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
import com.ifma.frequencia.api.dto.request.AlunoRequest;
import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.model.generator.PessoaGenerator;
import com.ifma.frequencia.domain.repository.AlunoRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AlunoControllerTest {
    
    @Autowired
    private RequestPerformer requestPerformer;
    
    @Autowired
    private PessoaGenerator pessoaGenerator;
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @AfterEach
    void afterEach(){
        alunoRepository.deleteAll();
        pessoaGenerator.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){

        AlunoRequest alunoRequest = new AlunoRequest();
        ResponseEntity<?> response = postSalvar(alunoRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        
        alunoRequest.setPessoa(1);
        response = postSalvar(alunoRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        
        Pessoa pessoa = pessoaGenerator.valid().persist().build();
        alunoRequest.setPessoa(pessoa.getIdPessoa());
        response = postSalvar(alunoRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deve_Salvar(){

        Pessoa pessoa = pessoaGenerator.valid().persist().build();
        
        AlunoRequest alunoRequest = new AlunoRequest();
        alunoRequest.setPessoa(pessoa.getIdPessoa());
        alunoRequest.setMatricula("20231SI0001");

        ResponseEntity<?> response = postSalvar(alunoRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    private ResponseEntity<?> postSalvar(Object requestParam){
        return requestPerformer.post("/alunos", requestParam, Aluno.class);
    }
}
