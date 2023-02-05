package com.ifma.frequencia.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ifma.frequencia.api.dto.mapper.AlunoMapper;
import com.ifma.frequencia.api.dto.request.AlunoRequest;
import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.repository.AlunoRepository;
import com.ifma.frequencia.domain.repository.EstagioRespository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("alunos")
public class AlunoController {

    private final AlunoRepository alunoRepository;
    private final EstagioRespository estagioRespository;

    private final AlunoMapper alunoMapper;
    
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid AlunoRequest alunoRequest){

        Aluno aluno = alunoMapper.toEntity(alunoRequest);
        alunoRepository.save(aluno);

        /** TODO: Colocar em um service */
        Estagio estagio = new Estagio();
        estagio.setAluno(aluno);
        estagio.setAtivo(true);
        estagioRespository.save(estagio);

        return ResponseEntity.created(UriComponentsBuilder
            .newInstance().path("/alunos/{id-aluno}")
            .buildAndExpand(aluno.getIdAluno()).toUri()
        ).body(aluno.getIdAluno());
    }
}
