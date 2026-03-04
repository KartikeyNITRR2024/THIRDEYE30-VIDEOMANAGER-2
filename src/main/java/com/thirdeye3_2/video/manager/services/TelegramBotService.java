package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.TelegramBotDto;
import com.thirdeye3_2.video.manager.enums.BotType;

public interface TelegramBotService {

    TelegramBotDto create(TelegramBotDto dto);

    TelegramBotDto update(UUID id, TelegramBotDto dto);

    void delete(UUID id);

    TelegramBotDto getById(UUID id);

    List<TelegramBotDto> getAll();

    TelegramBotDto activate(UUID id);

    TelegramBotDto deactivate(UUID id);

    List<TelegramBotDto> getByTypeAndActive(BotType botType, Boolean active);
    
    Map<BotType, List<TelegramBotDto>> getAllActiveGroupedByType();
}