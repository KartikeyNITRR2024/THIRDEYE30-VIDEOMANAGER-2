package com.thirdeye3_2.video.manager.repositories;

import com.thirdeye3_2.video.manager.entities.ContentGenerator;
import com.thirdeye3_2.video.manager.enums.GeneratorType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;


public interface ContentGeneratorRepository extends JpaRepository<ContentGenerator, UUID> {
    Optional<ContentGenerator> findByType(GeneratorType type);
}