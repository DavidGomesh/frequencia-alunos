package com.ifma.frequencia.api.dto.mapper;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.api.dto.request.CartaoRequest;
import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.service.PessoaService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CartaoMapper {

    private final PessoaService pessoaService;
    
    public Cartao toEntity(CartaoRequest cartaoRequest){

        Pessoa pessoa = pessoaService.buscarPorId(cartaoRequest.getPessoa());
        
        Cartao cartao = new Cartao();
        cartao.setCodigo(cartaoRequest.getCodigo());
        cartao.setPessoa(pessoa);
        
        return cartao;
    }
}
