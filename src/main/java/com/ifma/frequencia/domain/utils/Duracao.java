package com.ifma.frequencia.domain.utils;

import java.time.Duration;

import lombok.Data;

@Data
public class Duracao {

    private Duration duration;
    
    private Long horas;
    private Long minutos;
    private Long segundos;
    private Long milisegundos;
    
    public Duracao(Duration duracao){
        this.horas = duracao.toHours();
        this.minutos = duracao.toMinutes() % 60;
        this.segundos = duracao.toSeconds() % 60;
        this.milisegundos = duracao.toMillis() % 1000;
        this.duration = duracao;
    }
}
