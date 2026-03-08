package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.ContentAdvertisementPosition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentAdvertisementDto {

    private UUID id;
    private String name;
    private String description;
    private Boolean active;

    private Integer startingSecond;
    private Integer endingSecond;

    private UUID contentMultimediaKey;
    private Integer height;

    private ContentAdvertisementPosition contentAdvertisementPosition;

    private UUID advertisementId;

    private LocalDateTime createdTime;
}
