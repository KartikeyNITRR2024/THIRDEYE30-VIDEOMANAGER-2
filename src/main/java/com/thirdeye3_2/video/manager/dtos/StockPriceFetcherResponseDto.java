package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StockPriceFetcherResponseDto {
   private LocalDateTime dateOfStockPrice;
   private UUID videoUuid;
   private List<String> stockAndMarketCodes;
}
