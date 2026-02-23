package com.thirdeye3_2.video.manager.repositories;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thirdeye3_2.video.manager.entities.MultiMedia;
import java.time.LocalDateTime;
import java.util.List;

public interface MultiMediaRepository extends JpaRepository<MultiMedia, UUID> {
	List<MultiMedia> findByAutoDeleteTrueAndLastUsedBefore(LocalDateTime time);
}