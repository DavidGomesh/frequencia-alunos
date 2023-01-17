package com.ifma.frequencia.domain.repository.queries;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public Optional<HorasEstagio> buscarHorasAtuais(Estagio estagio) {

        QHorasEstagio qHorasEstagio = QHorasEstagio.horasEstagio;
        JPAQuery<HorasEstagio> query = (jpaQueryFactory.select(qHorasEstagio)
            .from(qHorasEstagio)
            .where(qHorasEstagio.estagio.idEstagio.eq(estagio.getIdEstagio()))
            .where(qHorasEstagio.dataRegistro.eq(LocalDate.now()))
        );

        HorasEstagio horasEstagio = query.fetchOne();
        return Optional.ofNullable(horasEstagio);
    }

    @Override
    public List<HorasEstagio> buscarHoras(Estagio estagio) {

        QHorasEstagio qHorasEstagio = QHorasEstagio.horasEstagio;
        JPAQuery<HorasEstagio> query = (jpaQueryFactory.select(qHorasEstagio)
            .from(qHorasEstagio)
            .where(qHorasEstagio.estagio.idEstagio.eq(estagio.getIdEstagio()))
        );

        return query.fetch();
    }



}
