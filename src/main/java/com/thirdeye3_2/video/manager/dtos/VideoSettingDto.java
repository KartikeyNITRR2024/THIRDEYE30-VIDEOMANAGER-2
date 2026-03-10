package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.AudioTimingMode;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private AudioTimingMode audioTimingMode;
    private LocalDateTime lastlyUsed;
}
