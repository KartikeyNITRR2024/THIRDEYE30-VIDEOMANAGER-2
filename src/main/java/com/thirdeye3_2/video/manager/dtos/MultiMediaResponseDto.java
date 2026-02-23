package com.thirdeye3_2.video.manager.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MultiMediaResponseDto implements Serializable  {
	private String key;
	private String folder1;
    private String folder2;
    private String name;
    private String description;
    private String url;
    private long size;
    private String multiMediaType;
    private LocalDateTime timeOfUpload;
    private LocalDateTime lastUsed;
    private Boolean autoDelete;
}
