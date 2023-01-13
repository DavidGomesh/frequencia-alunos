package com.ifma.frequencia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifma.frequencia.domain.model.Estagio;

@Repository
public interface EstagioRespository extends JpaRepository<Estagio, Integer> {
    
}
