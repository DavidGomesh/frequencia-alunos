package com.ifma.frequencia.domain.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.generator.AlunoGenerator;
import com.ifma.frequencia.domain.model.generator.CartaoGenerator;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AlunoRepositoryTest {
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private AlunoGenerator alunoGenerator;
    
    @Autowired
    private CartaoGenerator cartaoGenerator;

    @AfterEach
    void afterEach(){
    }

    @Test
    void deve_EncontrarAlunoPorCodigoCartao(){

        Aluno aluno = alunoGenerator.valid().persist().build();
        cartaoGenerator.codigo().pessoa(aluno.getPessoa());
        Cartao cartao = cartaoGenerator.persist().build();

        Optional<Aluno> optAluno = alunoRepository.buscarPorCodigoCartao(cartao.getCodigo());
        assertTrue(optAluno.isPresent());

        Aluno alunoEncontrado = optAluno.get();
        assertEquals(aluno.getIdAluno(), alunoEncontrado.getIdAluno());
    }
}
