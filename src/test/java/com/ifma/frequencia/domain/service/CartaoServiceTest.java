package com.ifma.frequencia.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ifma.frequencia.domain.exception.CartaoNotFoundException;
import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.generator.CartaoGenerator;
import com.ifma.frequencia.domain.repository.CartaoRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CartaoServiceTest {

    @Autowired
    private CartaoService cartaoService;
    
    @Autowired
    private CartaoRepository cartaoRepository;
    
    @Autowired
    private CartaoGenerator cartaoGenerator;
    
    @AfterEach
    void afterEach(){
        cartaoGenerator.deleteAll();
    }

    @Test
    void naoDeve_SalvarComErroDeValidacao(){

        Cartao cartao = cartaoGenerator.invalid().build();
        assertThrows(ConstraintViolationException.class, () -> {
            cartaoService.salvar(cartao);
        });
        
        cartaoGenerator.codigo();
        assertThrows(ConstraintViolationException.class, () -> {
            cartaoService.salvar(cartao);
        });

    }

    @Test
    void deve_Salvar(){
        Cartao cartao = cartaoGenerator.valid().build();
        assertDoesNotThrow(() -> {
            cartaoService.salvar(cartao);
        });
        cartaoRepository.delete(cartao);
    }

    @Test
    void naoDeve_EncontrarCartaoInexistente(){
        cartaoGenerator.valid().codigo("Xx Xx Xx");
        cartaoGenerator.persist();
        assertThrows(CartaoNotFoundException.class, () -> {
            cartaoService.buscarPorCodigo("Zz Zz Zz");
        });
    }

    @Test
    void deve_EncontrarCartao(){

        String codigo = "Xx Xx Xx";
        cartaoGenerator.valid().codigo(codigo);
        Cartao cartao = cartaoGenerator.persist().build();

        Cartao cartaoEncontrado = cartaoService.buscarPorCodigo(codigo);
        assertNotNull(cartaoEncontrado);
        assertEquals(cartao.getIdCartao(), cartaoEncontrado.getIdCartao());

    }
}
