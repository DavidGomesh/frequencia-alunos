package com.ifma.frequencia.api.dto.mapper;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.api.dto.request.PessoaRequest;
import com.ifma.frequencia.api.dto.response.PessoaResponse;
import com.ifma.frequencia.domain.model.Pessoa;

@Component
public class PessoaMapper {
    
    public Pessoa toEntity(PessoaRequest pessoaRequest){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaRequest.getNome());
        return pessoa;
    }

    public PessoaResponse toResponse(Pessoa pessoa){
        PessoaResponse pessoaResponse = new PessoaResponse();
        pessoaResponse.setNome(pessoa.getNome());
        return pessoaResponse;
    }
}
