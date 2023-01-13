package com.ifma.frequencia.domain.model.builder;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.HorasEstagio;

@Component
public class HorasEstagioBuilder {

    protected HorasEstagio horasEstagio = new HorasEstagio();

    public HorasEstagioBuilder estagio(Estagio estagio){
        horasEstagio.setEstagio(estagio);
        return this;
    }

    public HorasEstagioBuilder dataRegistro(){
        return dataRegistro(LocalDate.now());
    }

    public HorasEstagioBuilder dataRegistro(LocalDate dataRegistro){
        horasEstagio.setDataRegistro(dataRegistro);
        return this;
    }

    public HorasEstagioBuilder horaInicio(){
        return horaInicio(LocalTime.now());
    }

    public HorasEstagioBuilder horaInicio(LocalTime horaInicio){
        horasEstagio.setHoraInicio(horaInicio);
        return this;
    }

    public HorasEstagioBuilder horaFim(){
        return horaFim(LocalTime.now().plusHours(4));
    }

    public HorasEstagioBuilder horaFim(LocalTime horaFim){
        horasEstagio.setHoraFim(horaFim);
        return this;
    }

    public HorasEstagioBuilder init(){
        horasEstagio = new HorasEstagio();
        return this;
    }

    public HorasEstagio build(){
        return horasEstagio;
    }

}
