package com.ifma.frequencia.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifma.frequencia.api.dto.mapper.EstagioMapper;
import com.ifma.frequencia.api.dto.response.EstagioResponse;
import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.repository.EstagioRespository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("estagios")
public class EstagioController {

    private final EstagioRespository estagioRespository;
    private final EstagioMapper estagioMapper;

    @GetMapping("ativos")
    public ResponseEntity<?> todosAtivos(){
        List<Estagio> estagios = estagioRespository.buscarAtivos();
        List<EstagioResponse> estagiosResponse = estagioMapper.toResponseList(estagios);
        return ResponseEntity.ok(estagiosResponse);
    }
}
