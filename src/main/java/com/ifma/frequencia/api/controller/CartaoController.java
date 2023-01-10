package com.ifma.frequencia.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ifma.frequencia.api.dto.mapper.CartaoMapper;
import com.ifma.frequencia.api.dto.request.CartaoRequest;
import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.service.CartaoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("cartoes")
public class CartaoController {

    private final CartaoService cartaoService;
    private final CartaoMapper cartaoMapper;
    
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid CartaoRequest cartaoRequest){

        Cartao cartao = cartaoMapper.toEntity(cartaoRequest);
        cartaoService.salvar(cartao);

        return ResponseEntity.created(UriComponentsBuilder
            .newInstance().path("/cartoes/{id-cartao}")
            .buildAndExpand(cartao.getIdCartao()).toUri()
        ).build();
    }
}
