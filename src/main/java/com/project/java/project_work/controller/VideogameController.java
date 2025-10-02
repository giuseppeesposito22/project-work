package com.project.java.project_work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.java.project_work.model.Videogame;
import com.project.java.project_work.service.PlatformService;
import com.project.java.project_work.service.VideogameService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/videogames")
public class VideogameController {

    @Autowired
    private VideogameService videogameService;

    @Autowired
    private PlatformService platformService;


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
        model.addAttribute("platforms", platformService.getAllPlatforms());
        
        return "videogames/create-update";    
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("videogame") Videogame videogame, 
     BindingResult bindingResult, Model model 
    ){

        if (bindingResult.hasErrors()) {
            model.addAttribute("platforms", platformService.getAllPlatforms());
            return "/videogames/create-update";
        }

        videogameService.createVideogame(videogame);


        return "redirect:/videogames";
    }
}
