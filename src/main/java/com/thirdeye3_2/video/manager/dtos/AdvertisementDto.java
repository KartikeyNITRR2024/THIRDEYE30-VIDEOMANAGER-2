package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdvertisementDto {

    private UUID id;
    private String name;
    private String description;
    
    private Boolean active;

    private Boolean isIntroAdvertismentPresent;
    private UUID introAdvertismentMultimediaKey;
    private Integer introAdvertismentSize;

    private Boolean isBadgeAdvertismentPresent;
    private UUID badgeAdvertismentMultimediaKey;
    private Integer badgeAdvertismentSize;

    private String badgeAdvertismentBackgroundColor;
    private String badgeAdvertismentBackgroundWidthPercent;

    private Boolean isContentAdvertismentPresent;

    private List<ContentAdvertisementDto> contentAdvertisements;

    private LocalDateTime createdTime;
}
