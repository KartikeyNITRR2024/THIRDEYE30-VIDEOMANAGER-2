package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.NewsImageAnimationType;

import lombok.Data;

@Data
public class NewsImageDto {

    private UUID id;
    private String name;
    private String description;
    private Boolean active;

    private String enterFrom;
    private String exitTo;
    private Double transitionT;
    private Double marginScale;

    private NewsImageAnimationType animationMode;
    private Double zoomIntensity;

    private Boolean isSpinEnabled;
    private Integer spinSpeed;

    private LocalDateTime lastlyUsed;
}
