package com.project.java.project_work.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.java.project_work.model.Videogame;

public interface VideogameRepository extends JpaRepository<Videogame, Integer>{

}
