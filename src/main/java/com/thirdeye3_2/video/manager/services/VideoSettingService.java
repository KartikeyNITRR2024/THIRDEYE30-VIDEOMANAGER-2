package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.VideoSettingDto;

public interface VideoSettingService {

    VideoSettingDto create(VideoSettingDto dto);

    VideoSettingDto getById(UUID id);

    List<VideoSettingDto> getAll();

    VideoSettingDto update(UUID id, VideoSettingDto dto);

    void delete(UUID id);

    VideoSettingDto makeActive(UUID id);
    
    VideoSettingDto getActiveSetting();
}
