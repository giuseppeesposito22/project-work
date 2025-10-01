package com.project.java.project_work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.java.project_work.service.VideogameService;

@Controller
@RequestMapping("/videogames")
public class VideogameController {

    @Autowired
    private VideogameService videogameService;




    @GetMapping
    public String index(Model model){

        model.addAttribute("videogames", videogameService.getAllVideogames());

        return "videogames/index";
    }
}
