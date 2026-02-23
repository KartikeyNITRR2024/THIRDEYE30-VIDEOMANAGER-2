package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.StockGroupDto;

public interface StockGroupService {

    StockGroupDto create(StockGroupDto dto);

    StockGroupDto getById(UUID id);

    List<StockGroupDto> getAll();

    StockGroupDto update(UUID id, StockGroupDto dto);

    void delete(UUID id);

    StockGroupDto updateActive(UUID id, Boolean status);
}
