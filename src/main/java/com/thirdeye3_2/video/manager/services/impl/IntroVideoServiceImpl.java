package com.thirdeye3_2.video.manager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.dtos.IntroVideoDto;
import com.thirdeye3_2.video.manager.entities.IntroVideo;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.IntroVideoRepository;
import com.thirdeye3_2.video.manager.services.IntroVideoService;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
public class IntroVideoServiceImpl implements IntroVideoService {

    private static final Logger log = LoggerFactory.getLogger(IntroVideoServiceImpl.class);

    @Autowired
    private IntroVideoRepository repository;

    @Override
    public IntroVideoDto create(IntroVideoDto dto) {
        log.info("Creating IntroVideo with name={}", dto.getName());

        IntroVideo entity = Mapper.toEntity(dto);
        entity.setLastlyUsed(LocalDateTime.now());

        return Mapper.toDto(repository.save(entity));
    }

    @Override
    public IntroVideoDto update(UUID id, IntroVideoDto dto) {
        log.info("Updating IntroVideo id={}", id);

        IntroVideo existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IntroVideo not found"));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setActive(dto.getActive());
        existing.setIsBackgroundImage(dto.getIsBackgroundImage());
        existing.setBackgroundImage(dto.getBackgroundImage());
        existing.setBackgroundColor(dto.getBackgroundColor());
        existing.setBackgroundOpacity(dto.getBackgroundOpacity());
        existing.setIsHeaderPresent(dto.getIsHeaderPresent());
        existing.setHeaderFontType(dto.getHeaderFontType());
        existing.setHeaderFontName(dto.getHeaderFontName());
        existing.setHeaderSize(dto.getHeaderSize());
        existing.setHeaderColor(dto.getHeaderColor());
        existing.setIsSubHeaderPresent(dto.getIsSubHeaderPresent());
        existing.setSubHeaderFontType(dto.getSubHeaderFontType());
        existing.setSubHeaderFontName(dto.getSubHeaderFontName());
        existing.setSubHeaderSize(dto.getSubHeaderSize());
        existing.setSubHeaderColor(dto.getSubHeaderColor());
        existing.setIsLinePresent(dto.getIsLinePresent());
        existing.setLineColor(dto.getLineColor());
        existing.setLineWidth(dto.getLineWidth());
        existing.setAdImageHeight(dto.getAdImageHeight());
        existing.setAdImageWidth(dto.getAdImageWidth());
        existing.setIsAudio(dto.getIsAudio());
        existing.setAudioMultiMediaKey(dto.getAudioMultiMediaKey());
        existing.setAudioVolumne(dto.getAudioVolumne());
        existing.setIscompanyNamePresent(dto.getIscompanyNamePresent());
        existing.setCompanyNamefontSize(dto.getCompanyNamefontSize());
        existing.setCompanyNamefontColor(dto.getCompanyNamefontColor());
        existing.setCompanyNamefontName(dto.getCompanyNamefontName());
        return Mapper.toDto(repository.save(existing));
    }

    @Override
    public IntroVideoDto getById(UUID id) {
        log.info("Fetching IntroVideo id={}", id);

        return Mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IntroVideo not found")));
    }

    @Override
    public List<IntroVideoDto> getAll() {
        log.info("Fetching all IntroVideos");

        return repository.findAll()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting IntroVideo id={}", id);
        repository.deleteById(id);
    }

    @Override
    public IntroVideoDto makeActive(UUID id) {
        log.info("Making IntroVideo active id={}", id);

        // Deactivate existing active
        repository.findByActiveTrue().ifPresent(video -> {
            video.setActive(false);
            repository.save(video);
        });

        // Activate new one
        IntroVideo video = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("IntroVideo not found"));

        video.setActive(true);
        video.setLastlyUsed(LocalDateTime.now());

        return Mapper.toDto(repository.save(video));
    }

    @Override
    public IntroVideoDto getActive() {
        log.info("Fetching active IntroVideo");

        return Mapper.toDto(repository.findByActiveTrue()
                .orElseThrow(() -> new ResourceNotFoundException("No active IntroVideo found")));
    }
}
