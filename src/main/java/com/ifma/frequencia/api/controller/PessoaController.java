package com.ifma.frequencia.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ifma.frequencia.api.dto.mapper.PessoaMapper;
import com.ifma.frequencia.api.dto.request.PessoaRequest;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.service.PessoaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("pessoas")
public class PessoaController {

    private final PessoaService pessoaService;
    private final PessoaMapper pessoaMapper;
    
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody PessoaRequest pessoaRequest){

        Pessoa pessoa = pessoaMapper.toEntity(pessoaRequest);
        pessoaService.salvar(pessoa);

        return ResponseEntity.created(UriComponentsBuilder
            .newInstance().path("/pessoas/{id-pessoa}")
            .buildAndExpand(pessoa.getIdPessoa()).toUri()
        ).build();
    }
}
