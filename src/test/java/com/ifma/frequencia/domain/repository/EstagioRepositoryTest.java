package com.ifma.frequencia.domain.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.generator.EstagioGenerator;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EstagioRepositoryTest {
    
    @Autowired
    private EstagioRespository estagioRespository;
    
    @Autowired
    private EstagioGenerator estagioGenerator;

    @Test
    void deve_EncontrarEstagioAtivoPorAluno(){
        
        Estagio estagio = estagioGenerator.valid().persist().build();
        Aluno aluno = estagio.getAluno();

        Optional<Estagio> optEstagio = estagioRespository.buscarAtivosPorAluno(aluno);
        assertTrue(optEstagio.isPresent());

        Estagio estagioEncontrado = optEstagio.get();
        assertEquals(estagio.getIdEstagio(), estagioEncontrado.getIdEstagio());
    }
}
