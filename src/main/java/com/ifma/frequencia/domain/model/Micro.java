package com.ifma.frequencia.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.ifma.frequencia.domain.enumerate.ModoOperacao;
import com.ifma.frequencia.domain.enumerate.TipoMicro;

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
