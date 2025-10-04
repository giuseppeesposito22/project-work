package com.project.java.project_work.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
     BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file
    ) throws IOException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("platforms", platformService.getAllPlatforms());
            return "/videogames/create-update";
        }


        
        if (!file.isEmpty()) {
            
            videogame.setImg(file.getOriginalFilename());
            Path uploadPath = Paths.get("uploads/img/");
            Files.createDirectories(uploadPath);
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        

        videogameService.createVideogame(videogame);


        return "redirect:/videogames";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){

        model.addAttribute("videogame", videogameService.getVideogameById(id));
        model.addAttribute("platforms", platformService.getAllPlatforms());
        model.addAttribute("edit", true);



        return "videogames/create-update";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, 
    @Valid @ModelAttribute("videogame") Videogame videogame, 
    BindingResult bindingResult, @RequestParam(name = "file", required = false) MultipartFile file, Model model) throws IOException{

        Videogame oldVideogame = videogameService.getVideogameById(id);

        if(bindingResult.hasErrors()){
            model.addAttribute("platforms", platformService.getAllPlatforms());
            model.addAttribute("edit", true);

            return "/videogames/create-update";
        }



        if (!file.isEmpty()) {
            
            videogame.setImg(file.getOriginalFilename());
            Path uploadPath = Paths.get("uploads/img/");
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } else{

            videogame.setImg(oldVideogame.getImg());
        }

        
        videogameService.updateVideogame(videogame);

        return "redirect:/videogames";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id) throws IOException{

        Videogame videogame = videogameService.getVideogameById(id);

        videogameService.deleteVideogame(id);
        Path imgPath = Paths.get("uploads/img/" + videogame.getImg());
        Files.deleteIfExists(imgPath);

        return "redirect:/videogames";
    }
}
