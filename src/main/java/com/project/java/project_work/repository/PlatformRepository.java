package com.project.java.project_work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.java.project_work.model.Platform;

import jakarta.transaction.Transactional;

public interface PlatformRepository extends JpaRepository<Platform, Integer>{

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value = "DELETE FROM platform_videogame WHERE platform_id = :platformId", nativeQuery = true)
    void deleteLinksByPlatformId(@Param("platformId") Integer platformId);

}
