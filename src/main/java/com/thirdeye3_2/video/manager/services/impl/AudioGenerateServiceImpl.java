package com.thirdeye3_2.video.manager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.thirdeye3_2.video.manager.dtos.AudioGenerateDto;
import com.thirdeye3_2.video.manager.entities.AudioGenerate;
import com.thirdeye3_2.video.manager.enums.TableName;
import com.thirdeye3_2.video.manager.repositories.AudioGenerateRepository;
import com.thirdeye3_2.video.manager.services.AudioGenerateService;
import com.thirdeye3_2.video.manager.services.PropertyService;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
@Slf4j
public class AudioGenerateServiceImpl implements AudioGenerateService {

    @Autowired
    private AudioGenerateRepository repository;
    
    @Autowired
    private PropertyService propertyService;

    @Override
    public AudioGenerateDto create(AudioGenerateDto dto) {
        log.info("Creating AudioGenerate entry for table: {}", dto.getTableName());

        AudioGenerate entity = Mapper.toEntity(dto);
        entity.setCreatedTime(LocalDateTime.now());
        entity.setIsAudioGenerated(false);
        entity.setAutoDelete(false);
        
        if(entity.getTableName() == null)
        {
        	entity.setTableName(TableName.AUDIOGENERATE);
        }
        

        AudioGenerate saved = repository.save(entity);

        log.info("AudioGenerate created successfully with id: {}", saved.getId());

        return Mapper.toDto(saved);
    }

    @Override
    public AudioGenerateDto getById(UUID id) {
        log.info("Fetching AudioGenerate by id: {}", id);

        AudioGenerate entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("AudioGenerate not found for id: {}", id);
                    return new ResourceNotFoundException("AudioGenerate not found");
                });

        return Mapper.toDto(entity);
    }

    @Override
    public List<AudioGenerateDto> getAll() {
        log.info("Fetching all AudioGenerate records");

        return repository.findAll()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AudioGenerateDto update(UUID id, AudioGenerateDto dto) {
        log.info("Updating AudioGenerate with id: {}", id);

        AudioGenerate entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("AudioGenerate not found for update: {}", id);
                    return new RuntimeException("AudioGenerate not found");
                });

        entity.setTableName(dto.getTableName());
        entity.setForeignKey(dto.getForeignKey());
        entity.setAutoDelete(dto.getAutoDelete());
        entity.setAudioGeneratedTime(dto.getAudioGeneratedTime());
        entity.setIsAudioGenerated(dto.getIsAudioGenerated());
        AudioGenerate updated = repository.save(entity);

        log.info("AudioGenerate updated successfully for id: {}", id);

        return Mapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting AudioGenerate with id: {}", id);
        repository.deleteById(id);
        log.info("Deleted successfully: {}", id);
    }

    @Override
    public List<AudioGenerateDto> getAllNotGenerated() {
        log.info("Fetching all records where isAudioGenerated = false");

        return repository.findByIsAudioGeneratedFalse()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AudioGenerateDto updateIsAudioGenerated(UUID id, UUID key) {
        log.info("Updating isAudioGenerated for id: {} to true", id);

        AudioGenerate entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("AudioGenerate not found for status update: {}", id);
                    return new ResourceNotFoundException("AudioGenerate not found");
                });

        entity.setIsAudioGenerated(Boolean.TRUE);
        entity.setAudioGeneratedTime(LocalDateTime.now());
        entity.setAudioMultiMediaKey(key);
        AudioGenerate updated = repository.save(entity);

        log.info("Status updated successfully for id: {}", id);

        return Mapper.toDto(updated);
    }
    
    @Override
    public AudioGenerateDto getByTableAndForeignKey(TableName tableName, UUID foreignKey) {

        log.info("Fetching AudioGenerate for table: {} and foreignKey: {}",
                tableName, foreignKey);

        AudioGenerate entity = repository
                .findByTableNameAndForeignKey(tableName, foreignKey)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("AudioGenerate not found"));

        return Mapper.toDto(entity);
    }
    
    @Override
    public void autoDeleteUnusedAudio() {

        LocalDateTime threshold = LocalDateTime.now().minusDays(propertyService.getmaximumTimeForResourcesInDays());

        log.info("Deleting AudioGenerate records older than {} days AND autoDelete=true. Threshold: {}",
        		propertyService.getmaximumTimeForResourcesInDays(), threshold);

        int deleted = repository.deleteOldAutoDeletable(threshold);

        log.info("Deleted {} AudioGenerate records", deleted);
    }
}
