package com.ifma.frequencia.domain.repository.filter;

import java.time.LocalDate;

import lombok.Data;

@Data
public class HorasEstagioFilter {
    public Integer estagio;
    public LocalDate dataRegistro;
}
