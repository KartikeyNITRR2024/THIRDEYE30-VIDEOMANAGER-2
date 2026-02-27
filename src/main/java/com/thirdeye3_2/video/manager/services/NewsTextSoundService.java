package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.NewsTextSoundDto;

public interface NewsTextSoundService {

    NewsTextSoundDto create(NewsTextSoundDto dto);

    NewsTextSoundDto update(UUID id, NewsTextSoundDto dto);

    NewsTextSoundDto getById(UUID id);

    List<NewsTextSoundDto> getAll();

    void delete(UUID id);

    NewsTextSoundDto makeActive(UUID id);

    NewsTextSoundDto getActive();
}
