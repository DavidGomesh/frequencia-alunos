package com.ifma.frequencia.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ifma.frequencia.domain.model.LogLeitura;
import com.ifma.frequencia.domain.repository.LogLeituraRepository;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    private final LogLeituraRepository logRepository;
    
    @GetMapping
    public ModelAndView home(){

        List<LogLeitura> logs = logRepository.buscarUltimos();

        ModelAndView mv = new ModelAndView("dashboard");
        mv.addObject("pageTitle", "Dashboard");
        mv.addObject("logs", logs);
        
        return mv;
    }
}
