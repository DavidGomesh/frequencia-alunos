package com.ifma.frequencia.api.dto.request;

import com.ifma.frequencia.domain.enumerate.TipoMicro;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MicroRequest {
    
    @NotNull
    private TipoMicro tipoMicro = TipoMicro.ESP32;

    private Integer localizacao;
}
