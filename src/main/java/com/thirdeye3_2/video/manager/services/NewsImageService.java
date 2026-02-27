package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.NewsImageDto;

public interface NewsImageService {

    NewsImageDto create(NewsImageDto dto);

    NewsImageDto update(UUID id, NewsImageDto dto);

    NewsImageDto getById(UUID id);

    List<NewsImageDto> getAll();

    void delete(UUID id);

    NewsImageDto makeActive(UUID id);

    NewsImageDto getActive();
}
