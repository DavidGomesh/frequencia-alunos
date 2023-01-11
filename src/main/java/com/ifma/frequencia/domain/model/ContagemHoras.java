package com.ifma.frequencia.domain.model;

import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.ifma.frequencia.domain.enumerate.TipoContagem;

import lombok.Data;

@Data
@Entity
public class ContagemHoras {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContagemHoras;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoContagem tipoContagem;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_aluno")
    private Aluno aluno;

    @NotNull
    private LocalTime horaInicio;

    @OneToMany(mappedBy = "contagemHoras")
    private Set<HorasContadas> horasContadas;
}
