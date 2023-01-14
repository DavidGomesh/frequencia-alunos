package com.ifma.frequencia.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ifma.frequencia.api.dto.mapper.AlunoMapper;
import com.ifma.frequencia.api.dto.request.AlunoRequest;
import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.service.AlunoService;
import com.ifma.frequencia.domain.service.PessoaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("alunos")
public class AlunoController {

    private final PessoaService pessoaService;
    private final AlunoService alunoService;

    private final AlunoMapper alunoMapper;
    
    @PostMapping
    public ResponseEntity<?> salvar(@Valid AlunoRequest alunoRequest){

        Aluno aluno = alunoMapper.toEntity(alunoRequest);
        pessoaService.salvar(aluno.getPessoa());
        alunoService.salvar(aluno);

        return ResponseEntity.created(UriComponentsBuilder
            .newInstance().path("/alunos/{id-aluno}")
            .buildAndExpand(aluno.getIdAluno()).toUri()
        ).build();
    }
}
