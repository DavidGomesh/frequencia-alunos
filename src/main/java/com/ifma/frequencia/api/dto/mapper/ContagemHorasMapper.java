package com.ifma.frequencia.api.dto.mapper;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.api.dto.request.ContagemHorasRequest;
import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.ContagemHoras;
import com.ifma.frequencia.domain.service.AlunoService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ContagemHorasMapper {

    private final AlunoService alunoService;
    
    public ContagemHoras toEntity(ContagemHorasRequest contagemHorasRequest){

        Aluno aluno = alunoService.buscarPorId(contagemHorasRequest.getAluno());

        ContagemHoras contagemHoras = new ContagemHoras();
        contagemHoras.setTipoContagem(contagemHorasRequest.getTipoContagem());
        contagemHoras.setAluno(aluno);
        contagemHoras.setHoraInicio(contagemHorasRequest.getHoraInicio());

        return contagemHoras;
    }
}
