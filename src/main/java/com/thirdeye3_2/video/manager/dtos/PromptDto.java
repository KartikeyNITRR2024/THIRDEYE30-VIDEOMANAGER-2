package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.PromptType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromptDto {

    private UUID id;
    private String name;
    private PromptType typeOfVideo;
    private LocalDateTime lastlyUsed;
    private LocalDateTime createdDateTime;
}
