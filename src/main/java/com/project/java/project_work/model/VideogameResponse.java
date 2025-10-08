package com.project.java.project_work.model;

import java.io.Serializable;


public class VideogameResponse implements Serializable {
    private Videogame videogame;
    private String message;



    public VideogameResponse(Videogame videogame, String message){
        this.videogame = videogame;
        this.message = message;
    }

    public VideogameResponse(){}



    public Videogame getVideogame() {
        return videogame;
    }



    public void setVideogame(Videogame videogame) {
        this.videogame = videogame;
    }



    public String getMessage() {
        return message;
    }



    public void setMessage(String message) {
        this.message = message;
    }

   
}
