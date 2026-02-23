package com.thirdeye3_2.video.manager.dtos;

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
}