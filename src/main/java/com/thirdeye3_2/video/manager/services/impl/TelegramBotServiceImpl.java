package com.thirdeye3_2.video.manager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.dtos.TelegramBotDto;
import com.thirdeye3_2.video.manager.entities.TelegramBot;
import com.thirdeye3_2.video.manager.enums.BotType;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.TelegramBotRepository;
import com.thirdeye3_2.video.manager.services.TelegramBotService;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

@Service
public class TelegramBotServiceImpl implements TelegramBotService {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotServiceImpl.class);

    @Autowired
    private TelegramBotRepository repository;

    @Override
    public TelegramBotDto create(TelegramBotDto dto) {
        logger.info("Creating Telegram Bot: {}", dto);

        TelegramBot entity = Mapper.toEntity(dto);
        entity.setCreatedDateTime(LocalDateTime.now());
        entity.setActive(true);

        TelegramBot saved = repository.save(entity);

        logger.info("Telegram Bot created with ID: {}", saved.getId());

        return Mapper.toDto(saved);
    }

    @Override
    public TelegramBotDto update(UUID id, TelegramBotDto dto) {
        logger.info("Updating Bot with ID: {}", id);

        TelegramBot bot = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bot not found"));

        bot.setName(dto.getName());
        bot.setChatId(dto.getChatId());
        bot.setBotToken(dto.getBotToken());
        bot.setBotType(dto.getBotType());

        TelegramBot updated = repository.save(bot);

        logger.info("Bot updated successfully: {}", id);

        return Mapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {
        logger.info("Deleting Bot with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new RuntimeException("Bot not found");
        }

        repository.deleteById(id);

        logger.info("Bot deleted successfully: {}", id);
    }

    @Override
    public TelegramBotDto getById(UUID id) {
        logger.info("Fetching Bot with ID: {}", id);

        TelegramBot bot = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bot not found"));

        return Mapper.toDto(bot);
    }

    @Override
    public List<TelegramBotDto> getAll() {
        logger.info("Fetching all Telegram Bots");

        return repository.findAll()
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TelegramBotDto activate(UUID id) {
        logger.info("Activating Bot with ID: {}", id);

        TelegramBot bot = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bot not found"));

        bot.setActive(true);
        
        logger.info("Bot activated successfully: {}", id);
        return Mapper.toDto(repository.save(bot));

    
    }

    @Override
    public TelegramBotDto deactivate(UUID id) {
        logger.info("Deactivating Bot with ID: {}", id);

        TelegramBot bot = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bot not found"));

        bot.setActive(false);
        logger.info("Bot deactivated successfully: {}", id);
        return Mapper.toDto(repository.save(bot));
    }

    @Override
    public List<TelegramBotDto> getByTypeAndActive(BotType botType, Boolean active) {
        logger.info("Fetching Bots by Type: {} and Active: {}", botType, active);

        return repository.findByBotTypeAndActive(botType, active)
                .stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Map<BotType, List<TelegramBotDto>> getAllActiveGroupedByType() {

        logger.info("Fetching all ACTIVE bots grouped by BotType");

        List<TelegramBot> activeBots = repository.findByActive(true);

        return activeBots.stream()
                .map(Mapper::toDto)
                .collect(Collectors.groupingBy(TelegramBotDto::getBotType));
    }
}