package com.ifma.frequencia.domain.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.exception.CartaoNotFoundException;
import com.ifma.frequencia.domain.exception.MicroNotFoundException;
import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.LogLeitura;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.repository.MicroRepository;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class MicroService {
    
    private final CartaoService cartaoService;
    private final LogLeituraService logLeituraService;
    private final MicroRepository microrRepository;

    private final SimpMessagingTemplate simpMessagingTemplate;

    public Micro salvar(Micro microcontrolador){
        return microrRepository.save(microcontrolador);
    }

    public Micro buscarPorId(Integer idMicro){
        return microrRepository.findById(idMicro).orElseThrow(() -> {
            throw new MicroNotFoundException(idMicro);
        });
    }

    public LogLeitura leitura(@NonNull Micro micro, @NonNull String codigo){

        LogLeitura log = null;
        try {
            Cartao cartao = cartaoService.buscarPorCodigo(codigo);
            log = logLeituraService.salvar(micro, cartao);

        } catch (CartaoNotFoundException e) {
            log = logLeituraService.salvar(micro, codigo);
        }
        
        simpMessagingTemplate.convertAndSend("/topic/leitura", "Cartão lido!");
        return log;
    }
}
