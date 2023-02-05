package com.ifma.frequencia.domain.repository.queries;

import java.util.List;
import java.util.Optional;

import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.QEstagio;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EstagioQueryImpl implements EstagioQuery {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Estagio> buscarAtivosPorAluno(Aluno aluno) {

        QEstagio qEstagio = QEstagio.estagio;
        JPAQuery<Estagio> query = (jpaQueryFactory
            .select(qEstagio).from(qEstagio)
            .where(qEstagio.aluno.eq(aluno))
            .where(qEstagio.ativo.isTrue())
        );

        Estagio estagio = query.fetchOne();
        return Optional.ofNullable(estagio);
    }
    
    @Override
    public List<Estagio> buscarAtivos() {

        QEstagio qEstagio = QEstagio.estagio;
        JPAQuery<Estagio> query = (jpaQueryFactory
            .select(qEstagio).from(qEstagio)
            .where(qEstagio.ativo.isTrue())
        );
    
        return query.fetch();
    }
    
}
