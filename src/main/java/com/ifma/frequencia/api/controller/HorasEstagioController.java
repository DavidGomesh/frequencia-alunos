package com.ifma.frequencia.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifma.frequencia.api.dto.mapper.HorasEstagioMapper;
import com.ifma.frequencia.api.dto.request.HorasEstagioRequest;
import com.ifma.frequencia.domain.model.HorasEstagio;
import com.ifma.frequencia.domain.repository.HorasEstagioRepository;
import com.ifma.frequencia.domain.service.HorasEstagioService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("horas-estagio")
public class HorasEstagioController {

    private final HorasEstagioService horasEstagioService;
    private final HorasEstagioRepository horasEstagioRepository;
    private final HorasEstagioMapper horasEstagioMapper;
    
    @PutMapping("{hora-estagio}")
    public ResponseEntity<?> atualizar(
        @PathVariable("hora-estagio") HorasEstagio horasEstagio,
        @RequestBody @Valid HorasEstagioRequest horasEstagioRequest){

        HorasEstagio horasEstagioAtualizadas = horasEstagioMapper.toEntity(horasEstagioRequest);
        horasEstagioService.atualizar(horasEstagio, horasEstagioAtualizadas);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{hora-estagio}")
    public ResponseEntity<?> excluir(@PathVariable("hora-estagio") HorasEstagio horasEstagio){
        horasEstagioRepository.delete(horasEstagio);
        return ResponseEntity.noContent().build();
    }

}
