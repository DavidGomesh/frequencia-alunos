package com.ifma.frequencia.domain.repository.queries;

import java.util.Optional;

import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Cartao;
import com.ifma.frequencia.domain.model.QAluno;
import com.ifma.frequencia.domain.repository.CartaoRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlunoQueryImpl implements AlunoQuery {

    private final CartaoRepository cartaoRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Aluno> buscarPorCodigoCartao(String codigo) {

        Optional<Cartao> optCartao = cartaoRepository.findByCodigo(codigo);
        if(optCartao.isEmpty()){
            return Optional.empty();
        }

        Cartao cartao = optCartao.get();

        QAluno qAluno = QAluno.aluno;
        JPAQuery<Aluno> query = (jpaQueryFactory
            .select(qAluno).from(qAluno)
            .where(qAluno.cartao.eq(cartao))
        );

        Aluno aluno = query.fetchOne();
        return Optional.ofNullable(aluno);
    }
    
}
