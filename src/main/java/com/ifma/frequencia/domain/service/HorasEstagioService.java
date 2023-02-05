package com.ifma.frequencia.domain.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.HorasEstagio;
import com.ifma.frequencia.domain.repository.HorasEstagioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HorasEstagioService {

    private final HorasEstagioRepository horasEstagioRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public HorasEstagio cadastrarHora(Estagio estagio){

        HorasEstagio horasEstagio = null;
        Optional<HorasEstagio> optHorasAtuais = horasEstagioRepository.buscarHorasAtuais(estagio);

        if(optHorasAtuais.isEmpty()){
            horasEstagio = new HorasEstagio();
            horasEstagio.setEstagio(estagio);
            horasEstagio.setDataRegistro(LocalDate.now());
            horasEstagio.setHoraInicio(LocalTime.now());
            return horasEstagioRepository.save(horasEstagio);
        }
        
        horasEstagio = optHorasAtuais.get();
        if(horasEstagio.getHoraFim() == null){
            horasEstagio.setHoraFim(LocalTime.now());
            simpMessagingTemplate.convertAndSend("/topic/contagem-horas", "Horas cadastradas!");
        }

        return horasEstagioRepository.save(horasEstagio);
    }
}
