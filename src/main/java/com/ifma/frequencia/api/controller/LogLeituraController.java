package com.ifma.frequencia.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifma.frequencia.api.dto.mapper.LogLeituraMapper;
import com.ifma.frequencia.api.dto.response.LogLeituraResponse;
import com.ifma.frequencia.domain.model.LogLeitura;
import com.ifma.frequencia.domain.repository.LogLeituraRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("logs")
public class LogLeituraController {

    private final LogLeituraRepository logRepository;
    private final LogLeituraMapper logMapper;
    
    @GetMapping("ultimos")
    public ResponseEntity<?> ultimosLogs(){
        List<LogLeitura> ultimosLogs = logRepository.buscarUltimos();
        List<LogLeituraResponse> ultimosLogsResp = logMapper.toResponseList(ultimosLogs);
        return ResponseEntity.ok(ultimosLogsResp);
    }
}
