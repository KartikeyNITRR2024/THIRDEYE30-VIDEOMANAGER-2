package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.OutroVideoDto;

public interface OutroVideoService {

    OutroVideoDto create(OutroVideoDto dto);

    OutroVideoDto update(UUID id, OutroVideoDto dto);

    OutroVideoDto getById(UUID id);

    List<OutroVideoDto> getAll();

    void delete(UUID id);

    OutroVideoDto makeActive(UUID id);

    OutroVideoDto getActive();
}
