package com.thirdeye3_2.video.manager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.dtos.PromptDetailDto;
import com.thirdeye3_2.video.manager.dtos.PromptDto;
import com.thirdeye3_2.video.manager.entities.Prompt;
import com.thirdeye3_2.video.manager.enums.PromptType;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.PromptRepository;
import com.thirdeye3_2.video.manager.services.PromptService;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
public class PromptServiceImpl implements PromptService {

    private static final Logger logger = LoggerFactory.getLogger(PromptServiceImpl.class);

    @Autowired
    private PromptRepository repository;

    @Override
    public PromptDetailDto create(PromptDetailDto dto) {
        logger.info("Creating Prompt: {}", dto.getName());

        Prompt entity = Mapper.toEntity(dto);
        entity.setCreatedDateTime(LocalDateTime.now());
        entity.setLastlyUsed(LocalDateTime.now());

        return Mapper.toDetailDto(repository.save(entity));
    }

    @Override
    public PromptDetailDto update(UUID id, PromptDetailDto dto) {

        logger.info("Updating Prompt with ID: {}", id);

        Prompt prompt = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prompt not found"));

        prompt.setName(dto.getName());
        prompt.setPrompt(dto.getPrompt());
        prompt.setTypeOfVideo(dto.getTypeOfVideo());

        return Mapper.toDetailDto(repository.save(prompt));
    }

    @Override
    public void delete(UUID id) {
        logger.info("Deleting Prompt with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Prompt not found");
        }

        repository.deleteById(id);
    }

    @Override
    public List<PromptDto> getAll() {
        logger.info("Fetching all prompts ordered by lastlyUsed DESC");

        return repository.findAllByOrderByLastlyUsedDesc()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PromptDto> getByType(PromptType type) {
        logger.info("Fetching prompts by type: {}", type);

        return repository.findByTypeOfVideoOrderByLastlyUsedDesc(type)
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PromptDetailDto getPromptByIdAndUpdateLastUsed(UUID id) {

        logger.info("Fetching full prompt and updating lastlyUsed for ID: {}", id);

        Prompt prompt = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prompt not found"));

        prompt.setLastlyUsed(LocalDateTime.now());
        repository.save(prompt);

        return Mapper.toDetailDto(prompt);
    }
}
