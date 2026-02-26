package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.BarRaceType;
import com.thirdeye3_2.video.manager.enums.HeaderType;
import com.thirdeye3_2.video.manager.enums.NewsImageType;
import com.thirdeye3_2.video.manager.enums.NewsTextType;

import lombok.Data;

@Data
public class ContentVideoDto {

    private UUID id;
    private String name;
    private String description;
    private Boolean active;

    private Boolean isBackgroundImage;
    private UUID backgroundImage;
    private String backgroundColor;
    private Double backgroundOpacity;

    private Boolean isHeaderPresent;
    private HeaderType headerType;
    private Double headerHeightInPercent;
    private Integer headerStartingPosition;

    private Boolean isBarRace;
    private BarRaceType barRaceType;
    private Double barRaceHeightInPercent;
    private Integer barRaceStartingPosition;

    private Boolean isNewsImage;
    private NewsImageType newsImageType;
    private Double newsImageHeightInPercent;
    private Integer newsImageStartingPosition;

    private Boolean isNewsText;
    private NewsTextType newsTextType;
    private Double newsTexteHeightInPercent;
    private Integer newsTextStartingPosition;

    private Boolean isAudio;
    private UUID audioMultiMediaKey;
    private Integer audioVolumne;

    private LocalDateTime lastlyUsed;
}