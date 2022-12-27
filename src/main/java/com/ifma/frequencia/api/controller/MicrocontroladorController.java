package com.ifma.frequencia.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ifma.frequencia.api.dto.mapper.MicrocontroladorMapper;
import com.ifma.frequencia.api.dto.request.MicrocontroladorRequest;
import com.ifma.frequencia.domain.model.Microcontrolador;
import com.ifma.frequencia.domain.service.MicrocontroladorService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("microcontroladores")
public class MicrocontroladorController {

    private final MicrocontroladorService microService;
    private final MicrocontroladorMapper microMapper;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody MicrocontroladorRequest microRequest){

        Microcontrolador micro = microMapper.toEntity(microRequest);
        microService.salvar(micro);

        return ResponseEntity.created(UriComponentsBuilder
            .newInstance().path("/microcontroladores/{id-microcontrolador}")
            .buildAndExpand(micro.getIdMicro()).toUri()
        ).build();
    }
}
