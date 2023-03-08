package com.ifma.frequencia.domain.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

        List<HorasEstagio> horasAtuais = horasEstagioRepository.buscarHorasAtuais(estagio);
        
        if(horasAtuais.isEmpty()){
            HorasEstagio horasEstagio = novaHorasEstagio(estagio);
            notificarHorasCadastradas();
            return horasEstagioRepository.save(horasEstagio);
        }

        if(horasAtuais.size() == 1){
            HorasEstagio horasEstagio = horasAtuais.get(0);
            if(horasEstagio.getHoraFim() == null){
                finalizarHorasEstagio(horasEstagio);
            }else{
                horasEstagio = novaHorasEstagio(estagio);
            }
            notificarHorasCadastradas();
            return horasEstagioRepository.save(horasEstagio);
        }

        HorasEstagio horasEstagio = horasAtuais.get(1);
        if(horasEstagio.getHoraFim() == null){
            finalizarHorasEstagio(horasEstagio);
            return horasEstagioRepository.save(horasEstagio);
        }

        return horasEstagio;
    }

    private HorasEstagio novaHorasEstagio(Estagio estagio){
        HorasEstagio horasEstagio = new HorasEstagio();
        horasEstagio.setEstagio(estagio);
        horasEstagio.setDataRegistro(LocalDate.now());
        horasEstagio.setHoraInicio(LocalTime.now());
        return horasEstagio;
    }

    private void finalizarHorasEstagio(HorasEstagio horasEstagio){
        horasEstagio.setHoraFim(LocalTime.now());
    }

    public HorasEstagio atualizar(HorasEstagio horasEstagio, HorasEstagio horasEstagioAtualizadas){
        horasEstagio.setHoraInicio(horasEstagioAtualizadas.getHoraInicio());
        horasEstagio.setHoraFim(horasEstagioAtualizadas.getHoraFim());
        return horasEstagioRepository.save(horasEstagio);
    }

    private void notificarHorasCadastradas(){
        simpMessagingTemplate.convertAndSend("/topic/contagem-horas", "Horas cadastradas!");
    }
}
