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

import com.thirdeye3_2.video.manager.dtos.NewsTextSoundDto;
import com.thirdeye3_2.video.manager.entities.NewsTextSound;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.NewsTextSoundRepository;
import com.thirdeye3_2.video.manager.services.NewsTextSoundService;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;


@Service
public class NewsTextSoundServiceImpl implements NewsTextSoundService {

    private static final Logger log = LoggerFactory.getLogger(NewsTextSoundServiceImpl.class);

    @Autowired
    private NewsTextSoundRepository repository;

    @Override
    public NewsTextSoundDto create(NewsTextSoundDto dto) {
        log.info("Creating NewsTextSound name={}", dto.getName());

        NewsTextSound entity = Mapper.toEntity(dto);
        entity.setActive(false);
        entity.setLastlyUsed(LocalDateTime.now());

        return Mapper.toDto(repository.save(entity));
    }

    @Override
    public NewsTextSoundDto update(UUID id, NewsTextSoundDto dto) {
        log.info("Updating NewsTextSound id={}", id);

        NewsTextSound existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NewsTextSound not found"));

        dto.setId(existing.getId());
        return Mapper.toDto(repository.save(Mapper.toEntity(dto)));
    }

    @Override
    public NewsTextSoundDto getById(UUID id) {
        log.info("Fetching NewsTextSound id={}", id);

        return Mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NewsTextSound not found")));
    }

    @Override
    public List<NewsTextSoundDto> getAll() {
        log.info("Fetching all NewsTextSounds");

        return repository.findAll()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting NewsTextSound id={}", id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public NewsTextSoundDto makeActive(UUID id) {
        log.info("Making NewsTextSound active id={}", id);

        repository.findByActiveTrue().ifPresent(item -> {
            item.setActive(false);
            repository.save(item);
        });

        NewsTextSound item = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NewsTextSound not found"));

        item.setActive(true);
        item.setLastlyUsed(LocalDateTime.now());
        return Mapper.toDto(repository.save(item));
    }

    @Override
    public NewsTextSoundDto getActive() {
        log.info("Fetching active NewsTextSound");

        return Mapper.toDto(repository.findByActiveTrue()
                .orElseThrow(() -> new ResourceNotFoundException("No active NewsTextSound found")));
    }
}
