package com.ifma.frequencia.api.dto.mapper;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.api.dto.request.AlunoRequest;
import com.ifma.frequencia.api.dto.response.AlunoResponse;
import com.ifma.frequencia.api.dto.response.PessoaResponse;
import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Pessoa;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AlunoMapper {

    private final PessoaMapper pessoaMapper;
    
    public Aluno toEntity(AlunoRequest alunoRequest){

        Pessoa pessoa = pessoaMapper.toEntity(alunoRequest.getPessoa());

        Aluno aluno = new Aluno();
        aluno.setPessoa(pessoa);
        aluno.setMatricula(alunoRequest.getMatricula());

        return aluno;
    }

    public AlunoResponse toResponse(Aluno aluno){

        PessoaResponse pessoa = pessoaMapper.toResponse(aluno.getPessoa());

        AlunoResponse alunoResponse = new AlunoResponse();
        alunoResponse.setPessoa(pessoa);
        alunoResponse.setMatricula(aluno.getMatricula());
        
        return alunoResponse;
    }
}
