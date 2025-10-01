package com.project.java.project_work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.java.project_work.model.Videogame;
import com.project.java.project_work.repository.VideogameRepository;

@Service
public class VideogameService {

    @Autowired
    private VideogameRepository videogameRepository;

    public List<Videogame> getAllVideogames(){
        return videogameRepository.findAll();
    }

    public Videogame getVideogameById(Integer id){
        return videogameRepository.findById(id).get();
    }

    public Videogame createVideogame(Videogame videogame){
        return videogameRepository.save(videogame);
    }

    public Videogame updateVideogame(Videogame videogame){
        return videogameRepository.save(videogame);
    }

    public void deleteVideogame(Integer id){
        videogameRepository.deleteById(id);
    }

    public boolean existById(Integer id){
        return videogameRepository.existsById(id);
    }

    public boolean exist(Videogame videogame){
        return videogameRepository.existsById(videogame.getId());
    }
}
