package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;
import com.thirdeye3_2.video.manager.dtos.VideoDetailsDto;
import com.thirdeye3_2.video.manager.enums.TableName;

public interface VideoDetailsService {

    VideoDetailsDto create(VideoDetailsDto dto);

    VideoDetailsDto getById(UUID id);

    List<VideoDetailsDto> getAll();

    VideoDetailsDto update(UUID id, VideoDetailsDto dto);

    void delete(UUID id);
    
    List<VideoDetailsDto> getByVideoId(UUID videoId);

	void updateBarGraphJsonMultiMediaKey(UUID videoId, UUID key, TableName tableName);
}
