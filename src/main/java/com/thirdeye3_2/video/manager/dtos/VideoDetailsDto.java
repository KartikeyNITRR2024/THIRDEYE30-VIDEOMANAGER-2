package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class VideoDetailsDto {
    private UUID id;
    private UUID videoId;
    private String introHeader;
    private String introSubHeader;
    private String header;
    private String outroHeader;
    private String outroSubHeader;
    private Boolean isbarGraphJsonMultiMediaKeyUploaded;
    private UUID barGraphJsonMultiMediaKey;
    private String introAudioString;
    private Boolean isIntroAudioStringPresent;
    private Boolean isIntroAudioStringUploaded;
    private UUID introAudioMultiMediaKey;
    private String outroAudioString;
    private Boolean isOutroAudioStringPresent;
    private Boolean isOutroAudioStringUploaded;
    private UUID outroAudioMultiMediaKey;
    private LocalDateTime createdTime;
}