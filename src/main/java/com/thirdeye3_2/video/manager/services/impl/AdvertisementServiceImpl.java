package com.thirdeye3_2.video.manager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.dtos.AdvertisementDto;
import com.thirdeye3_2.video.manager.entities.Advertisement;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.AdvertisementRepository;
import com.thirdeye3_2.video.manager.services.AdvertisementService;

import lombok.extern.slf4j.Slf4j;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
@Slf4j
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Override
    public AdvertisementDto createAdvertisement(AdvertisementDto dto) {

        log.info("Creating advertisement");

        Advertisement advertisement = Mapper.toEntity(dto);
        advertisement.setCreatedTime(LocalDateTime.now());

        Advertisement saved = advertisementRepository.save(advertisement);

        log.info("Advertisement created with id {}", saved.getId());

        return Mapper.toDto(saved);
    }

    @Override
    public AdvertisementDto updateAdvertisement(UUID id, AdvertisementDto dto) {

        log.info("Updating advertisement {}", id);

        Advertisement entity = advertisementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advertisement not found"));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        entity.setIsIntroAdvertismentPresent(dto.getIsIntroAdvertismentPresent());
        entity.setIntroAdvertismentMultimediaKey(dto.getIntroAdvertismentMultimediaKey());
        entity.setIntroAdvertismentSize(dto.getIntroAdvertismentSize());

        entity.setIsBadgeAdvertismentPresent(dto.getIsBadgeAdvertismentPresent());
        entity.setBadgeAdvertismentMultimediaKey(dto.getBadgeAdvertismentMultimediaKey());
        entity.setBadgeAdvertismentSize(dto.getBadgeAdvertismentSize());

        entity.setBadgeAdvertismentBackgroundColor(dto.getBadgeAdvertismentBackgroundColor());
        entity.setBadgeAdvertismentBackgroundWidthPercent(dto.getBadgeAdvertismentBackgroundWidthPercent());

        entity.setIsContentAdvertismentPresent(dto.getIsContentAdvertismentPresent());

        Advertisement saved = advertisementRepository.save(entity);

        log.info("Advertisement updated {}", id);

        return Mapper.toDto(saved);
    }

    @Override
    public AdvertisementDto getAdvertisement(UUID id) {

        log.info("Fetching advertisement {}", id);

        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advertisement not found"));

        return Mapper.toDto(advertisement);
    }

    @Override
    public List<AdvertisementDto> getAllAdvertisements() {

        log.info("Fetching all advertisements");

        return advertisementRepository.findAllByOrderByCreatedTimeDesc()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdvertisementDto> getActiveAdvertisements() {

        log.info("Fetching active advertisements");

        return advertisementRepository.findByActiveTrueOrderByCreatedTimeDesc()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdvertisementDto activateAdvertisement(UUID id) {

        log.info("Activating advertisement {}", id);

        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advertisement not found"));

        advertisement.setActive(true);

        return Mapper.toDto(advertisementRepository.save(advertisement));
    }

    @Override
    public AdvertisementDto deactivateAdvertisement(UUID id) {

        log.info("Deactivating advertisement {}", id);

        Advertisement advertisement = advertisementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advertisement not found"));

        advertisement.setActive(false);

        return Mapper.toDto(advertisementRepository.save(advertisement));
    }

    @Override
    public void deleteAdvertisement(UUID id) {

        log.info("Deleting advertisement {}", id);

        advertisementRepository.deleteById(id);
    }
}