package com.ifma.frequencia.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ifma.frequencia.api.dto.mapper.SalaMapper;
import com.ifma.frequencia.api.dto.request.SalaRequest;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.repository.SalaRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("salas")
public class SalaController {

    private final SalaRepository salaRepository;
    private final SalaMapper salaMapper;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid SalaRequest salaRequest){

        Sala sala = salaMapper.toEntity(salaRequest);
        salaRepository.save(sala);

        return ResponseEntity.created(UriComponentsBuilder
            .newInstance().path("/salas/{id-sala}")
            .buildAndExpand(sala.getIdSala()).toUri()
        ).build();
    }
}
