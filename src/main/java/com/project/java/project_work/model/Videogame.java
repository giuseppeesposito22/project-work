package com.project.java.project_work.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "vdeogames")
public class Videogame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome del videogioco non puo' essere vuoto")
    private String name;

    @NotNull(message = "La data di rilascio non puo' essere null")
    private LocalDate releaseDate;

    @NotNull(message = "Il prezzo del videogame non puo' essere null")
    private Double price;

    @ManyToMany
    @JoinTable(name = "platform_videogame",
    joinColumns = @JoinColumn(name = " videogame_id"),
    inverseJoinColumns = @JoinColumn(name ="platform_id") )
    private List<Platform> platforms;



}
