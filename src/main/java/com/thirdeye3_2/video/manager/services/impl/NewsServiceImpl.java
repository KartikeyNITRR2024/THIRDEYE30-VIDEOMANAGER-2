package com.thirdeye3_2.video.manager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.thirdeye3_2.video.manager.dtos.AudioGenerateDto;
import com.thirdeye3_2.video.manager.dtos.NewsDto;
import com.thirdeye3_2.video.manager.entities.News;
import com.thirdeye3_2.video.manager.enums.TableName;
import com.thirdeye3_2.video.manager.repositories.NewsRepository;
import com.thirdeye3_2.video.manager.services.AudioGenerateService;
import com.thirdeye3_2.video.manager.services.NewsService;
import com.thirdeye3_2.video.manager.services.PropertyService;
import com.thirdeye3_2.video.manager.utils.Mapper;

@Service
@Slf4j
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository repository;
    
    @Autowired
    private AudioGenerateService audioGenerateService;
    
    @Autowired
    private PropertyService propertyService;

    @Override
    public NewsDto create(NewsDto dto) {
        log.info("Creating news with header: {}", dto.getHeader());

        News entity = Mapper.toEntity(dto);
        entity.setCreatedTime(LocalDateTime.now());
        entity.setAutoDelete(false);

        News saved = repository.save(entity);

        log.info("News created successfully with id: {}", saved.getId());
        
        return Mapper.toDto(saved, audioGenerateService.create(new AudioGenerateDto(null, TableName.NEWS, saved.getId(), null, null, Boolean.FALSE, null, Boolean.TRUE, dto.getAudioContent())).getContent());
    }

    @Override
    public NewsDto getById(UUID id) {
        log.info("Fetching news by id: {}", id);

        News entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("News not found with id: {}", id);
                    return new RuntimeException("News not found");
                });

        return Mapper.toDto(entity, audioGenerateService.getByTableAndForeignKey(TableName.NEWS, id).getContent());
    }

    @Override
    public List<NewsDto> getAll() {
        log.info("Fetching all news records");

        return repository.findAll()
                .stream()
                .map(entity -> Mapper.toDto(entity, audioGenerateService.getByTableAndForeignKey(TableName.NEWS, entity.getId()).getContent()))
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto update(UUID id, NewsDto dto) {
        log.info("Updating news with id: {}", id);

        News entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("News not found for update: {}", id);
                    return new RuntimeException("News not found");
                });

        entity.setHeader(dto.getHeader());
        entity.setContent(dto.getContent());
        entity.setNewsWarningColor(dto.getNewsWarningColor());
        entity.setIsImageMultiMediaKeyUploaded(dto.getIsImageMultiMediaKeyUploaded());
        entity.setImageMultiMediaKey(dto.getImageMultiMediaKey());
        entity.setIsAudioMultiMediaKeyUploaded(dto.getIsAudioMultiMediaKeyUploaded());
        entity.setAudioMultiMediaKey(dto.getAudioMultiMediaKey());
        entity.setAutoDelete(dto.getAutoDelete());

        News updated = repository.save(entity);

        log.info("News updated successfully: {}", id);

        return Mapper.toDto(updated, audioGenerateService.getByTableAndForeignKey(TableName.NEWS, entity.getId()).getContent());
    }
    
    @Override
    public NewsDto addImageMultiMediaKey(UUID uuid, UUID key)
    {
    	log.info("Updating news with id: {}", uuid);

        News entity = repository.findById(uuid)
                .orElseThrow(() -> {
                    log.error("News not found for update: {}", uuid);
                    return new RuntimeException("News not found");
                });
        
        entity.setImageMultiMediaKey(key);
        entity.setIsImageMultiMediaKeyUploaded(true);
        
        News updated = repository.save(entity);

        log.info("Image key added successfully: {}", uuid);

        return Mapper.toDto(updated, null);
        
    }
    
    @Override
    public NewsDto addAudioMultiMediaKey(UUID uuid, UUID key)
    {
    	log.info("Updating news with id: {}", uuid);

        News entity = repository.findById(uuid)
                .orElseThrow(() -> {
                    log.error("News not found for update: {}", uuid);
                    return new RuntimeException("News not found");
                });
        
        entity.setAudioMultiMediaKey(key);
        entity.setIsAudioMultiMediaKeyUploaded(true);
        
        News updated = repository.save(entity);

        log.info("Audio key added successfully: {}", uuid);
        
        return Mapper.toDto(updated, null);
        
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting news with id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public List<NewsDto> getByVideoDetailsId(UUID videoDetailsId) {
        log.info("Fetching news by videoDetailsId: {}", videoDetailsId);

        return repository.findByVideoDetailsId(videoDetailsId)
                .stream()
                .map(entity -> Mapper.toDto(entity, audioGenerateService.getByTableAndForeignKey(TableName.NEWS, entity.getId()).getContent()))
                .collect(Collectors.toList());
    }
    
    @Override
    public void autoDeleteUnusedNews() {

        LocalDateTime threshold = LocalDateTime.now().minusDays(propertyService.getmaximumTimeForResourcesInDays());

        log.info("Deleting news older than {} days AND autoDelete=true. Threshold: {}",
        		propertyService.getmaximumTimeForResourcesInDays(), threshold);

        int deletedCount = repository
                .deleteOldAutoDeletableNews(threshold);

        log.info("Deleted {} old auto-deletable news records",
                deletedCount);
    }
}
