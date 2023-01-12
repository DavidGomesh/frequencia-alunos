package com.ifma.frequencia.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.generator.AlunoGenerator;
import com.ifma.frequencia.domain.repository.AlunoRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AlunoServiceTest {

    @Autowired
    private AlunoService alunoService;
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private AlunoGenerator alunoGenerator;

    @AfterEach
    void afterEach(){
        alunoGenerator.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){

        Aluno aluno = alunoGenerator.invalid().build();
        assertThrows(ConstraintViolationException.class, () -> {
            alunoService.salvar(aluno);
        });

        alunoGenerator.pessoa();
        assertThrows(ConstraintViolationException.class, () -> {
            alunoService.salvar(aluno);
        });
    }

    @Test
    void deve_Salvar(){
        Aluno aluno = alunoGenerator.valid().build();
        assertDoesNotThrow(() -> {
            alunoService.salvar(aluno);
        });
        alunoRepository.delete(aluno);
    }
}
