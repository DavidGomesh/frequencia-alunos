package com.ifma.frequencia.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.enumerate.TipoContagem;
import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.ContagemHoras;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.repository.AlunoRepository;
import com.ifma.frequencia.domain.repository.ContagemHorasRepository;
import com.ifma.frequencia.domain.repository.PessoaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ContagemHorasServiceTest {

    @Autowired
    private ContagemHorasService contagemHorasService;

    @Autowired
    private ContagemHorasRepository contagemHorasRepository;
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private AlunoRepository alunoRepository;

    @AfterEach
    void afterEach(){
        contagemHorasRepository.deleteAll();
        alunoRepository.deleteAll();
        pessoaRepository.deleteAll();
    }
    
    @Test
    void naoDeve_SalvarComErroDeValidacao(){

        ContagemHoras contagemHoras = new ContagemHoras();
        assertThrows(ConstraintViolationException.class, () -> {
            contagemHorasService.salvar(contagemHoras);
        });
        assertNull(contagemHoras.getIdContagemHoras());
        
        contagemHoras.setTipoContagem(TipoContagem.ESTAGIO);
        assertThrows(ConstraintViolationException.class, () -> {
            contagemHorasService.salvar(contagemHoras);
        });
        assertNull(contagemHoras.getIdContagemHoras());
        
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("David");
        pessoaRepository.save(pessoa);

        Aluno aluno = new Aluno();
        aluno.setPessoa(pessoa);
        aluno.setMatricula("20232SI0001");
        alunoRepository.save(aluno);

        contagemHoras.setAluno(aluno);
        contagemHoras.setTipoContagem(TipoContagem.ESTAGIO);
        assertThrows(ConstraintViolationException.class, () -> {
            contagemHorasService.salvar(contagemHoras);
        });
        assertNull(contagemHoras.getIdContagemHoras());

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

        ContagemHoras contagemHoras = new ContagemHoras();
        contagemHoras.setTipoContagem(TipoContagem.ESTAGIO);
        contagemHoras.setAluno(aluno);
        contagemHoras.setHoraInicio(LocalTime.now());

        assertDoesNotThrow(() -> {
            contagemHorasService.salvar(contagemHoras);
        });
        assertNotNull(contagemHoras.getIdContagemHoras());
        
    }
}
