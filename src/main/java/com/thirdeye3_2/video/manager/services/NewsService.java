package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.NewsDto;

public interface NewsService {

    NewsDto create(NewsDto dto);

    NewsDto getById(UUID id);

    List<NewsDto> getAll();

    NewsDto update(UUID id, NewsDto dto);

    void delete(UUID id);

    List<NewsDto> getByVideoDetailsId(UUID videoDetailsId);
    
    void autoDeleteUnusedNews();

	NewsDto addAudioMultiMediaKey(UUID uuid, UUID key);

	NewsDto addImageMultiMediaKey(UUID uuid, UUID key);
}
