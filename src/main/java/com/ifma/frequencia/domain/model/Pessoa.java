package com.ifma.frequencia.domain.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPessoa;

    @NotBlank
    private String nome;

    @OneToMany(mappedBy = "pessoa")
    private Set<Cartao> cartoes;
}
