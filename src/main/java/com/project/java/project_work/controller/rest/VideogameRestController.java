package com.project.java.project_work.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.java.project_work.model.Videogame;
import com.project.java.project_work.service.VideogameService;

@RestController
@RequestMapping("api/videogames")
public class VideogameRestController {

    @Autowired
    private VideogameService videogameService;

    @GetMapping
    public List<Videogame> index(){
        return videogameService.getAllVideogames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videogame> show(@PathVariable Integer id){


        if(videogameService.existById(id)){
            return new ResponseEntity<Videogame>(videogameService.getVideogameById(id), HttpStatus.OK);
        }

        return new ResponseEntity<Videogame>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Videogame> store(@RequestBody Videogame videogame){

       
        return new ResponseEntity<Videogame>(videogameService.createVideogame(videogame), HttpStatus.OK);


       
    }


    @PutMapping("/{id}")
    public ResponseEntity<Videogame> update(@RequestBody Videogame videogame, @PathVariable Integer id){
        if (videogameService.existById(id)) {
            videogame.setId(id);
            return new ResponseEntity<Videogame>(videogameService.updateVideogame(videogame), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    
    @DeleteMapping("/{id}")
    public ResponseEntity<Videogame> delete(@PathVariable Integer id){
        if (videogameService.existById(id)) {
            videogameService.deleteVideogame(id);
            return new ResponseEntity<Videogame>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
