package com.thirdeye3_2.video.manager.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.thirdeye3_2.video.manager.entities.Video;

public interface VideoRepository extends JpaRepository<Video, UUID> {
	List<Video> findAllByOrderByCreatedDateTimeDesc();
	List<Video> findByIsCompletedFalseOrderByCreatedDateTimeDesc();
}