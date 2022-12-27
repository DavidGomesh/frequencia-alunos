package com.ifma.frequencia.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.exception.MicroDesconhecido;
import com.ifma.frequencia.domain.model.LogLeitura;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.repository.MicroRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MicroService {
    
    private final LogLeituraService logLeituraService;
    private final MicroRepository microrRepository;

    public Micro salvar(Micro microcontrolador){
        return microrRepository.save(microcontrolador);
    }

    public Optional<Micro> buscarPorId(Integer idMicro){
        return microrRepository.findById(idMicro);
    }

    public Micro pegarPorId(Integer idMicro){
        return buscarPorId(idMicro).orElseThrow(() -> {
            throw new MicroDesconhecido(idMicro);
        });
    }

    public LogLeitura leitura(Integer idMicro, String codigo){
        Micro micro = pegarPorId(idMicro);
        return logLeituraService.salvar(micro, codigo);
    }
}
