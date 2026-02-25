package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.ContentVideoDto;

public interface ContentVideoService {

    ContentVideoDto create(ContentVideoDto dto);

    ContentVideoDto update(UUID id, ContentVideoDto dto);

    ContentVideoDto getById(UUID id);

    List<ContentVideoDto> getAll();

    void delete(UUID id);

    ContentVideoDto makeActive(UUID id);

    ContentVideoDto getActive();
}
