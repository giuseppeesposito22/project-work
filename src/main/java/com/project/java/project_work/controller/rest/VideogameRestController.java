package com.project.java.project_work.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.project.java.project_work.model.VideogameResponse;
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
    public ResponseEntity<?> show(@PathVariable Integer id){

        try {

            Videogame videogame = videogameService.getVideogameById(id);

            VideogameResponse response = new VideogameResponse(videogame, "Trovato Vg con id: " + id);

            return new ResponseEntity<>(response, HttpStatus.OK);
     
            
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            
            response.put("err", e.getMessage());
            response.put("message", "Elemento non trovato");

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }


        
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody Videogame videogame){

        try {
            Videogame newVideogame = videogameService.createVideogame(videogame);
            VideogameResponse response = new VideogameResponse(newVideogame, "Vg creato correttamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("err", e.getMessage());
            response.put("message", "Errore nella creazione del Vg");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
       
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Videogame videogame, @PathVariable Integer id){

        try {
            videogame.setId(id);
            return new ResponseEntity<Videogame>(videogameService.updateVideogame(videogame), HttpStatus.OK);
            
        } catch (Exception e) {
         
            Map<String, String> response = new HashMap<>();
            response.put("err", e.getMessage());
            response.put("message", "Elemento non trovato");
    
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

      
    }


    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){

        if (videogameService.existById(id)) {
            
            videogameService.deleteVideogame(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Vg eliminato correttamente");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

     
            Map<String, String> response = new HashMap<>();
            response.put("message", "Vg non trovato");
           

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);


    }

}
