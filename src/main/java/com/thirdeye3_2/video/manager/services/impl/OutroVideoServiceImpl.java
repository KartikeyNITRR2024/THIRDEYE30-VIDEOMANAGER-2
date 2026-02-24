package com.thirdeye3_2.video.manager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.dtos.OutroVideoDto;
import com.thirdeye3_2.video.manager.entities.OutroVideo;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.OutroVideoRepository;
import com.thirdeye3_2.video.manager.services.OutroVideoService;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
public class OutroVideoServiceImpl implements OutroVideoService {

    private static final Logger log = LoggerFactory.getLogger(OutroVideoServiceImpl.class);

    @Autowired
    private OutroVideoRepository repository;

    @Override
    public OutroVideoDto create(OutroVideoDto dto) {
        log.info("Creating OutroVideo name={}", dto.getName());

        OutroVideo entity = Mapper.toEntity(dto);
        entity.setLastlyUsed(LocalDateTime.now());

        return Mapper.toDto(repository.save(entity));
    }

    @Override
    public OutroVideoDto update(UUID id, OutroVideoDto dto) {
        log.info("Updating OutroVideo id={}", id);

        OutroVideo existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OutroVideo not found"));

        dto.setId(existing.getId());
        return Mapper.toDto(repository.save(Mapper.toEntity(dto)));
    }

    @Override
    public OutroVideoDto getById(UUID id) {
        log.info("Fetching OutroVideo id={}", id);

        return Mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OutroVideo not found")));
    }

    @Override
    public List<OutroVideoDto> getAll() {
        log.info("Fetching all OutroVideos");

        return repository.findAll()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting OutroVideo id={}", id);
        repository.deleteById(id);
    }

    @Override
    public OutroVideoDto makeActive(UUID id) {
        log.info("Making OutroVideo active id={}", id);

        repository.findByActiveTrue().ifPresent(video -> {
            video.setActive(false);
            repository.save(video);
        });

        OutroVideo video = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OutroVideo not found"));

        video.setActive(true);
        video.setLastlyUsed(LocalDateTime.now());

        return Mapper.toDto(repository.save(video));
    }

    @Override
    public OutroVideoDto getActive() {
        log.info("Fetching active OutroVideo");

        return Mapper.toDto(repository.findByActiveTrue()
                .orElseThrow(() -> new ResourceNotFoundException("No active OutroVideo found")));
    }
}
