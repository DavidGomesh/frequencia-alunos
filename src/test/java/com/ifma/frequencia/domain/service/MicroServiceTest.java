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

import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.repository.CartaoRepository;
import com.ifma.frequencia.domain.repository.LogLeituraRepository;
import com.ifma.frequencia.domain.repository.MicroRepository;
import com.ifma.frequencia.domain.repository.PessoaRepository;
import com.ifma.frequencia.domain.repository.SalaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MicroServiceTest {

    @Autowired
    private MicroService microService;

    @Autowired
    private MicroRepository microRepository;

    @Autowired
    private SalaRepository salaRepository;
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private CartaoRepository cartaoRepository;
    
    @Autowired
    private LogLeituraRepository logRepository;

    @AfterEach
    void afterEach(){
        microRepository.deleteAll();
        salaRepository.deleteAll();
        cartaoRepository.deleteAll();
        pessoaRepository.deleteAll();
        logRepository.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){
        Micro micro = new Micro();
        assertThrows(ConstraintViolationException.class, () -> {
            microService.salvar(micro);
        });
        assertNull(micro.getIdMicro());
    }

    @Test
    void deve_Salvar(){
        Sala sala = new Sala();
        sala.setDescricao("P1S01");
        salaRepository.save(sala);

        Micro micro = new Micro();
        micro.setLocalizacao(sala);
        assertDoesNotThrow(() -> {
            microService.salvar(micro);
        });
        assertNotNull(micro.getIdMicro());
    }

    @Test
    void deve_RealizarLeituraCartaoDesconhecido(){
        Sala sala = new Sala();
        sala.setDescricao("P1S01");
        salaRepository.save(sala);

        Micro micro = new Micro();
        micro.setLocalizacao(sala);
        microRepository.save(micro);

        assertDoesNotThrow(() -> {
            microService.leitura(micro, "Xx Xx Xx");
        });
    }

    @Test
    void deve_RealizarLeituraCartaoConhecido(){
        Sala sala = new Sala();
        sala.setDescricao("P1S01");
        salaRepository.save(sala);

        Micro micro = new Micro();
        micro.setLocalizacao(sala);
        microRepository.save(micro);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("David");
        pessoaRepository.save(pessoa);

        final String codigo = "Xx Xx Xx";

        Cartao cartao = new Cartao();
        cartao.setCodigo(codigo);
        cartao.setPessoa(pessoa);
        cartaoRepository.save(cartao);

        assertDoesNotThrow(() -> {
            microService.leitura(micro, codigo);
        });
    }
}
