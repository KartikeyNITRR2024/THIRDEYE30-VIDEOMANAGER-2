package com.thirdeye3_2.video.manager.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thirdeye3_2.video.manager.entities.NewsImage;

public interface NewsImageRepository extends JpaRepository<NewsImage, UUID> {

    Optional<NewsImage> findByActiveTrue();
}
