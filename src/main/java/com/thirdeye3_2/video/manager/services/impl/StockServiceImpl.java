package com.thirdeye3_2.video.manager.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.dtos.StockDto;
import com.thirdeye3_2.video.manager.entities.Stock;
import com.thirdeye3_2.video.manager.entities.StockGroup;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.StockGroupRepository;
import com.thirdeye3_2.video.manager.repositories.StockRepository;
import com.thirdeye3_2.video.manager.services.StockService;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
public class StockServiceImpl implements StockService {

    private static final Logger log =
            LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private StockRepository repository;

    @Autowired
    private StockGroupRepository groupRepository;


    @Override
    public StockDto create(StockDto dto) {

        log.info("Creating stock | name={} | groupId={}",
                dto.getName(), dto.getGroupId());

        StockGroup group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> {
                    log.error("StockGroup not found | id={}", dto.getGroupId());
                    return new ResourceNotFoundException("StockGroup not found");
                });

        Stock stock = Mapper.toEntity(dto, group);
        stock.setActive(true);

        Stock saved = repository.save(stock);

        log.info("Stock created successfully | id={}", saved.getId());

        return Mapper.toDto(saved);
    }

    @Override
    public StockDto getById(UUID id) {

        log.info("Fetching stock | id={}", id);

        Stock stock = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Stock not found | id={}", id);
                    return new ResourceNotFoundException("Stock not found");
                });

        return Mapper.toDto(stock);
    }

    @Override
    public List<StockDto> getAll() {

        log.info("Fetching all stocks");

        return repository.findAll()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StockDto update(UUID id, StockDto dto) {

        log.info("Updating stock | id={}", id);

        Stock stock = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Stock not found for update | id={}", id);
                    return new RuntimeException("Stock not found");
                });

        StockGroup group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> {
                    log.error("StockGroup not found | id={}", dto.getGroupId());
                    return new ResourceNotFoundException("StockGroup not found");
                });

        stock.setName(dto.getName());
        stock.setMarketCode(dto.getMarketCode());
        stock.setStockName(dto.getStockName());
        stock.setGroup(group);

        Stock updated = repository.save(stock);

        log.info("Stock updated successfully | id={}", id);

        return Mapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {

        log.info("Deleting stock | id={}", id);

        if (!repository.existsById(id)) {
            log.error("Stock not found for delete | id={}", id);
            throw new ResourceNotFoundException("Stock not found");
        }

        repository.deleteById(id);

        log.info("Stock deleted successfully | id={}", id);
    }

    @Override
    public StockDto updateActive(UUID id, Boolean status) {

        log.info("Updating stock active status | id={} | status={}",
                id, status);

        Stock stock = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Stock not found | id={}", id);
                    return new ResourceNotFoundException("Stock not found");
                });

        stock.setActive(status);

        Stock updated = repository.save(stock);

        log.info("Stock status updated successfully | id={}", id);

        return Mapper.toDto(updated);
    }
}
