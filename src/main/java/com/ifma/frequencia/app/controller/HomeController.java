package com.ifma.frequencia.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    @GetMapping
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("dashboard");
        mv.addObject("pageTitle", "Dashboard");
        return mv;
    }
}
