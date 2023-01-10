package com.ifma.frequencia.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.repository.AlunoRepository;
import com.ifma.frequencia.domain.repository.PessoaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AlunoServiceTest {

    @Autowired
    private AlunoService alunoService;
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @AfterEach
    void afterEach(){
        alunoRepository.deleteAll();
        pessoaRepository.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){

        Aluno aluno = new Aluno();
        assertThrows(ConstraintViolationException.class, () -> {
            alunoService.salvar(aluno);
        });
        assertNull(aluno.getIdAluno());
        
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("David");
        pessoaRepository.save(pessoa);

        aluno.setPessoa(pessoa);
        assertThrows(ConstraintViolationException.class, () -> {
            alunoService.salvar(aluno);
        });
        assertNull(aluno.getIdAluno());
    }

    @Test
    void deve_Salvar(){

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("David");
        pessoaRepository.save(pessoa);

        Aluno aluno = new Aluno();
        aluno.setPessoa(pessoa);
        aluno.setMatricula("20232SI0001");

        assertDoesNotThrow(() -> {
            alunoService.salvar(aluno);
        });

        assertNotNull(aluno.getIdAluno());
    }
}
