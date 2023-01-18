package com.ifma.frequencia.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("app/alunos")
public class TelaAlunoController {

    @GetMapping("cadastrar")
    public ModelAndView cadastrar(@RequestParam(name = "codigo", required = false) String codigo){

        ModelAndView mv = new ModelAndView("alunos/cadastrar");
        mv.addObject("pageTitle", "Cadastrar aluno");
        mv.addObject("pageDescription", "Cadastre o aluno para poder fazer a contagem de horas.");

        mv.addObject("codigo", codigo);
        return mv;

    }
}
