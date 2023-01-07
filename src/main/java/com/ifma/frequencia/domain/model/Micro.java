package com.ifma.frequencia.domain.model;

import com.ifma.frequencia.domain.enumerate.ModoOperacao;
import com.ifma.frequencia.domain.enumerate.TipoMicro;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Micro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMicro;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoMicro tipoMicro = TipoMicro.ESP32;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private ModoOperacao modoOperacao = ModoOperacao.APENAS_LEITURA;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_localizacao")
    private Sala localizacao;
}
