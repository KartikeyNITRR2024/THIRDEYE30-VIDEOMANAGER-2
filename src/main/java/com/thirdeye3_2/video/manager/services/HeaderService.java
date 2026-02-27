package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.HeaderDto;

public interface HeaderService {

    HeaderDto create(HeaderDto dto);

    HeaderDto update(UUID id, HeaderDto dto);

    HeaderDto getById(UUID id);

    List<HeaderDto> getAll();

    void delete(UUID id);

    HeaderDto makeActive(UUID id);

    HeaderDto getActive();
}
