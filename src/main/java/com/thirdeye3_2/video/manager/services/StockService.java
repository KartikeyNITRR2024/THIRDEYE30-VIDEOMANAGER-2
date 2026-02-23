package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.StockDto;

public interface StockService {

    StockDto create(StockDto dto);

    StockDto getById(UUID id);

    List<StockDto> getAll();

    StockDto update(UUID id, StockDto dto);

    void delete(UUID id);

    StockDto updateActive(UUID id, Boolean status);
}
