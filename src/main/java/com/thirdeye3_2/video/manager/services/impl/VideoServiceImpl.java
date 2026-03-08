package com.thirdeye3_2.video.manager.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.dtos.VideoDto;
import com.thirdeye3_2.video.manager.entities.Video;
import com.thirdeye3_2.video.manager.repositories.VideoRepository;
import com.thirdeye3_2.video.manager.services.VideoService;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
public class VideoServiceImpl implements VideoService {

    private static final Logger log =
            LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public VideoDto create(VideoDto dto) {
        log.info("Creating new video | name={} | type={}",
                dto.getName(), dto.getTypeOfVideo());
        
        if(!dto.getAdsPresent())
        {
        	dto.setAdsId(null);
        }

        Video saved = videoRepository.save(Mapper.toEntity(dto));

        log.info("Video created successfully | id={}", saved.getId());

        return Mapper.toDto(saved);
    }

    @Override
    public VideoDto getById(UUID id) {
        log.info("Fetching video | id={}", id);

        Video video = videoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Video not found | id={}", id);
                    return new ResourceNotFoundException("Video not found");
                });

        return Mapper.toDto(video);
    }

    @Override
    public List<VideoDto> getAll() {
        log.info("Fetching all videos");

        List<VideoDto> list = videoRepository.findAllByOrderByCreatedDateTimeDesc()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());

        log.info("Total videos fetched = {}", list.size());

        return list;
    }

    @Override
    public VideoDto update(UUID id, VideoDto dto) {
        log.info("Updating video | id={}", id);

        Video video = videoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Video not found for update | id={}", id);
                    return new ResourceNotFoundException("Video not found");
                });

        if(!dto.getAdsPresent())
        {
        	dto.setAdsId(null);
        }
        video.setName(dto.getName());
        video.setDescription(dto.getDescription());
        video.setDateOfUpload(dto.getDateOfUpload());
        video.setTypeOfVideo(dto.getTypeOfVideo());
        video.setStockGroup(dto.getStockGroup());
        video.setAdsPresent(dto.getAdsPresent());
        video.setAdsId(dto.getAdsId());
        video.setIsCompleted(dto.getIsCompleted());
        video.setVideoMultiMediaKey(dto.getVideoMultiMediaKey());
        Video updated = videoRepository.save(video);
        log.info("Video updated successfully | id={}", id);
        return Mapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting video | id={}", id);

        videoRepository.deleteById(id);

        log.info("Video deleted successfully | id={}", id);
    }
    
    @Override
    public List<VideoDto> getPendingVideos() {
        log.info("Fetching all incomplete videos...");
        return videoRepository.findByIsCompletedFalseOrderByCreatedDateTimeDesc()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public void updateIsCompleted(UUID id, Boolean isCompleted, UUID videoMultiMediaKey)
    {
    	 log.info("Updating video iscompleted | id={}", id);
         Video video = videoRepository.findById(id)
                 .orElseThrow(() -> {
                     log.error("Video not found for update | id={}", id);
                     return new ResourceNotFoundException("Video not found");
                 });
         video.setIsCompleted(isCompleted);
         video.setVideoMultiMediaKey(videoMultiMediaKey);
         Video updated = videoRepository.save(video);
         log.info("Video updated successfully | id={}", id);
    }
}