package com.thirdeye3_2.video.manager.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thirdeye3_2.video.manager.entities.ContentVideo;

public interface ContentVideoRepository extends JpaRepository<ContentVideo, UUID> {

    Optional<ContentVideo> findByActiveTrue();
}
