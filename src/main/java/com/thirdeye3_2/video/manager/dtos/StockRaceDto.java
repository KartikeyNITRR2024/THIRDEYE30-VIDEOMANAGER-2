package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StockRaceDto {

    private UUID id;
    private String name;
    private String description;
    private Boolean active;

    private String accentColor;
    private String textColor;
    private String bgBadgeColor;

    private Double barAlpha;
    private Integer glowSize;

    private List<String> barColors;

    private Integer baseFontSize;
    private Integer labelFontSize;
    private Integer valueFontSize;
    private Integer clockFontSize;

    private Double barHeight;
    private Integer topN;
    
    @JsonProperty("xLimitMultiplier")
    private Double xLimitMultiplier;

    private LocalDateTime lastlyUsed;
}
