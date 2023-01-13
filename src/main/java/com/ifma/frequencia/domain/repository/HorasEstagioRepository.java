package com.ifma.frequencia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifma.frequencia.domain.model.HorasEstagio;
import com.ifma.frequencia.domain.repository.queries.HorasEstagioQuery;

@Repository
public interface HorasEstagioRepository extends 
    JpaRepository<HorasEstagio, Integer>,
    HorasEstagioQuery {
    
}
