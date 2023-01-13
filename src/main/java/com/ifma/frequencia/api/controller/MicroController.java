package com.ifma.frequencia.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ifma.frequencia.api.dto.mapper.MicroMapper;
import com.ifma.frequencia.api.dto.request.MicroRequest;
import com.ifma.frequencia.domain.exception.MicroNotFoundException;
import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.HorasEstagio;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.repository.AlunoRepository;
import com.ifma.frequencia.domain.repository.EstagioRespository;
import com.ifma.frequencia.domain.service.HorasEstagioService;
import com.ifma.frequencia.domain.service.MicroService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("micros")
public class MicroController {

    private final MicroService microService;
    private final HorasEstagioService horasEstagioService;

    private final AlunoRepository alunoRepository;
    private final EstagioRespository estagioRespository;

    private final MicroMapper microMapper;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid MicroRequest microRequest){

        Micro micro = microMapper.toEntity(microRequest);
        microService.salvar(micro);

        return ResponseEntity.created(UriComponentsBuilder
            .newInstance().path("/micros/{id-microcontrolador}")
            .buildAndExpand(micro.getIdMicro()).toUri()
        ).build();
    }

    @PostMapping("{micro}/leitura/{codigo}")
    public ResponseEntity<?> leitura(@PathVariable("micro") Micro micro, @PathVariable("codigo") String codigo){
        try {
            var log = microService.leitura(micro, codigo);
            return ResponseEntity.created(UriComponentsBuilder
                .newInstance().path("/log-leitura/{id-log-leitura}")
                .buildAndExpand(log.getIdLogLeitura()).toUri()
            ).build();
            
        } catch (MicroNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                "Microcontrolador desconhecido!"
            );
        }
    }

    @PostMapping("{micro}/estagio/{codigo}")
    public ResponseEntity<?> cadastrarHorasEstagio(@PathVariable("micro") Micro micro, @PathVariable("codigo") String codigo){

        microService.leitura(micro, codigo);
        Optional<Aluno> optAluno = alunoRepository.buscarPorCodigoCartao(codigo);
        if(optAluno.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                "Nenhum aluno cadastrado com esse cartão!"
            );
        }
        
        Aluno aluno = optAluno.get();
        Optional<Estagio> optEstagio = estagioRespository.buscarAtivosPorAluno(aluno);
        if(optEstagio.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                "Nenhum estágio ativo para esse aluno!"
            );
        }

        Estagio estagio = optEstagio.get();
        HorasEstagio horasEstagio = horasEstagioService.cadastrarHora(estagio);
        return ResponseEntity.created(UriComponentsBuilder
            .newInstance().path("/horasEstagio/{id-horas-estagio}")
            .buildAndExpand(horasEstagio.getIdHorasEstagio()).toUri()
        ).build();
    }
}
