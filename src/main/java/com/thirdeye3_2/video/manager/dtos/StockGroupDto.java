package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.*;

@Data
@Builder
public class StockGroupDto {

    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime lastUsed;
    private Boolean active;
    private List<StockDto> stocks;
}
