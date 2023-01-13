package com.ifma.frequencia.domain.repository.queries;

import java.util.Optional;

import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.HorasEstagio;

public interface HorasEstagioQuery {

    /**
     * Busca as horas registradas no dia atual.
     * @param estagio
     * @return
     */
    public Optional<HorasEstagio> buscarHorasAtuais(Estagio estagio);
}
