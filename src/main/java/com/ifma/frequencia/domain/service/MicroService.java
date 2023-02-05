package com.ifma.frequencia.domain.service;

import java.util.Optional;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.LogLeitura;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.repository.CartaoRepository;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class MicroService {
    
    private final LogLeituraService logLeituraService;
    private final CartaoRepository cartaoRepository;

    private final SimpMessagingTemplate simpMessagingTemplate;

    public LogLeitura leitura(@NonNull Micro micro, @NonNull String codigo){

        LogLeitura log = null;
        Optional<Cartao> optCartao = cartaoRepository.findByCodigo(codigo);

        if(optCartao.isPresent()){
            Cartao cartao = optCartao.get();
            log = logLeituraService.salvar(micro, cartao);
        }else{
            log = logLeituraService.salvar(micro, codigo);
        }

        simpMessagingTemplate.convertAndSend(
            "/topic/leitura", "Cartão lido!"
        );

        return log;
    }
}
