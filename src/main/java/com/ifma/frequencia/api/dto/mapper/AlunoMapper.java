package com.ifma.frequencia.api.dto.mapper;

import org.springframework.stereotype.Component;

import com.ifma.frequencia.api.dto.request.AlunoRequest;
import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Pessoa;
import com.ifma.frequencia.domain.service.PessoaService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AlunoMapper {

    private final PessoaService pessoaService;
    
    public Aluno toEntity(AlunoRequest alunoRequest){

        Pessoa pessoa = pessoaService.buscarPorId(alunoRequest.getPessoa());

        Aluno aluno = new Aluno();
        aluno.setPessoa(pessoa);
        aluno.setMatricula(alunoRequest.getMatricula());

        return aluno;
    }
}
