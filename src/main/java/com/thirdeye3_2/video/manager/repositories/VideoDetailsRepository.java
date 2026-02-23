package com.thirdeye3_2.video.manager.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.thirdeye3_2.video.manager.entities.VideoDetails;

public interface VideoDetailsRepository extends JpaRepository<VideoDetails, UUID> {
	 List<VideoDetails> findAllByVideoId(UUID videoId);
}
