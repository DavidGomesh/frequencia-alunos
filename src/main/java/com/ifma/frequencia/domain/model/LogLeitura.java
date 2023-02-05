package com.ifma.frequencia.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class LogLeitura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLogLeitura;

    @NotNull
    private LocalDateTime dataHora = LocalDateTime.now();

    @NotBlank
    private String micro;
    @NotBlank
    private String tipoMicro;
    @NotBlank
    private String modoOperacao;
    @NotBlank
    private String localizacao;

    @NotBlank
    private String codigo;
    private String pessoa;
}
