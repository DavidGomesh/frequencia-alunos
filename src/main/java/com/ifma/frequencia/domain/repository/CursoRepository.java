package com.ifma.frequencia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifma.frequencia.domain.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    
}
