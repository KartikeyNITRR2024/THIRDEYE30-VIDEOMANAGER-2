package com.thirdeye3_2.video.manager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import com.thirdeye3_2.video.manager.dtos.AudioGenerateDto;
import com.thirdeye3_2.video.manager.dtos.NewsCsvDto;
import com.thirdeye3_2.video.manager.dtos.NewsDto;
import com.thirdeye3_2.video.manager.entities.News;
import com.thirdeye3_2.video.manager.enums.TableName;
import com.thirdeye3_2.video.manager.repositories.NewsRepository;
import com.thirdeye3_2.video.manager.services.AudioGenerateService;
import com.thirdeye3_2.video.manager.services.NewsService;
import com.thirdeye3_2.video.manager.services.PropertyService;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.exceptions.CorruptedMultiMediaException;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

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
        entity.setAutoDelete(Boolean.TRUE);

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
                    return new ResourceNotFoundException("News not found");
                });
        
        AudioGenerateDto dto = audioGenerateService.getByTableAndForeignKeyForInternalUse(TableName.NEWS, id);

        return Mapper.toDto(entity, dto==null ? null : dto.getContent());
    }

    @Override
    public List<NewsDto> getAll() {
        log.info("Fetching all news records");

        return repository.findAllByOrderByCreatedTimeDesc()
        		.stream()
                .map(entity -> {
                    AudioGenerateDto audioDto = audioGenerateService.getByTableAndForeignKeyForInternalUse(TableName.NEWS, entity.getId());
                    return Mapper.toDto(entity, audioDto == null ? null : audioDto.getContent());
                })
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto update(UUID id, NewsDto dto) {
        log.info("Updating news with id: {}", id);

        News entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("News not found for update: {}", id);
                    return new ResourceNotFoundException("News not found");
                });

        entity.setHeader(dto.getHeader());
        entity.setContent(dto.getContent());
        entity.setCompanyName(dto.getCompanyName());
        entity.setNewsWarningColor(dto.getNewsWarningColor());
        entity.setIsImageMultiMediaKeyUploaded(dto.getIsImageMultiMediaKeyUploaded());
        entity.setImageMultiMediaKey(dto.getImageMultiMediaKey());
        entity.setIsAudioMultiMediaKeyUploaded(dto.getIsAudioMultiMediaKeyUploaded());
        entity.setAudioMultiMediaKey(dto.getAudioMultiMediaKey());
        entity.setAutoDelete(dto.getAutoDelete());

        News updated = repository.save(entity);

        log.info("News updated successfully: {}", id);
        
        AudioGenerateDto dto1 = audioGenerateService.getByTableAndForeignKey(TableName.NEWS, id);

        return Mapper.toDto(updated, dto1==null ? null : dto1.getContent());
    }
    
    @Override
    public NewsDto addImageMultiMediaKey(UUID uuid, UUID key)
    {
    	log.info("Updating news with id: {}", uuid);

        News entity = repository.findById(uuid)
                .orElseThrow(() -> {
                    log.error("News not found for update: {}", uuid);
                    return new ResourceNotFoundException("News not found");
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
                    return new ResourceNotFoundException("News not found");
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
                .map(entity -> {
                    AudioGenerateDto audioDto = audioGenerateService.getByTableAndForeignKeyForInternalUse(TableName.NEWS, entity.getId());
                    return Mapper.toDto(entity, audioDto == null ? null : audioDto.getContent());
                })
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
    
    @Override
    @org.springframework.transaction.annotation.Transactional // Ensures all rows save or none do
    public void uploadCsv(NewsCsvDto csvDto) {
        log.info("Starting CSV upload for videoDetailsId: {}", csvDto.getVideoDetailsId());
        
        if (csvDto.getFile() == null || csvDto.getFile().isEmpty()) {
            throw new CorruptedMultiMediaException("File must not be empty");
        }
        
        if (csvDto.getVideoDetailsId() == null) {
            throw new CorruptedMultiMediaException("videoDetailsId must be provided");
        }

        MultipartFile file = csvDto.getFile();
        try (java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.InputStreamReader(file.getInputStream(), java.nio.charset.StandardCharsets.UTF_8))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (data.length < 5) {
                    log.warn("Skipping malformed row (insufficient columns): {}", line);
                    continue;
                }
                NewsDto dto = NewsDto.builder()
                        .videoDetailsId(csvDto.getVideoDetailsId())
                        .header(clean(data[0]))
                        .content(clean(data[1]))
                        .audioContent(clean(data[2]))
                        .newsWarningColor(clean(data[3]))
                        .autoDelete(Boolean.TRUE)
                        .isAudioMultiMediaKeyUploaded(Boolean.FALSE)
                        .isImageMultiMediaKeyUploaded(Boolean.FALSE)
                        .build();
                this.create(dto);
            }
        } catch (Exception ex) {
            log.error("Failed to process CSV: {}", ex.getMessage());
            throw new CorruptedMultiMediaException("Invalid CSV file format or content: " + ex.getMessage());
        }
    }

    private String clean(String input) {
        return (input == null) ? "" : input.replace("\"", "").trim();
    }
}
