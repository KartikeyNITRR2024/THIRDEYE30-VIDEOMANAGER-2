package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class NewsTextSoundDto {

    private UUID id;
    private String name;
    private String description;
    private Boolean active;

    private String bgTheme;
    private String accentColor;
    private String textColor;

    private Double boxAlpha;
    private Double boxPad;
    private Integer dpi;

    private Double transitionT;
    private String enterFrom;
    private String exitTo;

    private Double focusY;
    private Double gapMain;
    private Double gapSub;
    private Double sideScale;

    private Integer headerWrap;
    private Integer contentWrap;
    private Integer otherWrap;

    private Integer baseFontSize;

    private Boolean isAudio;
    private Double endSilenceTimeInSeconds;
    private Integer audioVolumne;

    private LocalDateTime lastlyUsed;
}
