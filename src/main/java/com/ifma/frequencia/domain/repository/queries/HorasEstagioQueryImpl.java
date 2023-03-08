package com.ifma.frequencia.domain.repository.queries;

import java.time.LocalDate;
import java.util.List;

import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.HorasEstagio;
import com.ifma.frequencia.domain.model.QHorasEstagio;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HorasEstagioQueryImpl implements HorasEstagioQuery {

    private final JPAQueryFactory jpaQueryFactory;
    
    @Override
    public List<HorasEstagio> buscarHorasAtuais(Estagio estagio) {

        QHorasEstagio qHorasEstagio = QHorasEstagio.horasEstagio;
        JPAQuery<HorasEstagio> query = (jpaQueryFactory
            .select(qHorasEstagio).from(qHorasEstagio)
            .where(qHorasEstagio.estagio.eq(estagio))
            .where(qHorasEstagio.dataRegistro.eq(LocalDate.now()))
            .orderBy(qHorasEstagio.dataRegistro.desc())
        );

        return query.fetch();
    }

    @Override
    public List<HorasEstagio> buscarHoras(Estagio estagio) {

        QHorasEstagio qHorasEstagio = QHorasEstagio.horasEstagio;
        JPAQuery<HorasEstagio> query = (jpaQueryFactory
            .select(qHorasEstagio).from(qHorasEstagio)
            .where(qHorasEstagio.estagio.eq(estagio))
        );

        return query.fetch();
    }
}
