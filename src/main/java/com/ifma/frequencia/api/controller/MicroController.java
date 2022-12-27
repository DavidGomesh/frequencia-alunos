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
import com.ifma.frequencia.api.dto.request.MicrocontroladorRequest;
import com.ifma.frequencia.domain.exception.MicroDesconhecido;
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

    @PostMapping("{id-micro}/leitura/{codigo}")
    public ResponseEntity<?> leitura(@PathVariable("id-micro") Integer idMicro, @PathVariable("codigo") String codigo){

        try {

            var log = microService.leitura(idMicro, codigo);
            return ResponseEntity.created(UriComponentsBuilder
                .newInstance().path("/log-leitura/{id-log-leitura}")
                .buildAndExpand(log.getIdLogLeitura()).toUri()
            ).build();
            
        } catch (MicroDesconhecido e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                "Microcontrolador desconhecido!"
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro desconhecido");
        }

    }
}
