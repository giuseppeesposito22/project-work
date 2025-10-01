package com.project.java.project_work.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "platforms")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome della piattaforma non puo' essere vuoto")
    private String name;

    @ManyToMany(mappedBy = "platforms")
    @JsonBackReference
    private List<Videogame> videogames;

    public Platform(){};

    public Platform(Integer id, @NotBlank(message = "Il nome della piattaforma non puo' essere vuoto") String name,
            List<Videogame> videogames) {
        this.id = id;
        this.name = name;
        this.videogames = videogames;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Videogame> getVideogames() {
        return videogames;
    }

    public void setVideogames(List<Videogame> videogames) {
        this.videogames = videogames;
    }

    

}
