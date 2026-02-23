package com.thirdeye3_2.video.manager.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.thirdeye3_2.video.manager.entities.News;

public interface NewsRepository extends JpaRepository<News, UUID> {

    List<News> findByVideoDetailsId(UUID videoDetailsId);
    
    @Modifying
    @Transactional
    @Query("""
           DELETE FROM News n 
           WHERE n.createdTime < :threshold 
           AND n.autoDelete = true
           """)
    int deleteOldAutoDeletableNews(
            @Param("threshold") LocalDateTime threshold);
}
