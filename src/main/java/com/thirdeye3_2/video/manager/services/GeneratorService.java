package com.thirdeye3_2.video.manager.services;

import com.thirdeye3_2.video.manager.dtos.AudioGenerateFetcherResponseDto;
import com.thirdeye3_2.video.manager.dtos.StockPriceFetcherResponseDto;
import com.thirdeye3_2.video.manager.dtos.VideoGenerateFetcherResponseDto;

public interface GeneratorService {

	StockPriceFetcherResponseDto stockPriceFetcher();

	AudioGenerateFetcherResponseDto audioGeneraterFetcher();

	VideoGenerateFetcherResponseDto videoGeneraterFetcher();

}
