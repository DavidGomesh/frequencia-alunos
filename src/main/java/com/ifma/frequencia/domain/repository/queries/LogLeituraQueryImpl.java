package com.ifma.frequencia.domain.repository.queries;

import java.util.List;

import com.ifma.frequencia.domain.model.LogLeitura;
import com.ifma.frequencia.domain.model.QLogLeitura;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LogLeituraQueryImpl implements LogLeituraQuery {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<LogLeitura> buscarUltimos() {
        
        QLogLeitura qLogLeitura = QLogLeitura.logLeitura;
        JPAQuery<LogLeitura> query = (jpaQueryFactory
            .select(qLogLeitura).from(qLogLeitura)
            .orderBy(qLogLeitura.dataHora.desc())
            .limit(10)
        );

        return query.fetch();
    }
    
}
