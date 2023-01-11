package com.ifma.frequencia.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ifma.frequencia.api.controller.utils.RequestPerformer;
import com.ifma.frequencia.api.dto.request.ContagemHorasRequest;
import com.ifma.frequencia.domain.enumerate.TipoContagem;
import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.ContagemHoras;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.repository.AlunoRepository;
import com.ifma.frequencia.domain.repository.ContagemHorasRepository;
import com.ifma.frequencia.domain.repository.PessoaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ContagemHorasControllerTest {
    
    @Autowired
    private RequestPerformer requestPerformer;

    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private ContagemHorasRepository contagemHorasRepository;

    @AfterEach
    void afterEach(){
        contagemHorasRepository.deleteAll();
        alunoRepository.deleteAll();
        pessoaRepository.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){

        ContagemHorasRequest contagemHorasRequest = new ContagemHorasRequest();
        ResponseEntity<?> response = postSalvar(contagemHorasRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        
        contagemHorasRequest.setTipoContagem(TipoContagem.ESTAGIO);
        response = postSalvar(contagemHorasRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        
        contagemHorasRequest.setAluno(1);
        response = postSalvar(contagemHorasRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("David");
        pessoaRepository.save(pessoa);

        Aluno aluno = new Aluno();
        aluno.setPessoa(pessoa);
        aluno.setMatricula("20232SI0001");
        alunoRepository.save(aluno);

        contagemHorasRequest.setAluno(aluno.getIdAluno());
        response = postSalvar(contagemHorasRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        
    }

    @Test
    void deve_Salvar(){

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("David");
        pessoaRepository.save(pessoa);

        Aluno aluno = new Aluno();
        aluno.setPessoa(pessoa);
        aluno.setMatricula("20232SI0001");
        alunoRepository.save(aluno);

        ContagemHorasRequest contagemHorasRequest = new ContagemHorasRequest();
        contagemHorasRequest.setTipoContagem(TipoContagem.ESTAGIO);
        contagemHorasRequest.setAluno(aluno.getIdAluno());
        contagemHorasRequest.setHoraInicio(LocalTime.now());

        ResponseEntity<?> response = postSalvar(contagemHorasRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    private ResponseEntity<?> postSalvar(Object requestParam){
        return requestPerformer.post("/contagens-horas", requestParam, ContagemHoras.class);
    }
}
