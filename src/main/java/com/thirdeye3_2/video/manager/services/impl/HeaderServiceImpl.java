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

import com.thirdeye3_2.video.manager.dtos.HeaderDto;
import com.thirdeye3_2.video.manager.entities.Header;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.HeaderRepository;
import com.thirdeye3_2.video.manager.services.HeaderService;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
public class HeaderServiceImpl implements HeaderService {

    private static final Logger log = LoggerFactory.getLogger(HeaderServiceImpl.class);

    @Autowired
    private HeaderRepository repository;

    @Override
    public HeaderDto create(HeaderDto dto) {
        log.info("Creating Header name={}", dto.getName());

        Header entity = Mapper.toEntity(dto);
        entity.setActive(false);
        entity.setLastlyUsed(LocalDateTime.now());

        return Mapper.toDto(repository.save(entity));
    }

    @Override
    public HeaderDto update(UUID id, HeaderDto dto) {
        log.info("Updating Header id={}", id);

        Header existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Header not found"));

        dto.setId(existing.getId());
        return Mapper.toDto(repository.save(Mapper.toEntity(dto)));
    }

    @Override
    public HeaderDto getById(UUID id) {
        log.info("Fetching Header id={}", id);

        return Mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Header not found")));
    }

    @Override
    public List<HeaderDto> getAll() {
        log.info("Fetching all Headers");

        return repository.findAll()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting Header id={}", id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public HeaderDto makeActive(UUID id) {
        log.info("Making Header active id={}", id);

        repository.findByActiveTrue().ifPresent(header -> {
            header.setActive(false);
            repository.save(header);
        });

        Header header = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Header not found"));

        header.setActive(true);
        header.setLastlyUsed(LocalDateTime.now());
        return Mapper.toDto(repository.save(header));
    }

    @Override
    public HeaderDto getActive() {
        log.info("Fetching active Header");

        return Mapper.toDto(repository.findByActiveTrue()
                .orElseThrow(() -> new ResourceNotFoundException("No active Header found")));
    }
}
