package com.thirdeye3_2.video.manager.services.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.entities.Video;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;
import com.thirdeye3_2.video.manager.repositories.VideoRepository;
import com.thirdeye3_2.video.manager.dtos.CurrentVideoDto;
import com.thirdeye3_2.video.manager.entities.CurrentVideo;
import com.thirdeye3_2.video.manager.repositories.CurrentVideoRepository;
import com.thirdeye3_2.video.manager.services.CurrentVideoService;
import com.thirdeye3_2.video.manager.utils.Mapper;

@Service
public class CurrentVideoServiceImpl implements CurrentVideoService {
	
    private static final Logger logger = LoggerFactory.getLogger(CurrentVideoServiceImpl.class);

    private static final Long SINGLETON_ID = 1L;
    
    @Autowired
    private CurrentVideoRepository currentVideoRepository;
    
    @Autowired
    private VideoRepository videoRepository;
 
	@Override
	public CurrentVideoDto getCurrentVideo() {
		logger.info("Fetching Ending configuration");

		CurrentVideo currentVideo = currentVideoRepository.findById(SINGLETON_ID)
                .orElseGet(() -> {
                    logger.info("Current Video not found. Creating new Current Video with id: {}", SINGLETON_ID);
                    CurrentVideo newCurrentVideo = new CurrentVideo();
                    newCurrentVideo.setId(SINGLETON_ID);
                    return currentVideoRepository.save(newCurrentVideo);
                });

        logger.info("Current Video fetched successfully with id: {}", currentVideo.getId());
        return Mapper.toDto(currentVideo);
	}

	@Override
	public CurrentVideoDto updateCurrentVideo(UUID uuid) {
		logger.info("Updating Ending configuration");

		CurrentVideo currentVideo = currentVideoRepository.findById(SINGLETON_ID)
                .orElseGet(() -> {
                	logger.info("Current Video not found. Creating new Current Video with id: {}", SINGLETON_ID);
                    CurrentVideo newCurrentVideo = new CurrentVideo();
                    newCurrentVideo.setId(SINGLETON_ID);
                    return currentVideoRepository.save(newCurrentVideo);
                });

        if (uuid != null) {
            logger.info("Linking Current video with videoId: {}", uuid);
            Video video = videoRepository.findById(uuid)
                    .orElseThrow(() -> {
                        logger.error("Video not found with id: {}", uuid);
                        return new ResourceNotFoundException("Video not found with ID: " + uuid);
                    });
            currentVideo.setVideoId(uuid);
        }

        CurrentVideo savedCurrentVideo = currentVideoRepository.save(currentVideo);
        logger.info("Current video updated successfully with id: {}", currentVideo.getId());

        return Mapper.toDto(savedCurrentVideo);
	}

}
