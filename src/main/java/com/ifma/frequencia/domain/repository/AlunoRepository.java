package com.ifma.frequencia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.repository.queries.AlunoQuery;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>, AlunoQuery {
    
}
