package com.ifma.frequencia.domain.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.exception.CartaoNotFoundException;
import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.repository.CartaoRepository;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class CartaoService {
    
    private final CartaoRepository cartaoRepository;

    public Cartao salvar(@Valid Cartao cartao){
        return cartaoRepository.save(cartao);
    }

    public Cartao buscarPorCodigo(@NonNull String codigo){
        return cartaoRepository.findByCodigo(codigo).orElseThrow(() -> {
            throw new CartaoNotFoundException("Código: " + codigo);
        });
    }
}
