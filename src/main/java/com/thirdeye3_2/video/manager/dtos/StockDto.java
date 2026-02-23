package com.thirdeye3_2.video.manager.dtos;

import java.util.UUID;

import lombok.*;

@Data
@Builder
public class StockDto {

    private UUID id;
    private String name;
    private String marketCode;
    private String stockName;
    private Boolean active;
    private UUID groupId;
}