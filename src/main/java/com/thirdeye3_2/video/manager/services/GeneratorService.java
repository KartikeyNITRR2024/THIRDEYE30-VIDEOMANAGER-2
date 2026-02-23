package com.thirdeye3_2.video.manager.services;

import com.thirdeye3_2.video.manager.dtos.AudioGenerateFetcherResponseDto;
import com.thirdeye3_2.video.manager.dtos.StockPriceFetcherResponseDto;

public interface GeneratorService {

	StockPriceFetcherResponseDto stockPriceFetcher();

	AudioGenerateFetcherResponseDto audioGeneraterFetcher();

}
