package com.ifma.frequencia.domain.service;

import java.time.LocalTime;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.HorasEstagio;
import com.ifma.frequencia.domain.model.builder.HorasEstagioBuilder;
import com.ifma.frequencia.domain.repository.HorasEstagioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HorasEstagioService {

    private final HorasEstagioRepository horasEstagioRepository;
    private final HorasEstagioBuilder horasEstagioBuilder;

    @Transactional
    public HorasEstagio salvar(@Valid HorasEstagio horasEstagio){
        return horasEstagioRepository.save(horasEstagio);
    }

    public HorasEstagio cadastrarHora(Estagio estagio){

        Optional<HorasEstagio> optHorasAtuais = horasEstagioRepository.buscarHorasAtuais(estagio);
        if(optHorasAtuais.isEmpty()){
            HorasEstagio horasEstagio = (horasEstagioBuilder
                .estagio(estagio).dataRegistro().horaInicio().build()
            );
            return salvar(horasEstagio);
        }
        
        HorasEstagio horasEstagio = optHorasAtuais.get();
        if(horasEstagio.getHoraFim() == null){
            horasEstagio.setHoraFim(LocalTime.now());
            salvar(horasEstagio);
        }
        
        return horasEstagio;
    }

}
