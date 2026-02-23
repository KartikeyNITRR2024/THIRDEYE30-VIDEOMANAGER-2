package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.*;

@Data
@Builder
public class NewsDto {

    private UUID id;
    private UUID videoDetailsId;
    private String header;
    private String content;
    private String audioContent;
    private String newsWarningColor;
    private LocalDateTime createdTime;

    private Boolean isImageMultiMediaKeyUploaded;
    private UUID imageMultiMediaKey;

    private Boolean isAudioMultiMediaKeyUploaded;
    private UUID audioMultiMediaKey;

    private Boolean autoDelete;
}