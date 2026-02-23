package com.thirdeye3_2.video.manager.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.dtos.VideoDetailsDto;
import com.thirdeye3_2.video.manager.entities.VideoDetails;
import com.thirdeye3_2.video.manager.repositories.VideoDetailsRepository;
import com.thirdeye3_2.video.manager.services.CurrentVideoService;
import com.thirdeye3_2.video.manager.services.VideoDetailsService;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
public class VideoDetailsServiceImpl implements VideoDetailsService {

    private static final Logger log =
            LoggerFactory.getLogger(VideoDetailsServiceImpl.class);

    @Autowired
    private VideoDetailsRepository repository;
    
    @Autowired
    private CurrentVideoService currentVideoService;

    @Override
    public VideoDetailsDto create(VideoDetailsDto dto) {
    	dto.setVideoId(currentVideoService.getCurrentVideo().getVideoId());
        log.info("Creating VideoDetails for videoId={}", dto.getVideoId());

        VideoDetails saved =
                repository.save(Mapper.toEntity(dto));

        log.info("VideoDetails created successfully | id={}", saved.getId());

        return Mapper.toDto(saved);
    }

    @Override
    public VideoDetailsDto getById(UUID id) {
        log.info("Fetching VideoDetails | id={}", id);

        VideoDetails entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("VideoDetails not found | id={}", id);
                    return new ResourceNotFoundException("VideoDetails not found");
                });

        return Mapper.toDto(entity);
    }

    @Override
    public List<VideoDetailsDto> getAll() {
        log.info("Fetching all VideoDetails");

        List<VideoDetailsDto> list =
                repository.findAll()
                        .stream()
                        .map(Mapper::toDto)
                        .collect(Collectors.toList());

        log.info("Total VideoDetails fetched = {}", list.size());

        return list;
    }

    @Override
    public VideoDetailsDto update(UUID id, VideoDetailsDto dto) {
        log.info("Updating VideoDetails | id={}", id);

        VideoDetails entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("VideoDetails not found for update | id={}", id);
                    return new ResourceNotFoundException("VideoDetails not found");
                });

        entity.setIntroHeader(dto.getIntroHeader());
        entity.setIntroSubHeader(dto.getIntroSubHeader());
        entity.setHeader(dto.getHeader());
        entity.setOutroHeader(dto.getOutroHeader());
        entity.setOutroSubHeader(dto.getOutroSubHeader());

        VideoDetails updated = repository.save(entity);

        log.info("VideoDetails updated successfully | id={}", id);

        return Mapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting VideoDetails | id={}", id);

        repository.deleteById(id);

        log.info("VideoDetails deleted successfully | id={}", id);
    }
    
    @Override
    public List<VideoDetailsDto> getByVideoId(UUID videoId) {

        log.info("Fetching all VideoDetails for videoId={}", videoId);

        List<VideoDetailsDto> list = repository
                .findAllByVideoId(videoId)
                .stream()
                .map(Mapper::toDto)
                .toList();

        if (list.isEmpty()) {
            log.warn("No VideoDetails found for videoId={}", videoId);
        } else {
            log.info("Total VideoDetails found for videoId={} is {}",
                    videoId, list.size());
        }

        return list;
    }

    @Override
    public void updateBarGraphJsonMultiMediaKey(UUID videoId, UUID key) {

        log.info("Updating barGraphJsonMultiMediaKey for videoId={} with key={}",
                videoId, key);

        List<VideoDetails> list = repository.findAllByVideoId(videoId);

        if (list.isEmpty()) {
            log.warn("No VideoDetails found for videoId={}", videoId);
            return;
        }
        
        for (VideoDetails videoDetails : list) {
            videoDetails.setBarGraphJsonMultiMediaKey(key);
            videoDetails.setIsbarGraphJsonMultiMediaKeyUploaded(Boolean.TRUE);
        }
        repository.saveAll(list);

        log.info("Updated barGraphJsonMultiMediaKey for {} VideoDetails records",
                list.size());
    }
}
