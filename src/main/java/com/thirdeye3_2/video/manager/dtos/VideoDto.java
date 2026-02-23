package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.VideoType;
import lombok.Data;

@Data
public class VideoDto {

	private UUID id;
    private String name;
    private String description;
    private LocalDateTime dateOfUpload;
    private VideoType typeOfVideo;
    private UUID stockGroup;
    private LocalDateTime createdDateTime;
    private Boolean adsPresent;
    private String adsId;
    private Boolean isCompleted;
}
