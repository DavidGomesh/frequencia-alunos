package com.ifma.frequencia.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.HorasEstagio;
import com.ifma.frequencia.domain.repository.EstagioRespository;
import com.ifma.frequencia.domain.repository.HorasEstagioRepository;
import com.ifma.frequencia.domain.utils.Duracao;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("app/alunos")
public class TelaDetalhesHorasEstagio {

    private final EstagioRespository estagioRespository;
    private final HorasEstagioRepository horasEstagioRepository;

    @GetMapping("{aluno}/estagios")
    public ModelAndView telaDetalhes(@PathVariable(name = "aluno") Aluno aluno){

        Optional<Estagio> optEstagio = estagioRespository.findByAluno(aluno);
        Estagio estagio = optEstagio.orElseThrow();
        Duracao horasTotais = estagio.horasTotais();
        List<HorasEstagio> horasEstagio = horasEstagioRepository.findByEstagioOrderByDataRegistroDesc(estagio);

        ModelAndView mv = new ModelAndView("alunos/detalhes-horas-estagio");
        mv.addObject("pageTitle", "Detalhes de Estágio");
        mv.addObject("estagio", estagio);
        mv.addObject("horasTotais", horasTotais);
        mv.addObject("horasEstagio", horasEstagio);

        return mv;
    }
}
