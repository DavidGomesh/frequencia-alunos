package com.ifma.frequencia.domain.service;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.repository.AlunoRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    
    public Aluno salvar(@Valid Aluno aluno){
        return alunoRepository.save(aluno);
    }
}
