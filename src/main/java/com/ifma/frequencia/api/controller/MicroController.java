package com.ifma.frequencia.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ifma.frequencia.api.dto.mapper.MicroMapper;
import com.ifma.frequencia.api.dto.request.MicrocontroladorRequest;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.service.MicroService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("microcontroladores")
public class MicroController {

    private final MicroService microService;
    private final MicroMapper microMapper;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody MicrocontroladorRequest microRequest){

        Micro micro = microMapper.toEntity(microRequest);
        microService.salvar(micro);

        return ResponseEntity.created(UriComponentsBuilder
            .newInstance().path("/microcontroladores/{id-microcontrolador}")
            .buildAndExpand(micro.getIdMicro()).toUri()
        ).build();
    }
}
