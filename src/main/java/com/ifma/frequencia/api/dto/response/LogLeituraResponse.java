package com.ifma.frequencia.api.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LogLeituraResponse {
    private LocalDateTime dataHora;
    private String micro;
    private String tipoMicro;
    private String modoOperacao;
    private String localizacao;
    private String codigo;
    private String pessoa;
}
