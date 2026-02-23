package com.thirdeye3_2.video.manager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.dtos.StockGroupDto;
import com.thirdeye3_2.video.manager.entities.StockGroup;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.StockGroupRepository;
import com.thirdeye3_2.video.manager.services.StockGroupService;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
public class StockGroupServiceImpl implements StockGroupService {

    private static final Logger log =
            LoggerFactory.getLogger(StockGroupServiceImpl.class);

    @Autowired
    private StockGroupRepository repository;

    @Override
    public StockGroupDto create(StockGroupDto dto) {

        log.info("Creating StockGroup | name={}", dto.getName());

        StockGroup entity = Mapper.toEntity(dto);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setLastUsed(LocalDateTime.now());
        entity.setActive(true);

        return Mapper.toDto(repository.save(entity));
    }

    @Override
    public StockGroupDto getById(UUID id) {

        StockGroup entity = repository.findByIdWithStocks(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found"));

        return Mapper.toDto(entity);
    }

    @Override
    public List<StockGroupDto> getAll() {
        return repository.findAll()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StockGroupDto update(UUID id, StockGroupDto dto) {

        StockGroup entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found"));

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return Mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public StockGroupDto updateActive(UUID id, Boolean status) {

        StockGroup entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found"));

        entity.setActive(status);

        return Mapper.toDto(repository.save(entity));
    }
}