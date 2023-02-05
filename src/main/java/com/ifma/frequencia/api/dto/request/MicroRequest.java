package com.ifma.frequencia.api.dto.request;

import javax.validation.constraints.NotNull;

import com.ifma.frequencia.domain.enumerate.TipoMicro;

import lombok.Data;

@Data
public class MicroRequest {
    
    @NotNull
    private TipoMicro tipoMicro = TipoMicro.ESP32;

    @NotNull
    private Integer localizacao;
}
