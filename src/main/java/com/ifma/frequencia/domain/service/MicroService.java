package com.ifma.frequencia.domain.service;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.exception.CartaoNotFoundException;
import com.ifma.frequencia.domain.exception.MicroNotFoundException;
import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.LogLeitura;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.repository.MicroRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MicroService {
    
    private final CartaoService cartaoService;
    private final LogLeituraService logLeituraService;

    private final MicroRepository microrRepository;

    public Micro salvar(Micro microcontrolador){
        return microrRepository.save(microcontrolador);
    }

    public Micro buscarPorId(Integer idMicro){
        return microrRepository.findById(idMicro).orElseThrow(() -> {
            throw new MicroNotFoundException(idMicro);
        });
    }

    public LogLeitura leitura(Integer idMicro, String codigo){
        Micro micro = buscarPorId(idMicro);

        try {
            Cartao cartao = cartaoService.buscarPorCodigo(codigo);
            return logLeituraService.salvar(micro, cartao);

        } catch (CartaoNotFoundException e) {
            return logLeituraService.salvar(micro, codigo);
        }
    }
}
