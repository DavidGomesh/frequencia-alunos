package com.ifma.frequencia.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCartao;

    @NotBlank
    private String codigo;

    @OneToOne(mappedBy = "cartao")
    private Aluno aluno;
}
