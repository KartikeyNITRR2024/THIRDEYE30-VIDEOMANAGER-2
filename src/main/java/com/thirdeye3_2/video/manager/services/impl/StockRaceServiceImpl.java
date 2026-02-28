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

import com.thirdeye3_2.video.manager.dtos.StockRaceDto;
import com.thirdeye3_2.video.manager.entities.StockRace;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.StockRaceRepository;
import com.thirdeye3_2.video.manager.services.StockRaceService;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
public class StockRaceServiceImpl implements StockRaceService {

    private static final Logger log = LoggerFactory.getLogger(StockRaceServiceImpl.class);

    @Autowired
    private StockRaceRepository repository;

    @Override
    public StockRaceDto create(StockRaceDto dto) {
        log.info("Creating StockRace name={}", dto.getName());        
        StockRace entity = Mapper.toEntity(dto);
        entity.setActive(false);
        entity.setLastlyUsed(LocalDateTime.now());

        return Mapper.toDto(repository.save(entity));
    }

    @Override
    public StockRaceDto update(UUID id, StockRaceDto dto) {
        log.info("Updating StockRace id={}", id);

        StockRace existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockRace not found"));

        dto.setId(existing.getId());
        return Mapper.toDto(repository.save(Mapper.toEntity(dto)));
    }

    @Override
    public StockRaceDto getById(UUID id) {
        log.info("Fetching StockRace id={}", id);

        return Mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockRace not found")));
    }

    @Override
    public List<StockRaceDto> getAll() {
        log.info("Fetching all StockRaces");

        return repository.findAll()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting StockRace id={}", id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public StockRaceDto makeActive(UUID id) {
        log.info("Making StockRace active id={}", id);

        repository.findByActiveTrue().ifPresent(race -> {
            race.setActive(false);
            repository.save(race);
        });

        StockRace race = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockRace not found"));

        race.setActive(true);
        race.setLastlyUsed(LocalDateTime.now());
        return Mapper.toDto(repository.save(race));
    }

    @Override
    public StockRaceDto getActive() {
        log.info("Fetching active StockRace");

        return Mapper.toDto(repository.findByActiveTrue()
                .orElseThrow(() -> new ResourceNotFoundException("No active StockRace found")));
    }
}
