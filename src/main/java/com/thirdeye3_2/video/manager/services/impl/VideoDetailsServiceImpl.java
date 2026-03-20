package com.thirdeye3_2.video.manager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.dtos.AudioGenerateDto;
import com.thirdeye3_2.video.manager.dtos.VideoDetailsDto;
import com.thirdeye3_2.video.manager.entities.VideoDetails;
import com.thirdeye3_2.video.manager.enums.TableName;
import com.thirdeye3_2.video.manager.repositories.VideoDetailsRepository;
import com.thirdeye3_2.video.manager.services.AudioGenerateService;
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
    
    @Autowired
    private AudioGenerateService audioGenerateService;

    @Override
    public VideoDetailsDto create(VideoDetailsDto dto) {
    	dto.setVideoId(currentVideoService.getCurrentVideo().getVideoId());
        log.info("Creating VideoDetails for videoId={}", dto.getVideoId());
        VideoDetails videoDetails = Mapper.toEntity(dto);
        videoDetails.setCreatedTime(LocalDateTime.now());
        videoDetails.setIsBarGraphFooterPresent(false);
        VideoDetails saved =
                repository.save(videoDetails);

        log.info("VideoDetails created successfully | id={}", saved.getId());

        String createdIntroString = null;
        String createdOutroString = null;
        
        if(saved.getIsIntroAudioStringPresent())
        {
        	createdIntroString = audioGenerateService.create(new AudioGenerateDto(null, TableName.VIDEODETAILS_INTRO, saved.getId(), null, null, Boolean.FALSE, null, Boolean.TRUE, dto.getIntroAudioString())).getContent();
        }
        if(saved.getIsOutroAudioStringPresent())
        {
        	createdOutroString = audioGenerateService.create(new AudioGenerateDto(null, TableName.VIDEODETAILS_OUTRO, saved.getId(), null, null, Boolean.FALSE, null, Boolean.TRUE, dto.getOutroAudioString())).getContent();
        }
        
        return Mapper.toDto(saved, createdIntroString, createdOutroString);
    }

    @Override
    public VideoDetailsDto getById(UUID id) {
        log.info("Fetching VideoDetails | id={}", id);

        VideoDetails entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("VideoDetails not found | id={}", id);
                    return new ResourceNotFoundException("VideoDetails not found");
                });
        
        String createdIntroString = null;
        String createdOutroString = null;
        AudioGenerateDto dto = null;
        
        if(entity.getIsIntroAudioStringPresent())
        {
        	dto = audioGenerateService.getByTableAndForeignKeyForInternalUse(TableName.VIDEODETAILS_INTRO, id);
        	createdIntroString = (dto==null ? null : dto.getContent());
        }
        if(entity.getIsOutroAudioStringPresent())
        {
        	dto = audioGenerateService.getByTableAndForeignKeyForInternalUse(TableName.VIDEODETAILS_OUTRO, id);
        	createdOutroString = (dto==null ? null : dto.getContent());
        }
        return Mapper.toDto(entity, createdIntroString, createdOutroString);
    }

    @Override
    public List<VideoDetailsDto> getAll() {
        log.info("Fetching all VideoDetails");

        List<VideoDetailsDto> list = repository.findAllByOrderByCreatedTimeDesc()
                .stream()
                .map(entity -> {
                    String createdIntroString = null;
                    String createdOutroString = null;
                    AudioGenerateDto dto = null;
                    if(entity.getIsIntroAudioStringPresent())
                    {
                    	dto = audioGenerateService.getByTableAndForeignKeyForInternalUse(TableName.VIDEODETAILS_INTRO, entity.getId());
                    	createdIntroString = (dto==null ? null : dto.getContent());
                    }
                    if(entity.getIsOutroAudioStringPresent())
                    {
                    	dto = audioGenerateService.getByTableAndForeignKeyForInternalUse(TableName.VIDEODETAILS_OUTRO, entity.getId());
                    	createdOutroString = (dto==null ? null : dto.getContent());
                    }
                    return Mapper.toDto(entity, createdIntroString, createdOutroString);
                })
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
        entity.setIsbarGraphJsonMultiMediaKeyUploaded(dto.getIsbarGraphJsonMultiMediaKeyUploaded());
        entity.setBarGraphJsonMultiMediaKey(dto.getBarGraphJsonMultiMediaKey());
        entity.setIsBarGraphFooterPresent(dto.getIsBarGraphFooterPresent());
        entity.setBarGraphRaceFooter(dto.getBarGraphRaceFooter());
        entity.setOutroHeader(dto.getOutroHeader());
        entity.setOutroSubHeader(dto.getOutroSubHeader());
        entity.setIsIntroAudioStringPresent(dto.getIsIntroAudioStringPresent());
        entity.setIsIntroAudioStringUploaded(dto.getIsIntroAudioStringUploaded());
        entity.setIntroAudioMultiMediaKey(dto.getIntroAudioMultiMediaKey());
        entity.setIsOutroAudioStringPresent(dto.getIsOutroAudioStringPresent());
        entity.setIsOutroAudioStringUploaded(dto.getIsOutroAudioStringUploaded());
        entity.setOutroAudioMultiMediaKey(dto.getOutroAudioMultiMediaKey());
        entity.setIsBarGraphFooterPresent(false);

        VideoDetails updated = repository.save(entity);

        log.info("VideoDetails updated successfully | id={}", id);
        
        String createdIntroString = null;
        String createdOutroString = null;
        AudioGenerateDto dto1 = null;
        if(entity.getIsIntroAudioStringPresent())
        {
        	dto1 = audioGenerateService.getByTableAndForeignKey(TableName.VIDEODETAILS_INTRO, id);
        	createdIntroString = (dto1==null ? null : dto1.getContent());
        }
        if(entity.getIsOutroAudioStringPresent())
        {
        	dto1 = audioGenerateService.getByTableAndForeignKey(TableName.VIDEODETAILS_OUTRO, id);
        	createdOutroString = (dto1==null ? null : dto1.getContent());
        }
        return Mapper.toDto(updated, createdIntroString, createdOutroString);
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

        List<VideoDetailsDto> list = repository.findAllByVideoId(videoId)
                .stream()
                .map(entity -> {
                    String createdIntroString = null;
                    String createdOutroString = null;
                    AudioGenerateDto dto = null;
                    if(entity.getIsIntroAudioStringPresent())
                    {
                    	dto = audioGenerateService.getByTableAndForeignKeyForInternalUse(TableName.VIDEODETAILS_INTRO, entity.getId());
                    	createdIntroString = (dto==null ? null : dto.getContent());
                    }
                    if(entity.getIsOutroAudioStringPresent())
                    {
                    	dto = audioGenerateService.getByTableAndForeignKeyForInternalUse(TableName.VIDEODETAILS_OUTRO, entity.getId());
                    	createdOutroString = (dto==null ? null : dto.getContent());
                    }

                    return Mapper.toDto(entity, createdIntroString, createdOutroString);
                })
                .toList();

        if (list.isEmpty()) {
            log.warn("No VideoDetails found for videoId={}", videoId);
        } else {
            log.info("Total VideoDetails found for videoId={} is {}", videoId, list.size());
        }

        return list;
    }

    @Override
    public void updateBarGraphJsonMultiMediaKey(UUID videoId, UUID key, TableName tableName) {

        log.info("Updating barGraphJsonMultiMediaKey for videoId={} with key={}",
                videoId, key);
        
        List<VideoDetails> list = null;
        
        if(tableName.equals(TableName.VIDEODETAILS_BARGAPH))
    	{
        	list = repository.findAllByVideoId(videoId);
    	}
        else
        {
        	Optional<VideoDetails> opt = repository.findById(videoId);
        	if(opt.isPresent())
        	{
        		list = List.of(opt.get());
        	}
        	
        }

        if (list == null || list.isEmpty()) {
            log.warn("No VideoDetails found for videoId={}", videoId);
            return;
        }
        
        for (VideoDetails videoDetails : list) {
        	if(tableName.equals(TableName.VIDEODETAILS_BARGAPH))
        	{
                videoDetails.setBarGraphJsonMultiMediaKey(key);
                videoDetails.setIsbarGraphJsonMultiMediaKeyUploaded(Boolean.TRUE);
        	}
        	else if(tableName.equals(TableName.VIDEODETAILS_INTRO))
        	{
                videoDetails.setIntroAudioMultiMediaKey(key);
                videoDetails.setIsIntroAudioStringUploaded(Boolean.TRUE);
        	}
        	else if(tableName.equals(TableName.VIDEODETAILS_OUTRO))
        	{
                videoDetails.setOutroAudioMultiMediaKey(key);
                videoDetails.setIsOutroAudioStringUploaded(Boolean.TRUE);
        	}
        }
        repository.saveAll(list);

        log.info("Updated barGraphJsonMultiMediaKey for {} VideoDetails records",
                list.size());
    }
}
