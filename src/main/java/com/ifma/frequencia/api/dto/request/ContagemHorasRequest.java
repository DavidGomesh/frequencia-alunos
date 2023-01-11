package com.ifma.frequencia.api.dto.request;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import com.ifma.frequencia.domain.enumerate.TipoContagem;

import lombok.Data;

@Data
public class ContagemHorasRequest {

    @NotNull
    private TipoContagem tipoContagem;

    @NotNull
    private Integer aluno;

    @NotNull
    private LocalTime horaInicio;
}
