package com.thirdeye3_2.video.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.AudioGenerateFetcherResponseDto;
import com.thirdeye3_2.video.manager.dtos.MultiMediaResponseDto;
import com.thirdeye3_2.video.manager.dtos.MultiMediaUploadDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.dtos.StockPriceFetcherResponseDto;
import com.thirdeye3_2.video.manager.services.GeneratorService;
import com.thirdeye3_2.video.manager.services.MultiMediaService;

@RestController
@RequestMapping("/vm2/generater")
public class GeneratorController {

	@Autowired
    private GeneratorService generatorService;
	
	@Autowired
	private MultiMediaService multiMediaService;
	
	@GetMapping("/stock-price-fetcher")
    public Response<StockPriceFetcherResponseDto> stockPriceFetcher() {
        return new Response<>(true, 0, null, generatorService.stockPriceFetcher());
    }
	
	@GetMapping("/audio-generater-fetcher")
    public Response<AudioGenerateFetcherResponseDto> audioGeneraterFetcher() {
        return new Response<>(true, 0, null, generatorService.audioGeneraterFetcher());
    }
	
	@PostMapping(value = "/upload-multi-media", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<MultiMediaResponseDto> uploadFile(@ModelAttribute MultiMediaUploadDto uploadDto) {
        return new Response<>(true, 0, null, multiMediaService.uploadMultiMedia(uploadDto));
    }
}
