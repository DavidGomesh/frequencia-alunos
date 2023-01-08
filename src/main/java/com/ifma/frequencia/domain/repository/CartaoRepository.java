package com.ifma.frequencia.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifma.frequencia.domain.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {
    
    Optional<Cartao> findByCodigo(String codigo);
}
