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

import com.thirdeye3_2.video.manager.dtos.NewsImageDto;
import com.thirdeye3_2.video.manager.entities.NewsImage;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.NewsImageRepository;
import com.thirdeye3_2.video.manager.services.NewsImageService;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
public class NewsImageServiceImpl implements NewsImageService {

    private static final Logger log = LoggerFactory.getLogger(NewsImageServiceImpl.class);

    @Autowired
    private NewsImageRepository repository;

    @Override
    public NewsImageDto create(NewsImageDto dto) {
        log.info("Creating NewsImage name={}", dto.getName());

        NewsImage entity = Mapper.toEntity(dto);
        entity.setActive(false);
        entity.setLastlyUsed(LocalDateTime.now());

        return Mapper.toDto(repository.save(entity));
    }

    @Override
    public NewsImageDto update(UUID id, NewsImageDto dto) {
        log.info("Updating NewsImage id={}", id);

        NewsImage existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NewsImage not found"));

        dto.setId(existing.getId());
        return Mapper.toDto(repository.save(Mapper.toEntity(dto)));
    }

    @Override
    public NewsImageDto getById(UUID id) {
        log.info("Fetching NewsImage id={}", id);

        return Mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NewsImage not found")));
    }

    @Override
    public List<NewsImageDto> getAll() {
        log.info("Fetching all NewsImages");

        return repository.findAll()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting NewsImage id={}", id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public NewsImageDto makeActive(UUID id) {
        log.info("Making NewsImage active id={}", id);

        repository.findByActiveTrue().ifPresent(image -> {
            image.setActive(false);
            repository.save(image);
        });

        NewsImage image = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NewsImage not found"));

        image.setActive(true);
        image.setLastlyUsed(LocalDateTime.now());
        return Mapper.toDto(repository.save(image));
    }

    @Override
    public NewsImageDto getActive() {
        log.info("Fetching active NewsImage");

        return Mapper.toDto(repository.findByActiveTrue()
                .orElseThrow(() -> new ResourceNotFoundException("No active NewsImage found")));
    }
}
