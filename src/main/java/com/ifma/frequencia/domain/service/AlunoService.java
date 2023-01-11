package com.ifma.frequencia.domain.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.ifma.frequencia.domain.exception.AlunoNotFoundException;
import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.repository.AlunoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    
    public Aluno salvar(@Valid Aluno aluno){
        return alunoRepository.save(aluno);
    }

    public Aluno buscarPorId(Integer idAluno){
        return alunoRepository.findById(idAluno).orElseThrow(() -> {
            throw new AlunoNotFoundException(idAluno);
        });
    }
}
