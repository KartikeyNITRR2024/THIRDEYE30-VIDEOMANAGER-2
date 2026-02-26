package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoSettingDto {
    private UUID id;
    private String name;
    private String description;
    private Boolean active;
    private Integer fps;
    private Long height;
    private Long width;
    private Boolean introPresent;
    private Integer introTime;
    private Boolean mainVideoPresent;
    private Integer mainVideoTime;
    private Boolean outroPresent;
    private Integer outroTime;
    private String sequence;
    private Boolean isAudio;
    private UUID audioMultiMediaKey;
    private Integer audioVolumne;
    private LocalDateTime lastlyUsed;
}
