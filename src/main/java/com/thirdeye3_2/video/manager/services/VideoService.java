package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;
import com.thirdeye3_2.video.manager.dtos.VideoDto;

public interface VideoService {

    VideoDto create(VideoDto dto);

    VideoDto getById(UUID id);

    List<VideoDto> getAll();

    VideoDto update(UUID id, VideoDto dto);

    void delete(UUID id);
}