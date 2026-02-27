package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class HeaderDto {

    private UUID id;
    private String name;
    private String description;
    private Boolean active;

    private String backgroundColor;
    private String accentColor;
    private String badgeColor;
    private String textColor;

    private Boolean isBadgePresent;
    private Double badgeWidthPct;
    private String badgePosition;

    private Double accentHeightPct;
    private Integer textSize;
    private Integer logoSize;

    private Double animCycle;
    private Integer animEase;
    private Integer animBuffer;

    private LocalDateTime lastlyUsed;
}
