package com.ifma.frequencia.domain.service;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.exception.CartaoNotFoundException;
import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.repository.CartaoRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartaoService {
    
    private final CartaoRepository cartaoRepository;

    public Cartao salvar(@Valid Cartao cartao){
        return cartaoRepository.save(cartao);
    }

    public Cartao buscarPorCodigo(String codigo){
        return cartaoRepository.findByCodigo(codigo).orElseThrow(() -> {
            throw new CartaoNotFoundException("CÓDIGO: " + codigo);
        });
    }
}
