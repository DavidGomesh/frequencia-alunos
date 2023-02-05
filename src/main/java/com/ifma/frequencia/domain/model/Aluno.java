package com.ifma.frequencia.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAluno;
    
    @NotBlank
    private String nome;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_curso")
    private Curso curso;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_cartao")
    private Cartao cartao;
}
