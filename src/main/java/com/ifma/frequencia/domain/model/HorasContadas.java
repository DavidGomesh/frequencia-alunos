package com.ifma.frequencia.domain.model;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class HorasContadas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHorasContadas;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_contagem_horas")
    private ContagemHoras contagemHoras;

    @NotNull
    private LocalDateTime dataHoraEntrada = LocalDateTime.now();

    private LocalDateTime dataHoraSaida;

    public Duration horasTotais(){
        return Duration.between(dataHoraEntrada, dataHoraSaida);
    }
}
