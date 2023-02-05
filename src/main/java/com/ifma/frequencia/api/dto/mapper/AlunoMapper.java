package com.ifma.frequencia.api.dto.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.api.dto.request.AlunoRequest;
import com.ifma.frequencia.api.dto.response.AlunoResponse;
import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Curso;
import com.ifma.frequencia.domain.repository.CursoRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AlunoMapper {

    private final CursoRepository cursoRepository;

    public Aluno toEntity(AlunoRequest alunoRequest){

        Optional<Curso> optCurso = cursoRepository.findById(alunoRequest.getCurso());
        Curso curso = optCurso.orElseThrow();
        
        Aluno aluno = new Aluno();
        aluno.setNome(alunoRequest.getNome());
        aluno.setCurso(curso);
        aluno.setCartao(alunoRequest.getCartao());

        return aluno;
    }

    public AlunoResponse toResponse(Aluno aluno){
        AlunoResponse alunoResponse = new AlunoResponse();
        alunoResponse.setNome(aluno.getNome());
        alunoResponse.setCurso(aluno.getCurso().getNome());
        alunoResponse.setCartao(aluno.getCartao());
        return alunoResponse;
    }
}
