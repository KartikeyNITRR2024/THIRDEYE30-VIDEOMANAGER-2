package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.GeneratorType;

import lombok.Data;

@Data
public class ContentGeneratorDto {
    private UUID id;
    private GeneratorType type;
    private String description;
    private Boolean active;
    private String url;
    private Boolean isRunning;
    private LocalDateTime lastlyStarted; 
    private LocalDateTime lastlyCompleted;
}
