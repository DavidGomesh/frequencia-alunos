package com.ifma.frequencia.api.controller;

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
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.service.MicroService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("micros")
public class MicroController {

    private final MicroService microService;
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
}
