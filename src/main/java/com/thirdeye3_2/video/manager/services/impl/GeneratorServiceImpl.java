package com.thirdeye3_2.video.manager.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.dtos.AudioGenerateContent;
import com.thirdeye3_2.video.manager.dtos.AudioGenerateDto;
import com.thirdeye3_2.video.manager.dtos.AudioGenerateFetcherResponseDto;
import com.thirdeye3_2.video.manager.dtos.CurrentVideoDto;
import com.thirdeye3_2.video.manager.dtos.StockDto;
import com.thirdeye3_2.video.manager.dtos.StockGroupDto;
import com.thirdeye3_2.video.manager.dtos.StockPriceFetcherResponseDto;
import com.thirdeye3_2.video.manager.dtos.VideoDetailsDto;
import com.thirdeye3_2.video.manager.dtos.VideoDto;
import com.thirdeye3_2.video.manager.enums.NewsMultiMediaType;
import com.thirdeye3_2.video.manager.exceptions.GeneratorFetchException;
import com.thirdeye3_2.video.manager.services.AudioGenerateService;
import com.thirdeye3_2.video.manager.services.CurrentVideoService;
import com.thirdeye3_2.video.manager.services.GeneratorService;
import com.thirdeye3_2.video.manager.services.StockGroupService;
import com.thirdeye3_2.video.manager.services.VideoDetailsService;
import com.thirdeye3_2.video.manager.services.VideoService;

@Service
public class GeneratorServiceImpl implements GeneratorService {

	private static final Logger logger = LoggerFactory.getLogger(GeneratorServiceImpl.class);
	
	 @Autowired
	 private CurrentVideoService currentVideoService;
	 
	 @Autowired
	 private VideoDetailsService videoDetailService;
	 
	 @Autowired
	 private VideoService videoService;
	 
	 @Autowired
	 private StockGroupService stockGroupService;
	 
	 @Autowired
	 private AudioGenerateService audioGenerateService;
	 
	 public VideoDto getCurrentVideo()
	 {
		 CurrentVideoDto currentVideoDto = currentVideoService.getCurrentVideo();
		 VideoDto videoDto = videoService.getById(currentVideoDto.getVideoId());
		 return videoDto;
	 }
	 
	 public VideoDetailsDto getCurrentVideoDetails()
	 {
		 VideoDto videoDto = getCurrentVideo();
		 List<VideoDetailsDto> videoDetailsDtos = videoDetailService.getByVideoId(videoDto.getId());
		 if(videoDetailsDtos.size() != 1)
		 {
			 throw new GeneratorFetchException("More then one video details");
		 }
		 return videoDetailsDtos.get(0);
	 }
	 
	 @Override
	 public StockPriceFetcherResponseDto stockPriceFetcher()
	 {
		 VideoDto currentVideo = getCurrentVideo();
		 if(currentVideo.getStockGroup() == null)
		 {
			 throw new GeneratorFetchException("Stock group not updated");
		 }
		 StockGroupDto stockGroupDto = stockGroupService.getById(currentVideo.getStockGroup());
		 if(stockGroupDto == null)
		 {
			 throw new GeneratorFetchException("Stock group not found");
		 }
		 List<String> stockCodes = new ArrayList<>();
		 for(StockDto stock : stockGroupDto.getStocks())
		 {
			 if(stock.getActive())
			 {
				 String stockCode = stock.getStockName();
				 if(stock.getMarketCode() != null && stock.getMarketCode().length() > 0)
				 {
					 stockCode+=("."+stock.getMarketCode());
				 }
				 stockCodes.add(stockCode);
			 }
		 }
		 StockPriceFetcherResponseDto stockPriceFetcherResponseDto = new StockPriceFetcherResponseDto(currentVideo.getDateOfUpload(), currentVideo.getId(), stockCodes);
		 return stockPriceFetcherResponseDto;
	 }
	 
	 @Override
	 public AudioGenerateFetcherResponseDto audioGeneraterFetcher()
	 {
		 AudioGenerateFetcherResponseDto audioGenerateFetcherResponseDto = new AudioGenerateFetcherResponseDto();
		 List<AudioGenerateDto> notGeneratedAudioContent = audioGenerateService.getAllNotGenerated();
		 List<AudioGenerateContent> audioGenerateContents = new ArrayList<>();
		 for(AudioGenerateDto audioGenerateDto : notGeneratedAudioContent)
		 {
			 AudioGenerateContent audioGenerateContent = new AudioGenerateContent();
			 audioGenerateContent.setContent(audioGenerateDto.getContent());
			 audioGenerateContent.setAudioGenerateUuid(audioGenerateDto.getId());
			 audioGenerateContent.setNewsUuid(audioGenerateDto.getForeignKey());
			 audioGenerateContent.setTableName(audioGenerateDto.getTableName());
			 audioGenerateContents.add(audioGenerateContent);
		 }
		 audioGenerateFetcherResponseDto.setContents(audioGenerateContents);
		 return audioGenerateFetcherResponseDto;
	 }
	 
	 
}
