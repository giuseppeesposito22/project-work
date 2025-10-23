package com.project.java.project_work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.java.project_work.model.Platform;
import com.project.java.project_work.repository.PlatformRepository;

import jakarta.transaction.Transactional;

@Service
public class PlatformService {

    @Autowired
    private PlatformRepository platformRepository;

    public List<Platform> getAllPlatforms(){
        return platformRepository.findAll();
    }

    public Platform getPlatformById(Integer id){
        return platformRepository.findById(id).get();
    }

    public Platform createPlatform(Platform videogame){
        return platformRepository.save(videogame);
    }

    public Platform updatePlatform(Platform videogame){
        return platformRepository.save(videogame);
    }

    @Transactional
    public void deletePlatform(Integer id){
        platformRepository.deleteLinksByPlatformId(id);
        platformRepository.deleteById(id);
    }

    public boolean existById(Integer id){
        return platformRepository.existsById(id);
    }

    public boolean exist(Platform platform){
        return platformRepository.existsById(platform.getId());
    }

  


}
