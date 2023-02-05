package com.ifma.frequencia.domain.service;

import java.util.Optional;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.LogLeitura;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.repository.AlunoRepository;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class MicroService {
    
    private final LogLeituraService logLeituraService;
    private final AlunoRepository alunoRepository;

    private final SimpMessagingTemplate simpMessagingTemplate;

    public LogLeitura leitura(@NonNull Micro micro, @NonNull String cartao){

        LogLeitura log = null;
        Optional<Aluno> optAluno = alunoRepository.findByCartao(cartao);

        if(optAluno.isPresent()){
            Aluno aluno = optAluno.get();
            log = logLeituraService.salvar(micro, aluno);
        }else{
            log = logLeituraService.salvar(micro, cartao);
        }

        simpMessagingTemplate.convertAndSend(
            "/topic/leitura", "Cartão lido!"
        );

        return log;
    }
}
