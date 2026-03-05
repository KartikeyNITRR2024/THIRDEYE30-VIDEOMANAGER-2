package com.thirdeye3_2.video.manager.dtos;
import com.thirdeye3_2.video.manager.enums.Gender;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TtsSoundDto {
    private UUID id;
    private String name;
    private Gender gender;
    private String voicePersonalities;
    private Boolean active;
    private LocalDateTime lastlyUsed;
}
