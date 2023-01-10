package com.ifma.frequencia.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.exception.CartaoNotFoundException;
import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.repository.CartaoRepository;
import com.ifma.frequencia.domain.repository.PessoaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CartaoServiceTest {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;
    
    @AfterEach
    void afterEach(){
        cartaoRepository.deleteAll();
        pessoaRepository.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){
        Cartao cartao = new Cartao();
        assertThrows(ConstraintViolationException.class, () -> {
            cartaoService.salvar(cartao);
        });
        assertNull(cartao.getIdCartao());
        
        cartao.setCodigo("Xx Xx Xx");
        assertThrows(ConstraintViolationException.class, () -> {
            cartaoService.salvar(cartao);
        });
        assertNull(cartao.getIdCartao());
    }

    @Test
    void deve_Salvar(){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("David");
        pessoaRepository.save(pessoa);

        Cartao cartao = new Cartao();
        cartao.setCodigo("Xx Xx Xx");
        cartao.setPessoa(pessoa);

        assertDoesNotThrow(() -> {
            cartaoService.salvar(cartao);
        });
        assertNotNull(cartao.getIdCartao());
    }

    @Test
    void naoDeve_EncontrarCartaoInexistente(){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("David");
        pessoaRepository.save(pessoa);

        Cartao cartao = new Cartao();
        cartao.setCodigo("Xx Xx Xx");
        cartao.setPessoa(pessoa);
        cartaoRepository.save(cartao);

        assertThrows(CartaoNotFoundException.class, () -> {
            cartaoService.buscarPorCodigo("Zz Zz Zz");
        });
    }

    @Test
    void deve_EncontrarCartao(){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("David");
        pessoaRepository.save(pessoa);

        Cartao cartao1 = new Cartao();
        cartao1.setCodigo("Xx Xx Xx");
        cartao1.setPessoa(pessoa);
        cartaoRepository.save(cartao1);
        
        Cartao cartao2 = new Cartao();
        cartao2.setCodigo("Zz Zz Zz");
        cartao2.setPessoa(pessoa);
        cartaoRepository.save(cartao2);

        Cartao cartaoEncontrado = cartaoService.buscarPorCodigo("Xx Xx Xx");
        assertNotNull(cartaoEncontrado);
        assertEquals(cartao1.getIdCartao(), cartaoEncontrado.getIdCartao());
    }
}
