package com.ifma.frequencia.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ifma.frequencia.domain.model.Curso;
import com.ifma.frequencia.domain.repository.CursoRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("app/alunos")
public class TelaAlunoController {

    private final CursoRepository cursoRepository;

    @GetMapping("cadastrar")
    public ModelAndView cadastrar(@RequestParam(name = "codigo", required = false) String codigo){

        List<Curso> cursos = cursoRepository.findAll();

        ModelAndView mv = new ModelAndView("alunos/cadastrar");
        mv.addObject("pageTitle", "Cadastrar aluno");
        mv.addObject("pageDescription", "Cadastre o aluno para poder fazer a contagem de horas.");

        mv.addObject("codigo", codigo);
        mv.addObject("cursos", cursos);
        return mv;

    }
}
