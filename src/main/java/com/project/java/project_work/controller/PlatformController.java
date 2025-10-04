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

import com.project.java.project_work.model.Platform;
import com.project.java.project_work.service.PlatformService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/platforms")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("platforms", platformService.getAllPlatforms());
        model.addAttribute("platform", new Platform());
        return "platforms/index";
    }




    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("platform") Platform platform, 
    BindingResult bindingResult,
    @RequestParam(name = "file", required = false) MultipartFile file, Model model) throws IOException{

        if (bindingResult.hasErrors()) {
            model.addAttribute("platforms", platformService.getAllPlatforms());
            return "platforms/index";
        }


        if (!file.isEmpty()) {
            platform.setImg(file.getOriginalFilename());
            Path uploadPath = Paths.get("uploads/img/");
            Files.createDirectories(uploadPath); 
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        platformService.createPlatform(platform);
        return "redirect:/platforms";
    }

  

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, 
    @RequestParam(name = "file", required = false) MultipartFile file,
    @ModelAttribute("platform") Platform platform ) throws IOException{

        Platform oldPlatform = platformService.getPlatformById(id);

        if (!file.isEmpty()) {
            platform.setImg(file.getOriginalFilename());
            Path uploadPath = Paths.get("uploads/img/");
            Files.createDirectories(uploadPath); 
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } else{
            platform.setImg(oldPlatform.getImg());
        }

        platformService.updatePlatform(platform);
        return "redirect:/platforms";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) throws IOException{

        Platform platform = platformService.getPlatformById(id);

        platformService.deletePlatform(id);
        Path imgPath = Paths.get("uploads/img/" + platform.getImg());
        Files.deleteIfExists(imgPath);

        return "redirect:/platforms";
    }
}
