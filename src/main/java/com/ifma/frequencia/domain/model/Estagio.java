package com.ifma.frequencia.domain.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Estagio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstagio;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_aluno")
    private Aluno aluno;

    @NotNull
    private Boolean ativo = true;

    @OneToMany(mappedBy = "estagio")
    private Set<HorasEstagio> horasEstagio;
}
