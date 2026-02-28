package com.thirdeye3_2.video.manager.services.impl;

import com.thirdeye3_2.video.manager.dtos.ContentGeneratorDto;
import com.thirdeye3_2.video.manager.entities.ContentGenerator;
import com.thirdeye3_2.video.manager.enums.GeneratorType;
import com.thirdeye3_2.video.manager.repositories.ContentGeneratorRepository;
import com.thirdeye3_2.video.manager.services.ContentGeneratorService;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;
import com.thirdeye3_2.video.manager.utils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContentGeneratorServiceImpl implements ContentGeneratorService {

	@Autowired
    private ContentGeneratorRepository repository;

    @Override
    @Transactional
    public ContentGeneratorDto updateStatus(GeneratorType type, boolean isRunning) {
        log.info("Request to update status for generator: {} to isRunning: {}", type, isRunning);
        
        ContentGenerator generator = repository.findByType(type)
                .orElseThrow(() -> {
                    log.error("Update failed: Generator not found with type: {}", type);
                    return new ResourceNotFoundException("Generator not found with type: " + type);
                });

        generator.setIsRunning(isRunning);
        
        if (Boolean.TRUE.equals(isRunning)) {
            generator.setLastlyStarted(LocalDateTime.now());
            log.debug("Generator {} started at {}", type, generator.getLastlyStarted());
        } else {
            generator.setLastlyCompleted(LocalDateTime.now());
            log.debug("Generator {} stopped at {}", type, generator.getLastlyCompleted());
        }
        
        ContentGeneratorDto savedDto = Mapper.toDto(repository.save(generator));
        log.info("Successfully updated status for generator: {}", type);
        return savedDto;
    }

    @Override
    @Transactional
    public ContentGeneratorDto toggleActive(GeneratorType type, boolean active) {
        log.info("Request to toggle active status for generator: {} to: {}", type, active);
        
        ContentGenerator generator = repository.findByType(type)
                .orElseThrow(() -> {
                    log.error("Toggle failed: Generator not found with type: {}", type);
                    return new ResourceNotFoundException("Generator not found with type: " + type);
                });

        generator.setActive(active);
        ContentGeneratorDto savedDto = Mapper.toDto(repository.save(generator));
        log.info("Successfully toggled active status to {} for generator: {}", active, type);
        return savedDto;
    }
    
    @Override
    @Transactional(readOnly = true)
    public ContentGeneratorDto getByGeneratorType(GeneratorType type) {
        log.debug("Fetching generator details for type: {}", type);
        return repository.findByType(type)
                .map(Mapper::toDto)
                .orElseThrow(() -> {
                    log.warn("Fetch failed: No generator record found for type: {}", type);
                    return new ResourceNotFoundException("Generator not found with type: " + type);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContentGeneratorDto> getAll() {
        log.info("Fetching all content generators from database");
        List<ContentGenerator> generators = repository.findAll();
        log.debug("Found {} generators", generators.size());
        
        return generators.stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }
}