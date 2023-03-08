package com.ifma.frequencia.domain.repository.queries;

import java.util.List;

import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.HorasEstagio;

public interface HorasEstagioQuery {

    /**
     * Busca as horas registradas no dia atual.
     * @param estagio
     * @return
     */
    public List<HorasEstagio> buscarHorasAtuais(Estagio estagio);

    /**
     * Busca todas as horas registradas
     * @param estagio
     * @return
     */
    public List<HorasEstagio> buscarHoras(Estagio estagio);
}
