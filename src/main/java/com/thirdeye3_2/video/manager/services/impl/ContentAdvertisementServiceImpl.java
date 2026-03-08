package com.thirdeye3_2.video.manager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.dtos.ContentAdvertisementDto;
import com.thirdeye3_2.video.manager.entities.Advertisement;
import com.thirdeye3_2.video.manager.entities.ContentAdvertisement;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.AdvertisementRepository;
import com.thirdeye3_2.video.manager.repositories.ContentAdvertisementRepository;
import com.thirdeye3_2.video.manager.services.ContentAdvertisementService;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ContentAdvertisementServiceImpl implements ContentAdvertisementService {

    @Autowired
    private ContentAdvertisementRepository contentAdvertisementRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Override
    public ContentAdvertisementDto createContentAdvertisement(ContentAdvertisementDto dto) {

        log.info("Creating content advertisement");

        Advertisement advertisement = advertisementRepository
                .findById(dto.getAdvertisementId())
                .orElseThrow(() -> new ResourceNotFoundException("Advertisement not found"));

        ContentAdvertisement entity = Mapper.toEntity(dto);

        entity.setAdvertisement(advertisement);
        entity.setCreatedTime(LocalDateTime.now());

        ContentAdvertisement saved = contentAdvertisementRepository.save(entity);

        log.info("Content advertisement created {}", saved.getId());

        return Mapper.toDto(saved);
    }

    @Override
    public ContentAdvertisementDto updateContentAdvertisement(UUID id, ContentAdvertisementDto dto) {

        log.info("Updating content advertisement {}", id);

        ContentAdvertisement entity = contentAdvertisementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContentAdvertisement not found"));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setActive(dto.getActive());

        entity.setStartingSecond(dto.getStartingSecond());
        entity.setEndingSecond(dto.getEndingSecond());
        entity.setContentMultimediaKey(dto.getContentMultimediaKey());
        entity.setHeight(dto.getHeight());
        entity.setContentAdvertisementPosition(dto.getContentAdvertisementPosition());

        ContentAdvertisement saved = contentAdvertisementRepository.save(entity);

        log.info("Content advertisement updated {}", id);

        return Mapper.toDto(saved);
    }

    @Override
    public ContentAdvertisementDto getContentAdvertisement(UUID id) {

        log.info("Fetching content advertisement {}", id);

        ContentAdvertisement entity = contentAdvertisementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContentAdvertisement not found"));

        return Mapper.toDto(entity);
    }

    @Override
    public List<ContentAdvertisementDto> getAllContentAdvertisements() {

        log.info("Fetching all content advertisements");

        return contentAdvertisementRepository.findAllByOrderByCreatedTimeDesc()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContentAdvertisementDto> getActiveContentAdvertisements() {

        log.info("Fetching active content advertisements");

        return contentAdvertisementRepository.findByActiveTrueOrderByCreatedTimeDesc()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContentAdvertisementDto activateContentAdvertisement(UUID id) {

        log.info("Activating content advertisement {}", id);

        ContentAdvertisement entity = contentAdvertisementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContentAdvertisement not found"));

        entity.setActive(true);

        return Mapper.toDto(contentAdvertisementRepository.save(entity));
    }

    @Override
    public ContentAdvertisementDto deactivateContentAdvertisement(UUID id) {

        log.info("Deactivating content advertisement {}", id);

        ContentAdvertisement entity = contentAdvertisementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ContentAdvertisement not found"));

        entity.setActive(false);

        return Mapper.toDto(contentAdvertisementRepository.save(entity));
    }

    @Override
    public void deleteContentAdvertisement(UUID id) {

        log.info("Deleting content advertisement {}", id);

        contentAdvertisementRepository.deleteById(id);
    }
}