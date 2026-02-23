package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.TableName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AudioGenerateDto {
    private UUID id;
    private TableName tableName;
    private UUID foreignKey;
    private LocalDateTime createdTime;
    private LocalDateTime audioGeneratedTime;
    private Boolean isAudioGenerated;
    private UUID audioMultiMediaKey;
    private Boolean autoDelete;
    private String content;
}
