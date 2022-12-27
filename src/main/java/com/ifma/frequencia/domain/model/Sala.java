package com.ifma.frequencia.domain.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSala;

    @NotBlank
    private String descricao;

    @NotNull
    @OneToMany(mappedBy = "localizacao")
    private Set<Microcontrolador> microcontroladores = new HashSet<>();
}
