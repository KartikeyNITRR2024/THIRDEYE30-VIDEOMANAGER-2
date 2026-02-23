package com.thirdeye3_2.video.manager.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thirdeye3_2.video.manager.entities.AudioGenerate;
import com.thirdeye3_2.video.manager.enums.TableName;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AudioGenerateRepository 
        extends JpaRepository<AudioGenerate, UUID> {

    List<AudioGenerate> findByIsAudioGeneratedFalse();
    
    Optional<AudioGenerate> findByTableNameAndForeignKey(TableName tableName, UUID foreignKey);
    
    @Modifying
    @Transactional
    @Query("""
           DELETE FROM AudioGenerate a
           WHERE a.createdTime < :threshold
           AND a.autoDelete = true
           """)
    int deleteOldAutoDeletable(
            @Param("threshold") LocalDateTime threshold);
}
