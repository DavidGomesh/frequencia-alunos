package com.ifma.frequencia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifma.frequencia.domain.model.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {
    
}
