package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.IntroVideoDto;

public interface IntroVideoService {

    IntroVideoDto create(IntroVideoDto dto);

    IntroVideoDto update(UUID id, IntroVideoDto dto);

    IntroVideoDto getById(UUID id);

    List<IntroVideoDto> getAll();

    void delete(UUID id);

    IntroVideoDto makeActive(UUID id);

    IntroVideoDto getActive();
}