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

import com.thirdeye3_2.video.manager.dtos.ContentVideoDto;
import com.thirdeye3_2.video.manager.entities.ContentVideo;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.ContentVideoRepository;
import com.thirdeye3_2.video.manager.services.ContentVideoService;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
public class ContentVideoServiceImpl implements ContentVideoService {

    private static final Logger log = LoggerFactory.getLogger(ContentVideoServiceImpl.class);

    @Autowired
    private ContentVideoRepository repository;

    @Override
    public ContentVideoDto create(ContentVideoDto dto) {
        log.info("Creating ContentVideo name={}", dto.getName());

        ContentVideo entity = Mapper.toEntity(dto);
        entity.setActive(false);
        entity.setLastlyUsed(LocalDateTime.now());

        return Mapper.toDto(repository.save(entity));
    }

    @Override
    public ContentVideoDto update(UUID id, ContentVideoDto dto) {
        log.info("Updating ContentVideo id={}", id);

        ContentVideo existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContentVideo not found"));

        dto.setId(existing.getId());
        return Mapper.toDto(repository.save(Mapper.toEntity(dto)));
    }

    @Override
    public ContentVideoDto getById(UUID id) {
        log.info("Fetching ContentVideo id={}", id);

        return Mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContentVideo not found")));
    }

    @Override
    public List<ContentVideoDto> getAll() {
        log.info("Fetching all ContentVideos");

        return repository.findAll()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting ContentVideo id={}", id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public ContentVideoDto makeActive(UUID id) {
        log.info("Making ContentVideo active id={}", id);

        repository.findByActiveTrue().ifPresent(video -> {
            video.setActive(false);
            repository.save(video);
        });

        ContentVideo video = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContentVideo not found"));

        video.setActive(true);
        video.setLastlyUsed(LocalDateTime.now());
        return  Mapper.toDto(repository.save(video));
    }

    @Override
    public ContentVideoDto getActive() {
        log.info("Fetching active ContentVideo");

        return Mapper.toDto(repository.findByActiveTrue()
                .orElseThrow(() -> new ResourceNotFoundException("No active ContentVideo found")));
    }
}
