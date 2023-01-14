package com.ifma.frequencia.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifma.frequencia.api.dto.request.AlunoRequest;

@Controller
@RequestMapping("alunos")
public class TelaAlunoController {

    @GetMapping("cadastrar")
    public ModelAndView cadastrar(){

        ModelAndView mv = new ModelAndView("alunos/cadastrar");
        mv.addObject("pageTitle", "Cadastrar aluno");
        mv.addObject("pageDescription", "Cadastre o aluno para poder lhe atribuir um cartão.");
        
        AlunoRequest alunoRequest = new AlunoRequest();
        mv.addObject("aluno", alunoRequest);

        return mv;

    }
}
