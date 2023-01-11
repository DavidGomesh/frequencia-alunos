package com.ifma.frequencia.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ifma.frequencia.api.dto.mapper.ContagemHorasMapper;
import com.ifma.frequencia.api.dto.request.ContagemHorasRequest;
import com.ifma.frequencia.domain.model.ContagemHoras;
import com.ifma.frequencia.domain.service.ContagemHorasService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("contagens-horas")
public class ContagemHorasController {

    private final ContagemHorasService contagemHorasService;
    private final ContagemHorasMapper contagemHorasMapper;
    
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid ContagemHorasRequest contagemHorasRequest){

        ContagemHoras contagemHoras = contagemHorasMapper.toEntity(contagemHorasRequest);
        contagemHorasService.salvar(contagemHoras);

        return ResponseEntity.created(UriComponentsBuilder
            .newInstance().path("contagens-horas/{id-contagem-horas}")
            .buildAndExpand(contagemHoras.getIdContagemHoras()).toUri()
        ).build();
    }
}
