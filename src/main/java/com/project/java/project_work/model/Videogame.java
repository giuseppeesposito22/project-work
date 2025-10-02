package com.project.java.project_work.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
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

    private String img;

    @Lob
    private String description;

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

    public Videogame(){};

    
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }



    public String getImg() {
        return img;
    }



    public void setImg(String img) {
        this.img = img;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    
    


}
