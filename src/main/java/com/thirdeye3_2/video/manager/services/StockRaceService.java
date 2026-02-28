package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.StockRaceDto;

public interface StockRaceService {

    StockRaceDto create(StockRaceDto dto);

    StockRaceDto update(UUID id, StockRaceDto dto);

    StockRaceDto getById(UUID id);

    List<StockRaceDto> getAll();

    void delete(UUID id);

    StockRaceDto makeActive(UUID id);

    StockRaceDto getActive();
}
