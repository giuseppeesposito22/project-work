package com.project.java.project_work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.java.project_work.model.Videogame;
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

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model){
        
        model.addAttribute("videogame", videogameService.getVideogameById(id));
        
        return "videogames/show";
    }

    @GetMapping("/create")
    public String create(Model model){
        
        model.addAttribute("videogame", new Videogame());
        
        return "videogames/create-update";    
    }
}
