package com.ifma.frequencia.api.dto.request;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class HorasEstagioRequest {
    
    @NotNull
    private LocalTime horaInicio;
    private LocalTime horaFim;
}
