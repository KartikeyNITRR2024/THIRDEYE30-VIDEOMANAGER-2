package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class IntroVideoDto {
    private UUID id;
    private String name;
    private String description;
    private Boolean active;
    private Boolean isBackgroundImage;
    private UUID backgroundImage;
    private String backgroundColor;
    private Double backgroundOpacity;
    private Boolean isHeaderPresent;
    private String headerFontType;
    private String headerFontName;
    private Integer headerSize;
    private String headerColor;
    private Boolean isSubHeaderPresent;
    private String subHeaderFontType;
    private String subHeaderFontName;
    private Integer subHeaderSize;
    private String subHeaderColor;
    private Boolean isLinePresent;
    private String lineColor;
    private Integer lineWidth;
    private Integer adImageHeight;
    private Integer adImageWidth;
    private Boolean isAudio;
    private UUID audioMultiMediaKey;
    private Integer audioVolumne;
    private LocalDateTime lastlyUsed;
}
