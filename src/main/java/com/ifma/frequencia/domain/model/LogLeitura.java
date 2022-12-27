package com.ifma.frequencia.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
