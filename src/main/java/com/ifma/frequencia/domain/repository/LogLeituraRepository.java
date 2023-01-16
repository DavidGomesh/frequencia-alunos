package com.ifma.frequencia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifma.frequencia.domain.model.LogLeitura;
import com.ifma.frequencia.domain.repository.queries.LogLeituraQuery;

@Repository
public interface LogLeituraRepository extends 
    JpaRepository<LogLeitura, Integer>,
    LogLeituraQuery {

}
