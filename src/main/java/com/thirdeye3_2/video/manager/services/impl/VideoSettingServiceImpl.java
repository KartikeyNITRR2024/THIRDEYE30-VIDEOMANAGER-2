package com.thirdeye3_2.video.manager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thirdeye3_2.video.manager.dtos.VideoSettingDto;
import com.thirdeye3_2.video.manager.entities.VideoSetting;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.VideoSettingRepository;
import com.thirdeye3_2.video.manager.services.VideoSettingService;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
public class VideoSettingServiceImpl implements VideoSettingService {

    private static final Logger log =
            LoggerFactory.getLogger(VideoSettingServiceImpl.class);

    @Autowired
    private VideoSettingRepository repository;

    @Override
    public VideoSettingDto create(VideoSettingDto dto) {

        log.info("Creating VideoSetting | name={}", dto.getName());

        VideoSetting entity = Mapper.toEntity(dto);
        entity.setActive(false);
        entity.setLastlyUsed(LocalDateTime.now());

        VideoSetting saved = repository.save(entity);

        log.info("VideoSetting created successfully | id={}", saved.getId());

        return Mapper.toDto(saved);
    }

    @Override
    public VideoSettingDto getById(UUID id) {

        log.info("Fetching VideoSetting | id={}", id);

        VideoSetting entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("VideoSetting not found | id={}", id);
                    return new ResourceNotFoundException("Setting not found");
                });

        return Mapper.toDto(entity);
    }

    @Override
    public List<VideoSettingDto> getAll() {

        log.info("Fetching all VideoSettings");

        List<VideoSettingDto> list = repository.findAll()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());

        log.info("Total VideoSettings found = {}", list.size());

        return list;
    }

    @Override
    public VideoSettingDto update(UUID id, VideoSettingDto dto) {

        log.info("Updating VideoSetting | id={}", id);

        VideoSetting entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("VideoSetting not found for update | id={}", id);
                    return new ResourceNotFoundException("Setting not found");
                });

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setFps(dto.getFps());
        entity.setHeight(dto.getHeight());
        entity.setWidth(dto.getWidth());
        entity.setIntroPresent(dto.getIntroPresent());
        entity.setIntroTime(dto.getIntroTime());
        entity.setMainVideoPresent(dto.getMainVideoPresent());
        entity.setMainVideoTime(dto.getMainVideoTime());
        entity.setOutroPresent(dto.getOutroPresent());
        entity.setOutroTime(dto.getOutroTime());
        entity.setSequence(dto.getSequence());
        entity.setIsAudio(dto.getIsAudio());
        entity.setAudioMultiMediaKey(dto.getAudioMultiMediaKey());
        entity.setAudioVolumne(dto.getAudioVolumne());
        VideoSetting updated = repository.save(entity);

        log.info("VideoSetting updated successfully | id={}", id);

        return Mapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {

        log.info("Deleting VideoSetting | id={}", id);

        if (!repository.existsById(id)) {
            log.error("VideoSetting not found for delete | id={}", id);
            throw new ResourceNotFoundException("Setting not found");
        }

        repository.deleteById(id);

        log.info("VideoSetting deleted successfully | id={}", id);
    }

    @Override
    @Transactional
    public VideoSettingDto makeActive(UUID id) {

        log.info("Activating VideoSetting | id={}", id);

        VideoSetting newActive = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("VideoSetting not found for activation | id={}", id);
                    return new ResourceNotFoundException("Setting not found");
                });

        VideoSetting currentActive = repository.findByActiveTrue();

        if (currentActive != null) {

            if (currentActive.getId().equals(id)) {
                log.info("VideoSetting already active | id={}", id);
                return Mapper.toDto(currentActive);
            }

            log.info("Deactivating current active setting | id={}",
                    currentActive.getId());

            currentActive.setActive(false);
            repository.save(currentActive);
        }

        newActive.setActive(true);
        newActive.setLastlyUsed(LocalDateTime.now());

        VideoSetting saved = repository.save(newActive);

        log.info("VideoSetting activated successfully | id={}", id);

        return Mapper.toDto(saved);
    }
    
    @Override
    @Transactional
    public VideoSettingDto getActiveSetting() {

        log.info("Fetching active VideoSetting");

        VideoSetting activeSetting = repository.findByActiveTrue();

        if (activeSetting == null) {
            log.error("No active VideoSetting found");
            throw new ResourceNotFoundException("No active VideoSetting found");
        }

        activeSetting.setLastlyUsed(LocalDateTime.now());
        repository.save(activeSetting);

        log.info("Active VideoSetting fetched successfully | id={}",
                activeSetting.getId());

        return Mapper.toDto(activeSetting);
    }
}
