package com.ifma.frequencia.domain.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.ifma.frequencia.domain.utils.Duracao;

import lombok.Data;

@Data
@Entity
public class HorasEstagio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHorasEstagio;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_estagio")
    private Estagio estagio;

    @NotNull
    private LocalDate dataRegistro;

    @NotNull
    private LocalTime horaInicio;
    private LocalTime horaFim;

    public Duracao horasTotais(){
        try{
            return new Duracao(Duration.between(horaInicio, horaFim));

        }catch(Exception e){
            return null;
        }
    }
}
