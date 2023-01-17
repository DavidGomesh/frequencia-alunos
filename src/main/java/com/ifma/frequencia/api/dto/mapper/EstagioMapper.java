package com.ifma.frequencia.api.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.api.dto.response.AlunoResponse;
import com.ifma.frequencia.api.dto.response.EstagioResponse;
import com.ifma.frequencia.domain.model.Estagio;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EstagioMapper {

    private final AlunoMapper alunoMapper;
    
    public EstagioResponse toResponse(Estagio estagio){

        AlunoResponse aluno = alunoMapper.toResponse(estagio.getAluno());

        EstagioResponse estagioResponse = new EstagioResponse();
        estagioResponse.setAluno(aluno);
        estagioResponse.setHorasTotais(estagio.horasTotais());

        return estagioResponse;
    }

    public List<EstagioResponse> toResponseList(List<Estagio> estagios){
        return (estagios.stream()
            .map(this::toResponse)
            .collect(Collectors.toList())
        );
    }
}
